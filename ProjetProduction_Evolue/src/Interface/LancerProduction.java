
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Chaine.Chaine;
import Historique.SemaineProduction;
import Operation.Charger;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author trongvo
 */
public class LancerProduction extends javax.swing.JFrame {

    Font Italic = new Font("Serif", Font.ITALIC, 12);
    Font Bold= new Font("Serif", Font.BOLD, 14);
    
    Acceuil ac = new Acceuil();
    Charger charger = new Charger();
    DefaultTableModel table1;
    DefaultTableModel table2;
    
    public LancerProduction() {
        initComponents();this.setLocationRelativeTo(null);
        for(SemaineProduction sp : this.ac.getAnneeProduction().getAnnee()){
            jComboBox1.addItem("Semaine "+ sp.getNoSem());
        }
        jComboBox2.addItem(Integer.toString(this.ac.getAnneeProduction().getNombreSemaine()));
        jComboBox2.addItem(Integer.toString(this.ac.getAnneeProduction().getNombreSemaine()+1));
        table1 = (DefaultTableModel) jTable1.getModel();
        table2 = (DefaultTableModel) jTable2.getModel();
    }

    public void showTable(){
        String nomSem = "Semaine ";
        for(SemaineProduction sp : this.ac.getAnneeProduction().getAnnee()){
            if((nomSem +sp.getNoSem()).equals(jComboBox1.getSelectedItem().toString())){
                Iterator<Map.Entry<String, Double>> res = sp.getChaineResultat().entrySet().iterator();
                    while(res.hasNext()){
                        Map.Entry<String, Double> a = (Map.Entry<String, Double>)res.next();
                        table1.addRow(new Object[]{
                            a.getKey(),this.ac.getUsine().getChaineparCode(a.getKey()).getNomC(),sp.getChaineNiv().get(a.getKey()),a.getValue()
                    });
                }
            }
        }
    }
    
