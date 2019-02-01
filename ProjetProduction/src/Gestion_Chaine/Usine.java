/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion_Chaine;

import Chaine.Chaine;
import static Stock.CsvFileHelper.readCsvFile;
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
 *
 * @author trongvo
 */
public class Usine {
    
    List<Chaine> Chaines;
    private final static String RESOURCES_PATH = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction/src/";
    private final static String RESOURCES_PATH_SUB = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction/";

    private final static String CHAINES_FILE_NAME = "chaines.csv";
    private final static String SOUS_ELEMENT_FILE_NAME ="sous_element.csv";
    private final static char SEPARATOR=';';
    private final static char SEPARATOR_SUB=',';
    
    
    public List<Chaine> getListeChaine(){
        return this.Chaines;
    }
    /** 
     * Permettre d'enregistrer les Chaines existants
     * dans le file chaines.csv
     * @return 
     */
    
    
    public Chaine getChaineparCode(String CodeC){
        Chaine c = null;
        for (Chaine iterator : this.Chaines){
        if (iterator.getCodeC().equals(CodeC)){
            c = iterator;
            }
        }
        return c;
    }
    public List<Chaine> findChaine() {

        final List<String[] > data = readCsvFile(RESOURCES_PATH + CHAINES_FILE_NAME, SEPARATOR);

        this.Chaines = dataToChaine(data);

        return Chaines;
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
    public HashMap<String, Integer> findElement_sub() {

        final List<String[] > data = readCsvFile(RESOURCES_PATH_SUB + SOUS_ELEMENT_FILE_NAME, SEPARATOR_SUB);
        HashMap<String, Integer> element = dataToHashMap(data);
        

        return element;
    }
    
    private List<Chaine> dataToChaine(List<String[]> data){

        final List<Chaine> chaines = new ArrayList<>();
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
     * écrire les sous-matière/sous-produit résultat dans 
     * un liste sous-element.csv
     * @param data 
     * @return 
     */
    public HashMap<String,Integer> dataToHashMap(List<String[]> data){
        HashMap<String,Integer> element = new HashMap<>();
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
                   
                    int quantite = Integer.parseInt(quantite_num);     
                    element.put(nomeE_pur,quantite);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Usine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return element;
    }
    
    public void afficheChaine(){
        
        for (Chaine ch : this.Chaines){
            System.out.println(ch.toString());
            System.out.println("----------------------");
        }
    }
}
