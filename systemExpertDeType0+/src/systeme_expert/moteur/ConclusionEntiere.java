package systeme_expert.moteur;
public class ConclusionEntiere extends Conclusion<Double> {
    protected ExpressionArithmetique valeur;

  
    /**
    * Constructeur de la conclusion
    * @param symbole  est le symbole de la conclusion .
    * @param valeur   est la valeur de cette conclusion .
    * 
    */
    public ConclusionEntiere(String symbole,  ExpressionArithmetique valeur) { 
        super(symbole);
        this.valeur = valeur;
    }

    /**
    * Méthode pour obtenir la valeur de la conclusion entiere
    */
    @Override
    public Double getValeur() {
        return valeur.evaluer();
    }
    /**
     * Methode pour ajouter la conclusion entire a la bas des faits comme un fait entier 
     * 
     * @param baseFaits La base de faits.
    */
    @Override
    public void ajouterAFaits(BaseDeFaits baseFaits) {
        // Créer un nouveau fait entier avec le symbole et la valeur de la conclusion
        FaitEntier fait = new FaitEntier(getSymbole(), valeur);
        // Ajouter le fait à la base de faits
        baseFaits.ajouterFait(getSymbole(), fait);
    }

 






    /**
     * Méthode toString pour obtenir une représentation textuelle de la conclusion

    */
    @Override
    public String toString() {
        return getSymbole() + " est " + valeur;
    }
}

