package systeme_expert.moteur;


/**
 * 
 
 * Classe Generique representant une  premisse d'une regle
 * @param <E> est la valeur de la premisse Booleenne,Entiere ,Symbolique
 */
public abstract class Premisse<E> {
    protected String symbole; 



    
        /**
         * Constructeur de la premisse
         * @param symbole  est le symbole de la  premissse.
         */
    public Premisse(String symbole) { 
        this.symbole = symbole;
    }
    /**
     * Méthode abstraite pour vérifier si la prémisse est vérifiée

   */

    public abstract boolean estVerifiee(BaseDeFaits baseFaits);
    /**
     * Méthode pour obtenir le symbole de la prémisse

   */
    public String getSymbole() {
        return symbole;
    }

    
    /**
     * Méthode toString pour obtenir une représentation textuelle de la premisse

   */
    @Override
    public String toString() {
        return symbole;
    }
        /**
     * Méthode abstraite pour pour convertir une prémisse vers un fait

   */
    public abstract Fait convert ();
}

