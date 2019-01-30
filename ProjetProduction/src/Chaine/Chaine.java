/**
 * Classe de la chaine de production
 */
package Chaine;

import Elements.Element;
import java.util.HashMap;

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
    private HashMap<Element, Integer> ElementE;
    private HashMap<Element, Integer> ElementS;
    private int NivActive;


    public Chaine(String CodeC, String nomC) {
        this.CodeC = CodeC;
        this.nomC = nomC;
        this.NivActive = 0;
    }
    
    

}
