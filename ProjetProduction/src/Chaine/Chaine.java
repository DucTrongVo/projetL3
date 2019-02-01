/**
 * Classe de la chaine de production
 */
package Chaine;

import Elements.Element;
import static Stock.CsvFileHelper.readCsvFile;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static javafx.scene.input.KeyCode.SEPARATOR;

/**
 *
 * @author trongvo 
 * @CodeC Le code de la chaîne 
 * @nomC Nom de la chaîne
 * @entree[] Les elements entree dans la chaîne
 * @QuantiteE[] Quantite de chaque objets
 * @indiceEntree L'indice pour manipuler le tableau d'elements entre dans la chaine
 * @sortie[] Les elements produit dans la chaine 
 * @indiceSortie indice pour manipuler le tableau de sortie 
 * @QuantiteS[] La quantite de chaque elements en sortie
 * @NivActive Le niveau d'activation d'une chaine
 */
public class Chaine {
    private String CodeC;
    private String nomC;
    private HashMap<String, Integer> ElementE;
    private HashMap<String, Integer> ElementS;
    private int NivActive;


    public Chaine(String CodeC, String nomC, HashMap<String, Integer> ElementE, HashMap<String, Integer> ElementS) {
        this.CodeC = CodeC;
        this.nomC = nomC;
        this.ElementE = ElementE;
        this.ElementS = ElementS;
        this.NivActive = 0;
    }
    
    public Chaine () {
        
    }
    
    public String getCodeC(){
        return this.CodeC;
    }
    public int setNiveauActive(int nb) {
        return this.NivActive = nb;
    }
    
    public int getNiveauActive() {
        return this.NivActive;
    }
    public HashMap<String, Integer> getElementEntree(){
        return this.ElementE;
    }
    
    public HashMap<String, Integer> getElementSortie(){
        return this.ElementS;
    }
    
    
    /**
     * Retourne la quantité d'élément passé en paramètre 
     * utilisé pour la production de la Chaine 
     * @param e
     * @return 
     */
    public int getNbProduitUtiliser(Element e){
        int nbProduit = 0;
        Iterator<Map.Entry<String, Integer>> es = this.ElementE.entrySet().iterator();
        while(es.hasNext()){
            Map.Entry<String, Integer> a = (Map.Entry<String, Integer>)es.next();
            
            if(e.getCodeE().equals(a.getKey())){
                
                nbProduit = a.getValue()*this.NivActive;
            }
        }
        return nbProduit;
    }
    
    
    /**
     * Retourne la quantité d'élément passé en paramètre
     * crée apres l'utilisation de la chaine
     * @param e
     * @return 
     */
    public int getNbProduitCreer(Element e){
        int nbProduit = 0;
        Iterator<Map.Entry<String, Integer>> es = this.ElementS.entrySet().iterator();
        while(es.hasNext()){
            Map.Entry<String, Integer> a = (Map.Entry<String, Integer>)es.next();
            if(e.getCodeE().equals(a.getKey())){
                nbProduit = a.getValue()*this.NivActive;
            }
        }
        return nbProduit;
    }
    
    public String toString() {
        String chaine = "";
        chaine = this.CodeC+" " +this.nomC+" / ";
        Iterator<Map.Entry<String, Integer>> ee = this.ElementE.entrySet().iterator();
        while(ee.hasNext()){
            Map.Entry<String, Integer> a = (Map.Entry<String, Integer>)ee.next();
            chaine = chaine +" "+a.getKey();
            String b = Integer.toString(a.getValue());
            chaine = chaine +" "+b;    
        }  
        Iterator<Map.Entry<String, Integer>> es = this.ElementS.entrySet().iterator();
        while(es.hasNext()){
            Map.Entry<String, Integer> c = (Map.Entry<String, Integer>)es.next();
            chaine = chaine+" / "+c.getKey();
            String d = Integer.toString(c.getValue());
            chaine = chaine +" "+d;
        }
        
        
        return (chaine);
    }
}
