package systeme_expert.moteur;
public class ConclusionBooleenne extends Conclusion<Boolean> {

    private boolean valeur;

    /**
    * Constructeur de la conclusion
    * @param symbole  est le symbole de la conclusion .
    * @param valeur   est la valeur de cette conclusion .
    * 
    */
    public ConclusionBooleenne(String symbole, boolean valeur) { 
        super(symbole); 
        this.valeur = valeur;
    }

    /**
    * Méthode pour obtenir la valeur de la conclusion booléenne
    */
    @Override
    public Boolean getValeur() {
        return valeur;
    }
    /**
    * Méthode pour déterminer le type de la conclusion
    */
    
    public String getType() {
        if (getValeur()) {
            return "Affirmative";
        } else {
            return "Négative";
        }
    }

    /**
     * Methode pour ajouter la conclusion booleene  a la bas des faits comme un fait booleen
     * 
     * @param baseFaits La base de faits.
    */

        @Override
    public void ajouterAFaits(BaseDeFaits baseFaits) {
        // Créer un nouveau fait booléen avec le symbole et la valeur de la conclusion
        FaitBooleen fait = new FaitBooleen(getSymbole(), valeur);
        // Ajouter le fait à la base de faits
        baseFaits.ajouterFait(getSymbole(), fait);
    }
      
    /**
     * Méthode toString pour obtenir une représentation textuelle de la conclusion

   */
    
    @Override
    public String toString() {
        return getSymbole() + " est " + (valeur ? "vrai" : "faux"); 
    }
}
