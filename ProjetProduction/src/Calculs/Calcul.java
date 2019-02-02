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
 *
 * @author trongvo
 */
public class Calcul {
    
    
    public boolean possibleProduction(Chaine ch) {
        boolean possible = true;
        HashMap<String, Integer> liste = ch.getElementEntree();
        
        int niv = ch.getNiveauActive();
        Iterator<Map.Entry<String, Integer>> ee = liste.entrySet().iterator();
        while(ee.hasNext()){
            Map.Entry<String, Integer> a = (Map.Entry<String, Integer>)ee.next();
            if (Objects.equals(a.getValue(), "NA")){
                possible = false;
            } 
        }     
        return possible;
    }
    
    public Calcul(){
        
    }
    
   
    

    
    public int efficacite(Chaine c, Stock st){
        int efficacite = 0;
        if (this.possibleProduction(c)){
            int valeurAcheter = 0, valeurVente = 0;
            String CodeE = "";
            Element e = null;
            if (this.possibleProduction(c)){
            Iterator<Map.Entry<String, Integer>> ee = c.getElementEntree().entrySet().iterator();
            while(ee.hasNext()){
                Map.Entry<String, Integer> a = (Map.Entry<String, Integer>)ee.next();
                e = st.getElementparCode(a.getKey());
                st.soustraireStock(e, c.getNbProduitUtiliser(e));
                valeurAcheter = valeurAcheter + c.getNbProduitUtiliser(e)*e.getPrixAchat();   
            }

            Iterator<Map.Entry<String, Integer>> es = c.getElementSortie().entrySet().iterator();
            while(es.hasNext()){
                Map.Entry<String, Integer> a = (Map.Entry<String, Integer>)es.next();

                e = st.getElementparCode(a.getKey());
                st.additionStock(e, c.getNbProduitCreer(e));
                valeurVente = valeurVente + c.getNbProduitCreer(e)*e.getPrixVente();   
                }  
            }
            efficacite = valeurVente - valeurAcheter;
        
        }
        return efficacite;
    }
}
