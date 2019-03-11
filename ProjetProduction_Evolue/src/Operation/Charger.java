/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operation;

import Chaine.Chaine;
import Elements.Element;
import Gestion_Chaine.Usine;
import static Gestion_Element.CsvFileHelper.readCsvFile;
import Gestion_Element.Stock;
import Historique.AnneeProduction;
import Historique.SemaineProduction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contenir les fonctions servent à charger les données dans la base des données du programme.
 * IL FAUT MODIFIER LES RESOURCES_ELEMENT_PATH LE CHEMIN VERS L'ENDROIT OU CONTIENT LE FILES ELEMENTS.CSV
 * LES RESOURCES_HISTOIRE_PATH LE CHEMIN VERS L'ENDROIT OU CONTIENT LE FILES histoire_production.CSV
 * ET LE RESOURCES_CHAINE_PATH ET RESOURCES_PATH_SUB LE CHEMIN VERS L'ENDROIT OU CONTIENT LE FILE CHAINES.CSV
 * @author trongvo
 */
public class Charger {
    
    private final static String RESOURCES_CHAINE_PATH = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction_Evolue/FilesCSV/Chaines/";
    private final static String RESOURCES_ELEMENT_PATH = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction_Evolue/FilesCSV/Elements/";
    private final static String RESOURCES_HISTOIRE_PATH = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction_Evolue/FilesCSV/Historique_Production/";
    private final static String RESOURCES_PATH_SUB = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction_Evolue/";

    private final static String CHAINES_FILE_NAME = "chaines.csv";
    private final static String ELEMENTS_FILE_NAME = "elements.csv";
    private final static String SOUS_ELEMENT_FILE_NAME ="sous_element.csv";
    private final static String HISTOIRE_FILE_NAME = "histoire_production.csv";
    private final static char SEPARATOR=';';
    private final static char SEPARATOR_SUB=',';

    public Charger() {
    }
    
    
    // CHARGER CHAINE EN USINE
    /**
     * Fonction sert à chargé les chaines de production existent
     * dans le files "chaines.csv" et les enregistrent dans le DB
     * @return 
     */
    public List<Chaine> chargerChaine(Usine u) {
        final List<String[] > data = readCsvFile(RESOURCES_CHAINE_PATH + CHAINES_FILE_NAME, SEPARATOR);

        u.setListChaine(dataToChaine(data));

        return u.getListeChaine();
    }
    
    
    /**
     * Créer une liste sous_element pour chaque liste d'element
     * d'entrer de la chaine de production
     * @param liste 
     */
    public void ecrireSousListe(String liste){
        try (FileWriter writer = new FileWriter("sous_element.csv")){
			writer.write(liste);
		} catch (IOException e) {
			System.out.println(e+" erreur écriture!");;
		}
    }
    
    public HashMap<String, Double> findElement_sub() {

        final List<String[] > data = readCsvFile(RESOURCES_PATH_SUB + SOUS_ELEMENT_FILE_NAME, SEPARATOR_SUB);
        HashMap<String, Double> element = dataToHashMap(data);
        

        return element;
    }
    
