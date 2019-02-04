/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Calculs.Calcul;
import Chaine.Chaine;
import Elements.Element;
import Gestion_Chaine.Usine;
import Interface.AfficherChaine;
import Stock.Stock;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author trongvo
 */
public class Main {
     public static void main(String[] args){
         
        Calcul calcul = new Calcul(); 
        Stock stock =new Stock();
        List<Element> l = stock.findElement();
        //stock.afficherListe();


        Usine usine = new Usine();
        List<Chaine> chtest = usine.findChaine();
        //usine.afficheChaine();
     
        
     
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
       
        stock.afficherListe();*/
       HashMap<String, Double> ElementE = new HashMap<>();
       ElementE.put("E001", 15.0);
       ElementE.put("E002", 16.0);
       HashMap<String, Double> ElementS = new HashMap<>();
       ElementS.put("E004", 15.0);
       
       Chaine c = new Chaine("CodeC", "nomC", ElementE, ElementS);
       
       usine.addChaine(c);
       usine.afficheChaine();
    }
}
