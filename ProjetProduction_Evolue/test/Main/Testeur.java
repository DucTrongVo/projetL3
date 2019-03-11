/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Operation.Calcul;
import Chaine.Chaine;
import Elements.Element;
import Gestion_Chaine.Usine;
import Interface.AfficherChaine;
import Gestion_Element.Stock;
import Historique.AnneeProduction;
import Historique.SemaineProduction;
import Operation.Charger;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toMap;



/**
 *
 * @author trongvo
 */
public class Testeur {
     public static void main(String[] args){
         
        Charger cs = new Charger(); 
        Calcul calcul = new Calcul(); 
        Stock stock =new Stock();
        List<Element> l = cs.chargerElement(stock);
        //stock.afficherListe();


        Usine usine = new Usine();
        List<Chaine> chtest = cs.chargerChaine(usine);
        for(Chaine c:chtest){
            System.out.println(c.getNomC());
        }
        //usine.afficheChaine();
        AnneeProduction ap = new AnneeProduction();
        //ArrayList<SemaineProduction> sp = ap.chargerHistoire();
        
        /*ap.ajouterSemaine(sp1);
        ap.ajouterSemaine(sp2);
        Resultat r = new Resultat(usine.getChaineparCode("C001"),10,100);
        
        sp1.addResultat(r);
        
        ap.afficheSemaine();*/
        
        /*HashMap<String,Double> k = new HashMap<>();
        k.put("mot",1.0);
        k.put("Nam", 5.0);
        k.put("ba", 3.0);
        k.put("bon", 4.0);
        k.put("hai", 2.0);
        
        System.out.println("avant");
        Iterator<Map.Entry<String, Double>> top = k.entrySet().iterator();
        while(top.hasNext()){
            Map.Entry<String, Double> a = (Map.Entry<String, Double>)top.next();
            System.out.println(a.getKey()+":"+a.getValue());
        }
     
        HashMap<String,Double> sorted;
        sorted = k.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(
            toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
        
        
        System.out.println("apres");       
        Iterator<Map.Entry<String, Double>> top2 = sorted.entrySet().iterator();
        while(top2.hasNext()){
            Map.Entry<String, Double> a = (Map.Entry<String, Double>)top2.next();
            System.out.println(a.getKey()+":"+a.getValue());
        }
       /* Scanner sc = new Scanner(System.in);
        System.out.println("Saisir la chaine de production souhaité:");
        String nomchaine = sc.nextLine();
        System.out.println("Saisir le niveau d'actionvation:");
        int nivAc = sc.nextInt();
        
        
        Chaine chaine_temp = usine.getChaineparCode(nomchaine);
        chaine_temp.setNiveauActive(nivAc);        
        double res = calcul.efficacite(chaine_temp, stock);
    
        if(res == 0){
            System.out.println("Production impossible!");
        }
        else {
            System.out.println("L'efficace de la production est: "+res);           
        }
       
        stock.afficherListe();
       HashMap<String, Double> ElementE = new HashMap<>();
       ElementE.put("E001", 15.0);
       ElementE.put("E002", 16.0);
       HashMap<String, Double> ElementS = new HashMap<>();
       ElementS.put("E004", 15.0);
       
       Chaine c = new Chaine("CodeC", "nomC", ElementE, ElementS);
       
       usine.addChaine(c);
       usine.afficheChaine();*/
    }
}