    public void clearTable(){
        while (table1.getRowCount()>0){
            table1.removeRow(0);
        }
    }
    public ArrayList<Chaine> lireChaineEntree(){
        ArrayList<Chaine> c_temp = new ArrayList<>();
        StringReader reader = new StringReader(jTextField1.getText());
        try (BufferedReader br = new BufferedReader(reader)){
            String line;
            while((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line,",");
                while(st.hasMoreTokens()){
                    String nomC = st.nextToken();
                    String nomeC_pur = nomC.replace("[()]","");
                    
                    Chaine chaine_final = this.ac.getUsine().getChaineparCode(nomeC_pur);
                    c_temp.add(chaine_final);
                }
            }
        }catch (IOException ex) {
            Logger.getLogger(AfficherChaine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c_temp;
    } 
    
    public String getPourcentage(double fils, double mere){
        if(mere == 0){
            return "Satisfaire";
        }
        else{
            double pour = (fils/mere)*100;
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            if(pour > 100){
                return "Satisfaire";
            }
            else {
                return (numberFormat.format(pour));
            }
        }
    }
    
    public void lancerLaProduction(List<Chaine> c_temp){
        SemaineProduction semPr;
        HashMap<String, Double> res_pro = new HashMap<String,Double>();
        HashMap<String, Integer> chaine_niv = new HashMap<>();
        
            for(Chaine c : c_temp){
                c.setNiveauActive(Integer.parseInt(jTextField3.getText()));
                String result;
                double res = this.ac.getCalcul().efficacite(c, this.ac.getStock());
                if(res == 0){
                    result = "Production impossible!\n";
                }
                else {
                    result = String.valueOf(res);
                }

                Iterator<Map.Entry<String, Double>> es = c.getElementSortie().entrySet().iterator();
                while(es.hasNext()){
                Map.Entry<String, Double> a = (Map.Entry<String, Double>)es.next();
                table2.addRow(new Object[]{
                    c.getCodeC(),c.getNomC(),c.getNiveauActive(),a.getKey(),(a.getValue()*c.getNiveauActive()),
                    getPourcentage(((a.getValue()*c.getNiveauActive())+this.ac.getStock().getElementparCode(a.getKey()).getQuantiteE()),this.ac.getStock().getElementparCode(a.getKey()).getDemande())
                        ,result
                });
                }
                res_pro.put(c.getCodeC(), res);
                chaine_niv.put(c.getCodeC(), Integer.parseInt(jTextField3.getText()));
                }
            semPr = new SemaineProduction(ac.getAnneeProduction().getNombreSemaine()+1,res_pro,chaine_niv);
            this.ac.getAnneeProduction().ajouterSemaine(semPr);
            this.ac.getAnneeProduction().ecrireHistoire();
            ArrayList<SemaineProduction> list_final = this.charger.chargerHistoire(this.ac.getAnneeProduction());
            this.ac.setSemaineProduction(list_final); 
    }
    
    public void lancerLaProduction_semainePre(List<Chaine> c_temp, int nbSem){
        SemaineProduction semPr = this.ac.getAnneeProduction().getSemaineParNb(nbSem);
        for(Chaine c : c_temp){
                c.setNiveauActive(Integer.parseInt(jTextField3.getText()));
                String result;
                double res = this.ac.getCalcul().efficacite(c, this.ac.getStock());
                if(res == 0){
                    result = "Production impossible!\n";
                }
                else {
                    result = String.valueOf(res);
                }

                Iterator<Map.Entry<String, Double>> es = c.getElementSortie().entrySet().iterator();

                while(es.hasNext()){
                Map.Entry<String, Double> a = (Map.Entry<String, Double>)es.next();
                table2.addRow(new Object[]{
                    c.getCodeC(),c.getNomC(),c.getNiveauActive(),a.getKey(),(a.getValue()*c.getNiveauActive()),
                    getPourcentage(((a.getValue()*c.getNiveauActive())+this.ac.getStock().getElementparCode(a.getKey()).getQuantiteE()),this.ac.getStock().getElementparCode(a.getKey()).getDemande())
                    ,result
                });
                }
                semPr.getChaineResultat().put(c.getCodeC(), res);
                semPr.getChaineNiv().put(c.getCodeC(), Integer.parseInt(jTextField3.getText()));
                }

            this.ac.getAnneeProduction().ecrireHistoire();
            ArrayList<SemaineProduction> list_final = this.charger.chargerHistoire(this.ac.getAnneeProduction());
            this.ac.setSemaineProduction(list_final);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BttonAfficheSemAct = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        BttonLancer = new javax.swing.JButton();
        BttonAfficherSem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BttonExportRes = new javax.swing.JButton();
        BttonRetour = new javax.swing.JButton();
        BttonCopier = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(136, 131, 131));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gestion Prodcution", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        BttonAfficheSemAct.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        BttonAfficheSemAct.setText("Afficher la Semaine Actuelle");
        BttonAfficheSemAct.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BttonAfficheSemAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttonAfficheSemActActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 3, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Chaine");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 3, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Niveau d'activation");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 3, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Choisi la semaine pour recopier ");

        BttonLancer.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        BttonLancer.setText("Lancer");
        BttonLancer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BttonLancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttonLancerActionPerformed(evt);
            }
        });

        BttonAfficherSem.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        BttonAfficherSem.setText("Afficher");
        BttonAfficherSem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BttonAfficherSem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttonAfficherSemActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code Chaine", "Nom Chaine", "Niveau d'Activation", "Résultat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        BttonExportRes.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        BttonExportRes.setText("Exporter Résultat");
        BttonExportRes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        BttonRetour.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        BttonRetour.setText("Retourner");
        BttonRetour.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BttonRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttonRetourActionPerformed(evt);
            }
        });

        BttonCopier.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        BttonCopier.setText("Copier");
        BttonCopier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BttonCopierActionPerformed(evt);
            }
        });

        jLabel6.setBackground(java.awt.Color.orange);
        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel6.setForeground(java.awt.Color.blue);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PAR SAISIR LES CHAINES SOUHAITÉES:");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setOpaque(true);

        jLabel8.setBackground(java.awt.Color.orange);
        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel8.setForeground(java.awt.Color.blue);
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("PAR COPIER UNE SEMAINE PRÉCÉDENTE:");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel8.setOpaque(true);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code Chaine", "Nom Chaine", "Niveau d'Activation", "Code Produit Sortie", "Nb Produit Créé(s)", "% de Satisfaction", "Résultat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setForeground(java.awt.Color.yellow);
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Choisir la semaine de production:");
        jLabel7.setToolTipText("");
        jLabel7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BttonExportRes)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BttonRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(312, 312, 312)
                                                .addComponent(jLabel1)))
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31)
                                                .addComponent(BttonCopier))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(35, 35, 35)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(44, 44, 44)
                                                .addComponent(BttonAfficherSem))))
                                    .addComponent(BttonLancer, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(220, 220, 220))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(BttonAfficheSemAct)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(350, 350, 350)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BttonAfficheSemAct)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(31, 31, 31)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(BttonLancer)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BttonExportRes)
                .addGap(42, 42, 42)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BttonAfficherSem))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BttonCopier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BttonRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
       
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void BttonAfficheSemActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttonAfficheSemActActionPerformed

        jTextField2.setText(Integer.toString(ac.getAnneeProduction().getNombreSemaine()));
    }//GEN-LAST:event_BttonAfficheSemActActionPerformed

    private void BttonLancerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttonLancerActionPerformed
        while (table2.getRowCount()>0){
            table2.removeRow(0);
        }

        if((jTextField1.getText().equals("")) || (jTextField3.getText().equals(""))){
                    JOptionPane.showMessageDialog(rootPane, "Il faut remplir les champs!");
        }
        else{
            int month = Integer.parseInt((String)(jComboBox2.getSelectedItem()));
            
            if (month == (this.ac.getAnneeProduction().getNombreSemaine()+1)){
                ArrayList<Chaine> c_temp = this.lireChaineEntree();
                this.lancerLaProduction(c_temp);
            }
            else{
                if (month == (this.ac.getAnneeProduction().getNombreSemaine())){
                    ArrayList<Chaine> c_temp = this.lireChaineEntree();
                    this.lancerLaProduction_semainePre(c_temp,this.ac.getAnneeProduction().getNombreSemaine());
                }
            }
        }
        
       
    }//GEN-LAST:event_BttonLancerActionPerformed

    private void BttonAfficherSemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttonAfficherSemActionPerformed
        this.clearTable();
        this.showTable();
    }//GEN-LAST:event_BttonAfficherSemActionPerformed

    private void BttonRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttonRetourActionPerformed
        this.setVisible(false);
        ac = new Acceuil();
        ac.setVisible(true);
    }//GEN-LAST:event_BttonRetourActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void BttonCopierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BttonCopierActionPerformed
        String str_chaine = "";
        
        for (int i=0; i<table1.getRowCount(); i++){
            str_chaine += table1.getValueAt(i,0);
            if(i<table1.getRowCount()-1){
                str_chaine += ",";
            }
        }
        
        String str_niv = ""+table1.getValueAt(0, 2);
        
        jTextField1.setText(str_chaine);
        jTextField3.setText(str_niv);
        jTextField2.setText(Integer.toString(ac.getAnneeProduction().getNombreSemaine()+1));
    }//GEN-LAST:event_BttonCopierActionPerformed

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        
    }//GEN-LAST:event_jTable1PropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LancerProduction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LancerProduction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LancerProduction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LancerProduction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LancerProduction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BttonAfficheSemAct;
    private javax.swing.JButton BttonAfficherSem;
    private javax.swing.JButton BttonCopier;
    private javax.swing.JButton BttonExportRes;
    private javax.swing.JButton BttonLancer;
    private javax.swing.JButton BttonRetour;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

    
    
}
