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
    protected double QuantiteE;
    protected String UMesure;
    protected double prixAchat;
    protected String prixAchat_str;
    protected double prixVente;
    protected String prixVente_str;
    
    
    public Element(){
        
    }
    /** 
     * COnstructeur de Element dont on connait tout les prix de vente et achat
     * @param CodeE
     * @param nomE
     * @param QuantiteE
     * @param UMesure
     * @param prixAchat
     * @param prixVente 
     */
    public Element(String CodeE, String nomE, double QuantiteE, String UMesure, double prixAchat, double prixVente) {
        this.CodeE = CodeE;
        this.nomE = nomE;
        this.QuantiteE = QuantiteE;
        this.UMesure = UMesure;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
    }
    /**
     * Constructeur dont le prix achat est inconnu
     * @param CodeE
     * @param nomE
     * @param QuantiteE
     * @param UMesure
     * @param prixAchat
     * @param prixVente 
     */
    public Element(String CodeE, String nomE, double QuantiteE, String UMesure, String prixAchat, double prixVente) {
        this.CodeE = CodeE;
        this.nomE = nomE;
        this.QuantiteE = QuantiteE;
        this.UMesure = UMesure;
        this.prixAchat_str = prixAchat;
        this.prixVente = prixVente;
        this.prixVente_str = null;
    }
    
    /**
     * Constructeur dont le prix de vente est inconnu
     * @param CodeE
     * @param nomE
     * @param QuantiteE
     * @param UMesure
     * @param prixAchat
     * @param prixVente 
     */
    public Element(String CodeE, String nomE, double QuantiteE, String UMesure, double prixAchat, String prixVente) {
        this.CodeE = CodeE;
        this.nomE = nomE;
        this.QuantiteE = QuantiteE;
        this.UMesure = UMesure;
        this.prixAchat = prixAchat;
        this.prixVente_str = prixVente;
        this.prixAchat_str = null;
    }
    /**
     * Constructeur dont le prix de vente et achat sont inconnus
     * @param CodeE
     * @param nomE
     * @param QuantiteE
     * @param UMesure
     * @param prixAchat
     * @param prixVente 
     */
    public Element(String CodeE, String nomE, double QuantiteE, String UMesure, String prixAchat, String prixVente) {
        this.CodeE = CodeE;
        this.nomE = nomE;
        this.QuantiteE = QuantiteE;
        this.UMesure = UMesure;
        this.prixAchat_str = prixAchat;
        this.prixVente_str = prixVente;
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
    public double setQuantiteE(double quantite){
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
    public double setprixAchat(double prix){
        return this.prixAchat = prix;
    }
    
    /**
     * Fonction qui fixe la valeur drix de vendre avec 
     * le valeur passer en paramètre
     * @param prix prix de vendre à fixé
     * @return Nouveau valeur du prixVendre correspond au valeur passé en paramètre
     */
    public double setprixVente(double prix){
        return this.prixVente = prix;
    }

    public String getCodeE() {
        return CodeE;
    }

    public String getNomE() {
        return nomE;
    }

    public double getQuantiteE() {
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
        String chaine = "";
        if((this.prixAchat_str == null) && (this.prixVente_str == null)){
            chaine = (this.CodeE+" "+this.nomE+" "+this.QuantiteE+" "+this.UMesure+" "+this.prixAchat+" "+this.prixVente);
        }
        if(((this.prixAchat_str == "NA")) && (this.prixVente_str == null)){
            chaine = (this.CodeE+" "+this.nomE+" "+this.QuantiteE+" "+this.UMesure+" "+this.prixAchat_str+" "+this.prixVente);
        }
        if((this.prixAchat_str == null) && (this.prixVente_str == "NA")){
            chaine = (this.CodeE+" "+this.nomE+" "+this.QuantiteE+" "+this.UMesure+" "+this.prixAchat+" "+this.prixVente_str);
        }
        
        if((this.prixAchat_str == "NA") && (this.prixVente_str == "NA")){
            chaine = (this.CodeE+" "+this.nomE+" "+this.QuantiteE+" "+this.UMesure+" "+this.prixAchat_str+" "+this.prixVente_str);
        }
        return chaine;
    }
    
    
}


