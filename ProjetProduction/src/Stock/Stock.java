/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import Elements.Element;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Classe qui représente le stock de tout les
 * matière première et produit
 */
public class Stock {
    private static int nbElement = 0;
    private static HashMap <Element, Integer> stock = new HashMap<Element,Integer>();
    
    
public void addElements(){
}
   

    
public String toString(){
    return("");
}    
    
    public void ajouterStock(Element e, int quantite){
        this.stock.put(e, quantite);
        this.nbElement++;
    }

    public static int getNbElement() {
        return nbElement;
    }

    public static HashMap<Element, Integer> getStock() {
        return stock;
    }
    
    
    public void lireElement()
    {
        try (BufferedReader br = new BufferedReader(new FileReader("elements.csv"))){
            String line;
            while((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line,";");
                while(st.hasMoreTokens()) {
                    for(int i=0;i<6;i++){
                        st.nextToken();
                    }
                    Element el = new Element();
                    el.setCodeE(st.nextToken());
                    el.setnomE(st.nextToken());
                    el.setQuantiteE(Integer.parseInt(st.nextToken()));
                    el.setUniteMeusre(st.nextToken());
                    el.setprixAchat(Double.parseDouble(st.nextToken()));
                    el.setprixVente(Double.parseDouble(st.nextToken()));
                    this.stock.put(el, el.getQuantiteE());
                    
                }
            }
          
        } catch (IOException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficheStock()
    {
        Iterator iterator = stock.entrySet().iterator();
        while (iterator.hasNext()) {
          Map.Entry mapentry = (Map.Entry) iterator.next();
          System.out.println("Element: "+mapentry.getKey()
                            + " | valeur: " + mapentry.getValue());
        } 
    }
    
}
