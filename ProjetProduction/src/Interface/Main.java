/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Calculs.Calcul;
import Chaine.Chaine;
import Elements.Element;
import Gestion_Chaine.Usine;
import Stock.Stock;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;



/**
 *
 * @author trongvo
 */
public class Main {
     public static void main(String[] args){
         
        Calcul calcul = new Calcul(); 
        Stock ELEMENT =new Stock();
        List<Element> l = ELEMENT.findElement();
        ELEMENT.afficherListe();


        Usine usine = new Usine();
        List<Chaine> chtest = usine.findChaine();
        usine.afficheChaine();
     
       
     
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisir la chaine de production souhaité:");
        String nomchaine = sc.nextLine();
        System.out.println("Saisir le niveau d'actionvation:");
        int nivAc = sc.nextInt();
        
        
        Chaine chaine_temp = usine.getChaineparCode(nomchaine);
        chaine_temp.setNiveauActive(nivAc);        
        int res = calcul.efficacite(chaine_temp, ELEMENT);
    
        
        System.out.println(res);    
        System.out.println(res + 200);
    
    
    
    
    }
}
