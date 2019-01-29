/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import Elements.Element;
import java.util.HashMap;

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
    
    
    
}
