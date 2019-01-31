/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Chaine.Chaine;
import Elements.Element;
import Gestion_Chaine.ChaineTotal;
import Stock.Stock;
import java.util.List;



/**
 *
 * @author trongvo
 */
public class Main {
     public static void main(String[] args){
        
    /*Stock test =new Stock();
    List<Element> l = test.findElement();
    test.afficherListe(l);
*/

    ChaineTotal ch = new ChaineTotal();
    List<Chaine> chtest = ch.findChaine();
    
    ch.afficheChaine(chtest);
    
    
   
}
    
}
