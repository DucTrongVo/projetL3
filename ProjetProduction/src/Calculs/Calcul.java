/**
 * Classe contient des fonctions qui servent à la 
 * calculation de la production
 */
package Calculs;

import Chaine.Chaine;
import Elements.Element;
import Gestion_Chaine.Usine;
import Stock.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Classe Calcul pour effectuer les variations sur le stock 
 * @author trongvo
 */
public class Calcul {
    
    
    /**
    *Méthode qui verifie que tous les éléments en entrer ont bien un prix d'achats
    * @ch chaine sur laquelle on veut savoir si la production est possible
    *@st Stock à partir du quel on va savoir si c'est possible
    */
    public boolean possibleProduction(Chaine ch, Stock st) {
        boolean possible = true;
        HashMap<String, Double> liste = ch.getElementEntree();
        
        int niv = ch.getNiveauActive();
        Iterator<Map.Entry<String, Double>> ee = liste.entrySet().iterator();
        while(ee.hasNext()){
            Map.Entry<String, Double> a = (Map.Entry<String, Double>)ee.next();
            if(st.getElementparCode(a.getKey()).getPrixAchat() == 0){
                possible = false;
            } 
        }     
        return possible;
    }
    
     /**
    *Constructeur de la classe Calcul
    * 
    */
    
    public Calcul(){
        
    }
    
   
    
     /**
    *
    * Méthode pour calculer l'efficacite d'une chaine c passer en parametre a partir des prix de ventes et d'achats prensent dans le Stock st passer en parametre
    *
    */
    
    public double efficacite(Chaine c, Stock st){
        double efficacite = 0;
        
        if (this.possibleProduction(c,st)){
            double valeurAcheter = 0, valeurVente = 0;
            String CodeE = "";
            Element e = null;
            
            //Parcours de l'element d'entree
            Iterator<Map.Entry<String, Double>> ee = c.getElementEntree().entrySet().iterator();
            while(ee.hasNext()){
                Map.Entry<String, Double> a = (Map.Entry<String, Double>)ee.next();
                e = st.getElementparCode(a.getKey());
                st.soustraireStock(e, c.getNbProduitUtiliser(e));
                valeurAcheter = valeurAcheter + c.getNbProduitUtiliser(e)*e.getPrixAchat();   
            }
               
            //Parcours de lelement sortie
            Iterator<Map.Entry<String, Double>> es = c.getElementSortie().entrySet().iterator();
            while(es.hasNext()){
                Map.Entry<String, Double> a = (Map.Entry<String, Double>)es.next();

                e = st.getElementparCode(a.getKey());
                st.additionStock(e, c.getNbProduitCreer(e));
                valeurVente = valeurVente + c.getNbProduitCreer(e)*e.getPrixVente();   
                }  
            st.rewriteCSV();
            efficacite = valeurVente - valeurAcheter;
        
        }
        return efficacite;
    }
    
    public double efficaciteNonchangeBase(Chaine c, Stock st){
        double efficacite = 0;
        Stock tmp = st;
        
        if (this.possibleProduction(c,tmp)){
            double valeurAcheter = 0, valeurVente = 0;
            String CodeE = "";
            Element e = null;
            Iterator<Map.Entry<String, Double>> ee = c.getElementEntree().entrySet().iterator();
            while(ee.hasNext()){
                Map.Entry<String, Double> a = (Map.Entry<String, Double>)ee.next();
                e = tmp.getElementparCode(a.getKey());
                tmp.soustraireStock(e, c.getNbProduitUtiliser(e));
                valeurAcheter = valeurAcheter + c.getNbProduitUtiliser(e)*e.getPrixAchat();   
            }

            Iterator<Map.Entry<String, Double>> es = c.getElementSortie().entrySet().iterator();
            while(es.hasNext()){
                Map.Entry<String, Double> a = (Map.Entry<String, Double>)es.next();

                e = tmp.getElementparCode(a.getKey());
                tmp.additionStock(e, c.getNbProduitCreer(e));
                valeurVente = valeurVente + c.getNbProduitCreer(e)*e.getPrixVente();   
                }  
            
            efficacite = valeurVente - valeurAcheter;
        
        }
        return efficacite;
    }
}
