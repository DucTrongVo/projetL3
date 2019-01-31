
package Stock;

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


/**
 *Classe qui représente le stock de tout les
 * matière première et produit
 */
public class Stock implements StockP{

    private static int nbElement = 0;
    private final static String RESOURCES_PATH = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction/src/";
    private final static String ELEMENTS_FILE_NAME = "elements.csv";
    private final static char SEPARATOR=';';
    
    
    public Stock() {
    }
    
    
    public void addElements(){
    }
   

    
  
    
   

    public static int getNbElement() {
        return nbElement;
    }

   
    public List<Element> findElement() {

        final List<String[] > data = readCsvFile(RESOURCES_PATH + ELEMENTS_FILE_NAME, SEPARATOR);

        final List<Element> elements = dataToElements(data);

        return elements;
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
            
            final Integer quantite = Integer.parseInt(quantiteSTR);
            final int prixAchat;
            prixAchat = Integer.parseInt(prixAchatSTR);
            final int prixVente;
            prixVente = Integer.parseInt(prixVenteSTR);
            final Element element= new Element(code,nom,quantite,unite,prixAchat,prixVente);
            elements.add(element);
        }
        return elements;
    }
    
    public void afficherListe(List<Element> listeE){
        Iterator i=listeE.iterator();
        while(i.hasNext()){
        Element v = (Element)i.next();
        System.out.println(v.toString());
        System.out.println("--------------------------------***");
      }
    }
 
  
   

}
