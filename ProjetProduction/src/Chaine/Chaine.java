/**
 * Classe de la chaine de production
 */
package Chaine;

import Elements.Element;
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
    private HashMap<String, Double> ElementE;
    private HashMap<String, Double> ElementS;
    private int NivActive;


    public Chaine(String CodeC, String nomC, HashMap<String, Double> ElementE, HashMap<String, Double> ElementS) {
        this.CodeC = CodeC;
        this.nomC = nomC;
        this.ElementE = ElementE;
        this.ElementS = ElementS;
        this.NivActive = 0;
    }
    
    public Chaine () {
        
    }
    public String getNomC(){
        return this.nomC;
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
    public HashMap<String, Double> getElementEntree(){
        return this.ElementE;
    }
    
    public HashMap<String, Double> getElementSortie(){
        return this.ElementS;
    }

    public void setCodeC(String CodeC) {
        this.CodeC = CodeC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public void setElementE(HashMap<String, Double> ElementE) {
        this.ElementE = ElementE;
    }

    public void setElementS(HashMap<String, Double> ElementS) {
        this.ElementS = ElementS;
    }


    public void setNivActive(int NivActive) {
        this.NivActive = NivActive;
    }
    
    
    /**
     * Retourne la quantité d'élément passé en paramètre 
     * utilisé pour la production de la Chaine 
     * @param e
     * @return 
     */
    public double getNbProduitUtiliser(Element e){
        double nbProduit = 0;
        Iterator<Map.Entry<String, Double>> ee = this.ElementE.entrySet().iterator();
        while(ee.hasNext()){
            Map.Entry<String, Double> a = (Map.Entry<String, Double>)ee.next();
            
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
    public double getNbProduitCreer(Element e){
        double nbProduit = 0;
        
        Iterator<Map.Entry<String, Double>> es = this.ElementS.entrySet().iterator();
        
        while(es.hasNext()){
            Map.Entry<String, Double> a = (Map.Entry<String, Double>)es.next();
            
            if(e.getCodeE().equals(a.getKey())){
                
                nbProduit = a.getValue()*this.NivActive;
            }
        }
        return nbProduit;
    }
    /**
     * Ecrire la liste des elements entrees sous forme:
     * (element,quantite)
     * @return 
     */
    public String EcireElementEntree(){
        String el = "";
        Iterator<Map.Entry<String, Double>> ee = this.ElementE.entrySet().iterator();
        while(ee.hasNext()){
            Map.Entry<String, Double> a = (Map.Entry<String, Double>)ee.next();
            el = el + "("+a.getKey()+","+a.getValue()+") ";
        }
        return el;
    }
    /**
     * Ecrire la liste des elements sorties sous forme:
     * (element,quantite)
     * @return 
     */
    public String EcireElementSortie(){
        String el = "";
        Iterator<Map.Entry<String, Double>> es = this.ElementS.entrySet().iterator();
        while(es.hasNext()){
            Map.Entry<String, Double> a = (Map.Entry<String, Double>)es.next();
            el = el + "("+a.getKey()+","+a.getValue()+") ";
        }
        return el;
    }
    public String toString() {
        String chaine = "";
        chaine = this.CodeC+" " +this.nomC+" /";
        Iterator<Map.Entry<String, Double>> ee = this.ElementE.entrySet().iterator();
        while(ee.hasNext()){
            Map.Entry<String, Double> a = (Map.Entry<String, Double>)ee.next();
            chaine = chaine +" "+a.getKey();
            String b = Double.toString(a.getValue());
            chaine = chaine +" "+b;    
        }  
        Iterator<Map.Entry<String, Double>> es = this.ElementS.entrySet().iterator();
        while(es.hasNext()){
            Map.Entry<String, Double> c = (Map.Entry<String, Double>)es.next();
            chaine = chaine+" /"+c.getKey();
            String d = Double.toString(c.getValue());
            chaine = chaine +" "+d;
        }
        
        
        return (chaine);
    }
}
