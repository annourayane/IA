package systeme_expert.moteur;

public class FaitBooleen extends Fait<Boolean> {
	private boolean valeur;

    /**
    * Constructeur du fait 
    * @param symbole  est le symbole du fait  .
    * @param valeur   est la valeur de ce fait  .
    * 
    */
	public FaitBooleen(String symbole,boolean valeur) {
		super(symbole);
		this.valeur = valeur;
		
	}


	/**
    *  autre Constructeur du fait 
    * @param symbole  est le symbole du fait  .
    * @param valeur   est la valeur de ce fait initiliser a false   .
    * 
    */
	public FaitBooleen(String symbole) {
		super(symbole);
		this.valeur = false;
		
	}
	/**
    * Méthode pour obtenir la valeur du fait  booléenn
    */
	@Override
	public Boolean getValeur() {
		return this.valeur;
	}

	public String getFaitType(){
        return "Fait Booleen";
    }

	@Override
	public String toString() {
    return "FaitBooleen{" +
            "symbole='" + symbole + '\'' +
            ", valeur=" + valeur +
            '}';
	}



}
