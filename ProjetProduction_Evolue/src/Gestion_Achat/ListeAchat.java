/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion_Achat;

import Elements.Element;
import Gestion_Element.Stock;
import Operation.Calcul;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trongvo
 */
public class ListeAchat {
    private final static String RESOURCES_ACHAT_PATH = "/home/trongvo/NetBeansProjects/projetL3-master/ProjetProduction_Evolue/FilesCSV/Achat/";
    HashMap<Element,Double> listeAchat = new HashMap<>();
    
    public ListeAchat(){
        
    }
    
    public HashMap<Element,Double> getListeAchat(){
        return this.listeAchat;
    }
    
    
    public HashMap<Element,Double> etablirListeAchat(Stock st){
        for (Element e : st.getListStock()){
            if(e.getQuantiteE() < 0){
                double q = e.getQuantiteE()*(-1);
                this.listeAchat.put(e,q);
            }
        }
        this.ecrireListeAchat();
        return this.listeAchat;
    }
    
    public HashMap<Element,Double> ajouterAchat(Element e, double q){
        if(this.listeAchat.containsKey(e)){
            this.listeAchat.put(e, q+this.listeAchat.get(e));
        }
        else{
            this.listeAchat.put(e, q);
        }
        this.ecrireListeAchat();
        return this.listeAchat;
    }
    
    public HashMap<Element, Double> enleverAchat(Element e){
        this.listeAchat.remove(e);
        this.ecrireListeAchat();
        return this.listeAchat;
    }
    public void setQuantiteAchete(Element e, double q){
        Iterator<Map.Entry<Element, Double>> es = this.listeAchat.entrySet().iterator();
        while(es.hasNext()){
            Map.Entry<Element, Double> a = (Map.Entry<Element, Double>)es.next();
            if(a.getKey().equals(e)){
                this.listeAchat.put(e, q);
            }
        }
    }
    public void ecrireListeAchat(){
        try (FileWriter writer0 = new FileWriter(RESOURCES_ACHAT_PATH+"liste_achat.csv")){
			writer0.write("Code Element;Nom Element;Quantite Acheter;Mesure;Prix Achat\n");
                        
		} catch (IOException ex) { 
                Logger.getLogger(Calcul.class.getName()).log(Level.SEVERE, null, ex);
            }
        Iterator<Map.Entry<Element, Double>> es = this.listeAchat.entrySet().iterator();
        while(es.hasNext()){
            Map.Entry<Element, Double> a = (Map.Entry<Element, Double>)es.next();
            try (FileWriter writer = new FileWriter(RESOURCES_ACHAT_PATH+"liste_achat.csv",true)){
                writer.write(a.getKey().getCodeE()+";"+a.getKey().getNomE()+";"+a.getValue()+";"+a.getKey().getUMesure()+";"+a.getKey().getPrixAchat()*a.getValue()+"\n");
            }catch (IOException ex) {
                Logger.getLogger(Calcul.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
}
