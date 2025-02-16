package systeme_expert.moteur;
public class PremisseBooleenne extends Premisse {
    private boolean valeur;


    
    /**
    * Constructeur de la premisse
    * @param symbole  est le symbole de la premisse .
    * @param valeur   est la valeur de cette premisse .
    * 
    */
    public PremisseBooleenne(String symbole, boolean valeur) { 
        super(symbole);
        this.valeur = valeur;
    }
    /**
    * Méthode pour obtenir la valeur de la premisse booléenne
    */
  
    public boolean getValeur() {
        return valeur;
    }

    /**
    * Méthode pour vérifier si la prémisse booléenne est vérifiée dans la base de faits
    */
    
    @Override
    public boolean estVerifiee(BaseDeFaits baseFaits) {
        return baseFaits.existe(getSymbole()) && baseFaits.getBooleen(getSymbole()) == valeur;
    }

    /**
    * Méthode pour convertir une premisse booleen vers un fait boolean
    */
     @Override
     public  Fait convert () {
         return new FaitBooleen(super.getSymbole(),this.valeur);
     }



    /**
     * Méthode toString pour obtenir une représentation textuelle de la premisse

   */
    @Override
    public String toString() {
         return getSymbole() + " est " + (valeur ? "vrai" : "faux");
    }
}
