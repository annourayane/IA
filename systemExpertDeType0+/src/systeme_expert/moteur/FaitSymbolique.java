package systeme_expert.moteur;


public class FaitSymbolique extends Fait<String> {
	protected String valeur;
	
	public FaitSymbolique(String symbole,String valeur){
		super(symbole);
		this.valeur = valeur;
	}

	public FaitSymbolique(String symbole){
		super(symbole);
		this.valeur = "";
	}
        @Override
	public String getValeur() {
		return this.valeur;
	}

	public String getFaitType(){
        return "Fait Symbolique";
    }

	@Override
	 public String toString() {
		return this.symbole + "(" + this.valeur + ");";
	 }

}
