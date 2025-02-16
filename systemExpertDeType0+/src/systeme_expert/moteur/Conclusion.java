package systeme_expert.moteur;

/**
 * 
 
 * Classe Generique representant une  conclusion d'une regle
 * @param <E> est la valeur de la conclusion Booleenne,Entiere ,Symbolique
 */

public abstract  class  Conclusion<E> {
    protected String symbole; 

    /**
      * Constructeur de la conclusion
      * @param symbole  est le symbole de la  conclusion.
    */
    public Conclusion(String symbole) { 
        this.symbole = symbole;
    }



    /**
     * Méthode pour obtenir le symbole de la conclusion
     * @return  la valeur du symbole
 
   */

    public String getSymbole() {
        return symbole;
    }

    /**
     * Méthode abstraite pour obtenir la valeur d'une conclusion 

   */

    public abstract E getValeur();
    

    /**
     * Methode abstraite   pour ajouter la conclusion a la bas des faits
     * 
     * @param baseFaits La base de faits.
   */
     public abstract void ajouterAFaits(BaseDeFaits baseFaits);

   


    /**
     * Méthode toString pour obtenir une représentation textuelle de la conclusion

   */
    
    @Override
    public String toString() {
        return symbole; 
    }
}
