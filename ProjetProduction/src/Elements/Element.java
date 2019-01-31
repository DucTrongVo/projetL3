/**
 * Classe mère qui représente tout les matières qui servent à la production
 * et des produits
 */
package Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class Element {
    protected String CodeE;
    protected String nomE;
    protected int QuantiteE;
    protected String UMesure;
    protected int prixAchat;
    protected int prixVente;
    
    
    public Element(){
        
    }

    public Element(String CodeE, String nomE, int QuantiteE, String UMesure, int prixAchat, int prixVente) {
        this.CodeE = CodeE;
        this.nomE = nomE;
        this.QuantiteE = QuantiteE;
        this.UMesure = UMesure;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
    }
    
    
    /**
     * Fonction qui fixe la valeur du CodeE avec 
     * le valeur passer en paramètre
     * @param CodeE Le code de l'élément à fixé
     * @return Nouveau valeur du CodeE correspond au valeur passé en paramètre
     */
    public String setCodeE(String CodeE){
        return this.CodeE = CodeE;
    }
    
    /**
     * Fonction qui fixe la valeur du nomE avec 
     * le valeur passer en paramètre
     * @param nomE Le nom de l'élément à fixé
     * @return Nouveau valeur du nomE correspond au valeur passé en paramètre
     */
    public String setnomE(String nomE){
        return this.nomE = nomE;
    }
    
    /**
     * Fonction qui fixe la valeur du quantité avec 
     * le valeur passer en paramètre
     * @param quantite la quantité à fixé
     * @return Nouveau valeur du quantiteE correspond au valeur passé en paramètre
     */
    public int setQuantiteE(int quantite){
        return this.QuantiteE = quantite;
    }
    
    /**
     * Fonction qui fixe la valeur de l'unité de mesure avec 
     * le valeur passer en paramètre
     * @param umesure l'unité de mesure à fixé
     * @return Nouveau valeur du UMesure correspond au valeur passé en paramètre
     */
    public String setUniteMeusre(String umesure){
        return this.UMesure = umesure;
    }
    
    /**
     * Fonction qui fixe la valeur dprix d'achat avec 
     * le valeur passer en paramètre
     * @param prix prix d'achat à fixer
     * @return Nouveau valeur du prixAchat correspond au valeur passé en paramètre
     */
    public double setprixAchat(int prix){
        return this.prixAchat = prix;
    }
    
    /**
     * Fonction qui fixe la valeur drix de vendre avec 
     * le valeur passer en paramètre
     * @param prix prix de vendre à fixé
     * @return Nouveau valeur du prixVendre correspond au valeur passé en paramètre
     */
    public double setprixVente(int prix){
        return this.prixVente = prix;
    }

    public String getCodeE() {
        return CodeE;
    }

    public String getNomE() {
        return nomE;
    }

    public int getQuantiteE() {
        return QuantiteE;
    }

    public String getUMesure() {
        return UMesure;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public double getPrixVente() {
        return prixVente;
    }
    
    /**
     * Fonction permet d'écrire dans le fichier dont le nom passé en paramètre
     * les attributs de l'élémént
     * @param fichier le nom du fichier ou on veut ajouter l'élément (elements.csv)
     */
    public void ecrireElementListe(){
        Scanner sc = new Scanner(System.in);
        try (FileWriter writer = new FileWriter("element.csv",true)){
            writer.write(this.CodeE+";"+this.nomE+";"+this.QuantiteE+";"+this.UMesure+";"+this.prixAchat+";"+this.prixVente);          
        } catch (IOException ex) {
            Logger.getLogger(Element.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String toString() {
        return (this.CodeE+" "+this.nomE+" "+this.QuantiteE+" "+this.UMesure+" "+this.prixAchat+" "+this.prixVente);
    }
    
    
}


