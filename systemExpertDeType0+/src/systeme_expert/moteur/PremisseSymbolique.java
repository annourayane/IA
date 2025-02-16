package systeme_expert.moteur;
public class PremisseSymbolique extends Premisse {
    private String valeur;

            /**
    * Constructeur de la premisse
    * @param symbole  est le symbole de la premisse .
    * @param valeur   est la valeur de cette premisse .
    * 
    */
    public PremisseSymbolique(String symbole, String valeur) { 
        super(symbole);
        this.valeur = valeur;
    }
    /**
    * Méthode pour vérifier si la prémisse symbolique est vérifiée dans la base de faits
    */
    
    @Override
    public boolean estVerifiee(BaseDeFaits baseFaits) {
        return baseFaits.existe(getSymbole()) && ((FaitSymbolique) baseFaits.rechercherFait(getSymbole())).getValeur().equals(valeur);
    }
    /**
    * Méthode pour convertir une premisse symbolique  vers un fait symbolique
    */

    @Override
     public  Fait convert () {
       return new FaitSymbolique(super.getSymbole(),this.valeur);
     }

    /**
     * Méthode toString pour obtenir une représentation textuelle de la premisse symbolique

   */
  
    @Override
    public String toString() {
        return getSymbole() + " = " + valeur;
    }
}
