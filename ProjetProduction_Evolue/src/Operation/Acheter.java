/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operation;

import Elements.Element;
import Gestion_Achat.ListeAchat;
import Gestion_Element.Stock;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trongvo
 */
public class Acheter {
    
    public Acheter(){
        
    }
    
    public void acheterStock(Stock st,ListeAchat la){
        Iterator<Map.Entry<Element, Double>> es = la.getListeAchat().entrySet().iterator();
        while(es.hasNext()){
            Map.Entry<Element, Double> a = (Map.Entry<Element, Double>)es.next();
            st.additionStock(a.getKey(), a.getValue());
        }
    }
    
    
    
   
}
