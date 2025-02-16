package systeme_expert.moteur;

public class FaitEntier extends Fait <Double> {
    protected ExpressionArithmetique valeur;

    /**
    * Constructeur du fait 
    * @param symbole  est le symbole du fait  .
    * @param valeur   est la valeur de ce fait  .
    * 
    */
    public FaitEntier(String symbole, ExpressionArithmetique valeur) {
        super(symbole);
        this.valeur = valeur;
    }

    /**
    *  autre Constructeur du fait 
    * @param symbole  est le symbole du fait  .
    * @param valeur   est la valeur de ce fait initiliser a null   .
    * 
    */

    public FaitEntier(String symbole) {
        super(symbole);
        this.valeur = null;
    }

    /**
    * Méthode pour obtenir la valeur du fait  booléenn
    */
    @Override
    public Double getValeur() {
		return this.valeur.evaluer();
	}


    
    public String getFaitType(){
        return "Fait Entier";
    }


    // toString
    @Override
    public String toString() {
        return "FaitEntier{" +
                "symbole='" + symbole + '\'' +
                ", valeur=" + valeur.evaluer() +
                '}';
    }
}
