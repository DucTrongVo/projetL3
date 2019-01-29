/**
 * Classe de la chaine de production
 */
package Chaine;

import Elements.Element;

/**
 *
 * @author trongvo
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

    public Chaine(String CodeC, String nomC) {
        this.CodeC = CodeC;
        this.nomC = nomC;
        this.NivActive = 0;
    }
    
    
}
