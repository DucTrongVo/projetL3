/**
 * Classe de la chaine de production
 */
package Chaine;

import Elements.Element;
import static Stock.CsvFileHelper.readCsvFile;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static javafx.scene.input.KeyCode.SEPARATOR;

/**
 *
 * @author trongvo 
 * @CodeC Le code de la chaîne 
 * @nomC Nom de la chaîne
 * @entree[] Les elements entree dans la chaîne
 * @QuantiteE[] Quantite de chaque objets
 * @indiceEntree L'indice pour manipuler le tableau d'elements entre dans la chaine
 * @sortie[] Les elements produit dans la chaine 
 * @indiceSortie indice pour manipuler le tableau de sortie 
 * @QuantiteS[] La quantite de chaque elements en sortie
 * @NivActive Le niveau d'activation d'une chaine
 */
public class Chaine {
    private String CodeC;
    private String nomC;
    private HashMap<String, Integer> ElementE;
    private HashMap<String, Integer> ElementS;
    private int NivActive;


    public Chaine(String CodeC, String nomC, HashMap<String, Integer> ElementE,HashMap<String, Integer> ElementS) {
        this.CodeC = CodeC;
        this.nomC = nomC;
        this.ElementE = ElementE;
        this.ElementS = ElementS;
        this.NivActive = 0;
    }
    
    public Chaine () {
        
    }
    
    
    public int setNiveauActive(int nb) {
        return this.NivActive = nb;
    }
    public String toString() {
        String chaine = "";
        chaine = this.CodeC+" " +this.nomC+" // ";
        for (String element : this.ElementE.keySet()) {
            String el = element.toString();
            String q = this.ElementE.get(el).toString();
            chaine = chaine +" "+ el+" "+q;
        }
        
        for (String element : this.ElementS.keySet()){
            String el = element.toString();
            String q = this.ElementS.get(el).toString();
            chaine = chaine +" // "+ el+" "+q;
        }
        
        return (chaine);
    }
}
