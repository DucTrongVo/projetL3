
package Stock;

import Calculs.Calcul;
import Elements.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.util.Elements;
import static Stock.CsvFileHelper.readCsvFile;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.FileWriter;


/**
 *Classe qui représente le stock de tout les
 * matière première et produit
 */
public class Stock implements StockP{

    private static int nbElement = 0;
    List<Element> Stock;
    private final static String RESOURCES_PATH = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction/";
    private final static String ELEMENTS_FILE_NAME = "elements.csv";
    private final static char SEPARATOR=';';
    
    
    public Stock() {
    }
    
    
   
   

    public static int getNbElement() {
        return nbElement;
    }

   
    public List<Element> findElement() {

        final List<String[] > data = readCsvFile(RESOURCES_PATH + ELEMENTS_FILE_NAME, SEPARATOR);

        this.Stock = dataToElements(data);

        return Stock;
    }
 

    public void addElement(Element e){
        this.Stock.add(e);
        this.rewriteCSV();
    }
    
    private List<Element> dataToElements(List<String[]> data){

        final List<Element> elements = new ArrayList<Element>();
        for (String[] oneData : data) {
            final String code = oneData[0];
            final String nom = oneData[1];
            final String quantiteSTR = oneData[2];
            final String  unite = oneData[3];
            final String prixAchatSTR= oneData[4];
            final String prixVenteSTR= oneData[5];
            
            final Double quantite = Double.parseDouble(quantiteSTR);
                      
            if(prixAchatSTR.equals("NA")){
                String prixAchat = "NA";
                if(prixVenteSTR.equals("NA")){
                    String prixVente = "NA";
                    final Element element= new Element(code,nom,quantite,unite,prixAchat,prixVente);
                    elements.add(element);
                }
                else{
                    double prixVente = Double.parseDouble(prixVenteSTR);
                    final Element element= new Element(code,nom,quantite,unite,prixAchat,prixVente);
                    elements.add(element);
            }
            }
            else{
                final double prixAchat;
                prixAchat = Double.parseDouble(prixAchatSTR);
                if(prixVenteSTR.equals("NA")){
                    String prixVente = "NA";
                    final Element element= new Element(code,nom,quantite,unite,prixAchat,prixVente);
                    elements.add(element);
                }
                else{
                    double prixVente = Double.parseDouble(prixVenteSTR);
                    final Element element= new Element(code,nom,quantite,unite,prixAchat,prixVente);
                    elements.add(element);
                }
            }
            
            
            this.nbElement++;
        }
        return elements;
    }
    
    public void afficherListe(){
        System.out.println("LA LISTE DES ÉLÉMENTS:");
        for (Element e : this.Stock) {
            System.out.println(e.toString());
            System.out.println("------------------ELEMENT------------------");
        }
    }
    
    public Element getElementparCode(String e){
        Element the_one = null;
        for (Element oneData : this.Stock) {
            if (oneData.getCodeE().equals(e)){
                the_one = oneData;
            } 
        }   
        return the_one;
    
        
    }
    
    
    /**
     * methode pour enleveer la quantité de matière
     * nécessaire pour la production du stocl

     * 
     */
    public void soustraireStock(Element e, double quantite){
        double nouv_quant = e.getQuantiteE() - quantite;
        this.getElementparCode(e.getCodeE()).setQuantiteE(nouv_quant);
        if (nouv_quant < 0){
            try (FileWriter writer = new FileWriter("liste_achat.csv",true)){
			writer.write(e.getCodeE()+";"+e.getNomE()+";"+nouv_quant*(-1)+";"+e.getUMesure()+";"+e.getPrixAchat()+"\n");
                        
		} catch (IOException ex) { 
                Logger.getLogger(Calcul.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        } 
    
    /**
     * Méthode pour mettre en stock les nouveaux produits
     */
    public void additionStock(Element e, double quantite){
        double nouv_quant = e.getQuantiteE() + quantite;
        this.getElementparCode(e.getCodeE()).setQuantiteE(nouv_quant);
         
    }
    
    
    public void rewriteCSV(){
        
            try (FileWriter writer = new FileWriter("elements.csv")){
                writer.write("Code"+";"+"Nom"+";"+"Quantite"+";"+"unite"+";"+"achat"+";"+"vente\n");
            }catch (IOException ex) { 
                Logger.getLogger(Calcul.class.getName()).log(Level.SEVERE, null, ex);
            } 
                try (FileWriter writer2 = new FileWriter("elements.csv",true)){
                for (Element e : this.Stock){
		writer2.write(e.getCodeE()+";"+e.getNomE()+";"+e.getQuantiteE()+";"+e.getUMesure()+";"+e.getPrixAchat()+";"+e.getPrixVente()+"\n");
                }
		} catch (IOException ex) { 
                Logger.getLogger(Calcul.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    
}
