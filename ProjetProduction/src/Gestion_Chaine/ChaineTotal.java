/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion_Chaine;

import Chaine.Chaine;
import static Stock.CsvFileHelper.readCsvFile;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author trongvo
 */
public class ChaineTotal {
    
    
    private final static String RESOURCES_PATH = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction/src/";
    private final static String CHAINES_FILE_NAME = "chaines.csv";
    private final static String SOUS_ELEMENT_FILE_NAME ="sous_element.csv";
    private final static char SEPARATOR=';';
    private final static char SEPARATOR_SUB=',';
    
    public List<Chaine> findChaine() {

        final List<String[] > data = readCsvFile(RESOURCES_PATH + CHAINES_FILE_NAME, SEPARATOR);

        final List<Chaine> chaines = dataToChaine(data);

        return chaines;
    }
    
    public void ecrireSousListe(String liste){
        try (FileWriter writer = new FileWriter("sous_element.csv")){
			writer.write(liste+"\n");
			
		} catch (IOException e) {
			System.out.println(e+" erreur écriture!");;
		}
    }
    public HashMap<String, Integer> findElement_sub() {

        final List<String[] > data1 = readCsvFile(RESOURCES_PATH + SOUS_ELEMENT_FILE_NAME, SEPARATOR_SUB);

        final HashMap<String, Integer> element = dataToHashMap(data1);

        return element;
    }
    
    private List<Chaine> dataToChaine(List<String[]> data){

        final List<Chaine> chaines = new ArrayList<Chaine>();
        for (String[] oneData : data) {
            final String codeC = oneData[0];
            final String nomC = oneData[1];
            
            final String  EEntre = oneData[2];
            // Utiliser la fonction datatoElement
            this.ecrireSousListe(EEntre);
            HashMap<String, Integer>ElE = this.findElement_sub();
            
            final String  ESortie = oneData[3];
            this.ecrireSousListe(ESortie);
            HashMap<String, Integer> ElS = this.findElement_sub();
            
            final Chaine chaine= new Chaine(codeC,nomC,ElE,ElS);
            chaines.add(chaine);
        }
        return chaines;
    }
    
    /**
     * écrire les sous-matière/sous-produit résultat dans un liste
     * @param data 
     * @return 
     */
    private HashMap<String,Integer> dataToHashMap(List<String[]> data){

        final HashMap<String,Integer> element = new HashMap<String,Integer>();
        for (String[] oneData : data) {
            final String nomE = oneData[0];
            final String quantiteE = oneData[1];
            
            final Integer quantite = Integer.parseInt(quantiteE);       
            
            element.put(nomE,quantite);
        }
        return element;
    }
    
    public void afficheChaine(List<Chaine> chaines){
        
        for (Chaine ch : chaines){
            System.out.println(ch.toString());
            System.out.println("----------------------");
        }
    }
}
