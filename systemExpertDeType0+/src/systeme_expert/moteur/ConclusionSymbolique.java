package systeme_expert.moteur;
public class ConclusionSymbolique extends Conclusion<String> {

    private String valeur;

    
    /**
    * Constructeur de la conclusion
    * @param symbole  est le symbole de la conclusion .
    * @param valeur   est la valeur de cette conclusion .
    * 
    */
    public ConclusionSymbolique(String symbole, String valeur) {
        super(symbole); 
        this.valeur = valeur;
    }

    /**
    * Méthode pour obtenir la valeur de la conclusion symbolique
    * 
    */
    @Override
    public String getValeur() {
        return valeur;
    }


    /**
     * Methode pour ajouter la conclusion symbolique  a la bas des faits comme un fait symbolique
     * 
     * @param baseFaits La base de faits.
    */
    @Override
    public void ajouterAFaits(BaseDeFaits baseFaits) {
        // Créer un nouveau fait symbolique avec le symbole et la valeur de la conclusion
        FaitSymbolique fait = new FaitSymbolique(getSymbole(), valeur);
        // Ajouter le fait à la base de faits
        baseFaits.ajouterFait(getSymbole(), fait);
    }

    /**
     * Méthode toString pour obtenir une représentation textuelle de la conclusion
    */
    @Override
    public String toString() {
        return getSymbole() + " = " + getValeur();
    }
}