    /**
     * Retourne la liste des Chaines de production
     * pour utiliser dans la fonction findChaine()
     * @param data
     * @return 
     */
    private List<Chaine> dataToChaine(List<String[]> data){

        final List<Chaine> chaines = new ArrayList<>();
        for (String[] oneData : data) {
            final String codeC = oneData[0];
            final String nomC = oneData[1];
            
            final String  EEntre = oneData[2];
            // Utiliser la fonction datatoElement
            this.ecrireSousListe(EEntre);   
            HashMap<String, Double>ElE = this.findElement_sub();
            
      
            
            final String  ESortie = oneData[3];
            this.ecrireSousListe(ESortie);
            HashMap<String, Double> ElS = this.findElement_sub();
            
            final Chaine chaine= new Chaine(codeC,nomC,ElE,ElS);
            chaines.add(chaine);
        }
        return chaines;
    }
    
    
    /**
     * écrire les sous-matière/sous-produit dans 
     * un liste sous-element.csv
     * @param data 
     * @return 
     */
    public HashMap<String,Double> dataToHashMap(List<String[]> data){
        HashMap<String,Double> element = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SOUS_ELEMENT_FILE_NAME)))
	{
            String line;
            while((line = br.readLine()) != null){
		StringTokenizer st = new StringTokenizer(line,",");
		while(st.hasMoreTokens()){
                    String nomE = st.nextToken();
                    String nomeE_pur = nomE.replace("(","");
                    
                    String quantiteE = st.nextToken();
                    String quantite_num = quantiteE.replace(")","");
                   
                    double quantite = Double.parseDouble(quantite_num);     
                    element.put(nomeE_pur,quantite);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Usine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return element;
    }
    
    //CHARGER ELEMENTS EN STOCKS
    
    /**
     * Charger le stock avec les éléments dans le file elements.csv
     * @return 
     */
    public List<Element> chargerElement(Stock stock) {

        final List<String[] > data = readCsvFile(RESOURCES_ELEMENT_PATH + ELEMENTS_FILE_NAME, SEPARATOR);

        stock.setListStock(dataToElements(data));

        return stock.getListStock();
    }
    
    private List<Element> dataToElements(List<String[]> data){

        final List<Element> elements = new ArrayList<Element>();
        for (String[] oneData : data) {
            final String code = oneData[0];
            final String nom = oneData[1];
            final String quantiteSTR = oneData[2];
            final String  unite = oneData[3];
            final String prixAchatSTR = oneData[4];
            final String prixVenteSTR = oneData[5];
            final String demande = oneData[6];
            
            final Double quantite = Double.parseDouble(quantiteSTR);
            final Double dem = Double.parseDouble(demande);
                      
            if(prixAchatSTR.equals("NA")){
                String prixAchat = "NA";
                if(prixVenteSTR.equals("NA")){
                    String prixVente = "NA";
                    final Element element= new Element(code,nom,quantite,unite,prixAchat,prixVente,dem);
                    elements.add(element);
                }
                else{
                    double prixVente = Double.parseDouble(prixVenteSTR);
                    final Element element= new Element(code,nom,quantite,unite,prixAchat,prixVente,dem);
                    elements.add(element);
            }
            }
            else{
                final double prixAchat;
                prixAchat = Double.parseDouble(prixAchatSTR);
                if(prixVenteSTR.equals("NA")){
                    String prixVente = "NA";
                    final Element element= new Element(code,nom,quantite,unite,prixAchat,prixVente,dem);
                    elements.add(element);
                }
                else{
                    double prixVente = Double.parseDouble(prixVenteSTR);
                    final Element element= new Element(code,nom,quantite,unite,prixAchat,prixVente,dem);
                    elements.add(element);
                }
            }
            
        }
        return elements;
    }
    
    // CHARGER SEMAINE DE PRODUCTION 
    /**
     * Charger l'histoire de production avec les semaines de productions
     * qui se trouve dans le file histoire_production.csv
     * @param ap
     * @return 
     */
    public ArrayList<SemaineProduction> chargerHistoire(AnneeProduction ap){
        final List<String[] > data = readCsvFile(RESOURCES_HISTOIRE_PATH + HISTOIRE_FILE_NAME, SEPARATOR);

        ap.setAnneeProduction(dataToAnnee(data));

        return ap.getAnnee();
    }
    
    private ArrayList<SemaineProduction> dataToAnnee(List<String[]> data){

        final ArrayList<SemaineProduction> annees = new ArrayList<>();
        for (String[] oneData : data) {
            final String nomSem = oneData[0];
            int nomSem_final = Integer.parseInt(nomSem);
            final String codeC = oneData[1];
            this.ecrireSousListe(codeC);
            HashMap<String, Double> ch = this.findChaine_Res_sub();
            HashMap<String, Integer> nv = this.findChaine_Niv_sub();
          
            final SemaineProduction sp= new SemaineProduction(nomSem_final,ch,nv);
            annees.add(sp);
        }
        return annees;
    }
  
    
    public HashMap<String, Double> findChaine_Res_sub() {

        final List<String[] > data = readCsvFile(RESOURCES_PATH_SUB + SOUS_ELEMENT_FILE_NAME, SEPARATOR_SUB);
        HashMap<String, Double> listCh_Res = dataToHashMap_Res(data);
        HashMap<String, Integer> listCh_Niv = dataToHashMap_Niv(data);
        

        return listCh_Res;
    }
    
    public HashMap<String, Integer> findChaine_Niv_sub() {

        final List<String[] > data = readCsvFile(RESOURCES_PATH_SUB + SOUS_ELEMENT_FILE_NAME, SEPARATOR_SUB);
        HashMap<String, Double> listCh_Res = dataToHashMap_Res(data);
        HashMap<String, Integer> listCh_Niv = dataToHashMap_Niv(data);
        

        return listCh_Niv;
    }
    
    public HashMap<String,Double> dataToHashMap_Res(List<String[]> data){
        HashMap<String,Double> res = new HashMap<>();
        HashMap<String,Integer> niv = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SOUS_ELEMENT_FILE_NAME)))
	{
            String line;
            while((line = br.readLine()) != null){
		StringTokenizer st = new StringTokenizer(line,",");
		while(st.hasMoreTokens()){
                    String nomC = st.nextToken();
                    String nomC_pur = nomC.replace("(","");
                    
                    String resC = st.nextToken();
                    double res_final = Double.parseDouble(resC); 
                    
                    String nivC = st.nextToken();
                    String nivC_num = nivC.replace(")", "");
                    int nivC_final = Integer.parseInt(nivC_num);
                    
                    res.put(nomC_pur,res_final);
                    niv.put(nomC_pur, nivC_final);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Usine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public HashMap<String,Integer> dataToHashMap_Niv(List<String[]> data){
        HashMap<String,Double> res = new HashMap<>();
        HashMap<String,Integer> niv = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SOUS_ELEMENT_FILE_NAME)))
	{
            String line;
            while((line = br.readLine()) != null){
		StringTokenizer st = new StringTokenizer(line,",");
		while(st.hasMoreTokens()){
                    String nomC = st.nextToken();
                    String nomC_pur = nomC.replace("(","");
                    
                    String resC = st.nextToken();
                    double res_final = Double.parseDouble(resC); 
                    
                    String nivC = st.nextToken();
                    String nivC_num = nivC.replace(")", "");
                    int nivC_final = Integer.parseInt(nivC_num);
                    
                    res.put(nomC_pur,res_final);
                    niv.put(nomC_pur, nivC_final);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Usine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return niv;
    }
 
}
