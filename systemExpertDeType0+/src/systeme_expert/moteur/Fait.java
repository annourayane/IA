package systeme_expert.moteur;
/**
 * 
 
 * Classe Generique representant un fait dans la base des faits.
 * @param <E> est la valeur du  fait  Booleenn,Entiee ,Symbolique
 */
public abstract class Fait<E> {
    protected String symbole;
   

    /**
    * Constructeur du  fait 
    * @param symbole  est le symbole du fait .
    */
    public Fait(String symbole) {
        this.symbole = symbole;
        
    }

    // Méthodes getters et setters
    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }
     /**
     * Méthode abstraite pour obtenir la valeur du fait 

   */

    public  abstract E getValeur();

 

    //Recuperer le type du fait (booleen, entier ou symbolique)
    abstract public String getFaitType();





}
