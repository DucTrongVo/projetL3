/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import Elements.Element;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.util.Elements;


/**
 *Classe qui représente le stock de tout les
 * matière première et produit
 */
public class Stock2 {
    private static int nbElement = 0;
    private final static String RESOURCES_PATH = "src/main/resources/";
    private final static String ELEMENTS_FILE_NAME = "elements.csv";
    private final static char SEPARATOR=';';

    
    
public void addElements(){
}
   

    
public String toString(){
    return("");
}    
    
   

    public static int getNbElement() {
        return nbElement;
    }

   
    
    
    public Liste<Element> findElments(){
        FileReader fr =null;
        try {
            File file =new File (RESOURCES_PATH+ELEMENTS_FILE_NAME);
            fr = new FileReader(file);
            CSVReader CSVReader=new CSVReader(fr,SEPARATOR);
            List<String[] > data = new ArrayList<String[] >();
            String[] nextLine = null;
            while ((nextLine = csvReader.readNext()) != null) {
                int size = nextLine.length;
                // ligne vide
                if (size == 0) {
                    continue;
                }
                String debut = nextLine[0].trim();
                if (debut.length() == 0 && size == 1) {
                    continue;
                }
                
                // ligne de commentaire
                if (debut.startsWith("#")) {
                    continue;
                }
                data.add(nextLine);
                
                List<Element> elements = new ArrayList<Element>();
                
                
                for (String[] oneData : data) {
                    String code = oneData[0];
                    String nom = oneData[1];
                    String quantiteSTR = oneData[2];
                    String  unite = oneData[3];
                    String prixAchatSTR= oneData[4];
                    String prixVenteSTR= oneData[5];
                    
                    Integer quantite = Integer.parseInt(quantiteSTR);
                    Integer prixAchat = Integer.parseInt(prixAchatSTR);
                    Integer prixVente = Double.pars(prixVenteSTR);
                    
                }
            }   return data;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
