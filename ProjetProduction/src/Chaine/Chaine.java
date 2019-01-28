/**
 * Classe de la chaine de production
 */
package Chaine;

import Elements.Element;

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
    private Element[] entree;
    private int[] QuantiteE;
    private int indiceEntree;
    private Element[] sortie;
    private int indiceSortie;
    private int[] QuantiteS;
    private int NivActive;
}
