package systeme_expert.moteur;

/**
 * 
 * Classe representant une valeur dans une  expression arithmetique   dans un fait entier.
 */
public class Valeur extends ExpressionArithmetique {

    private double valeur;

    /**
      * Constructeur de la valeur
      * @param valeur est  la valeur d'une feuille d'une expression arithmetique.
    */
    public Valeur(double valeur) {
        this.valeur = valeur;
    }

    @Override
    public double evaluer() {
        return this.valeur;
    } 
    
        @Override
    public String toString(){
		return  "" + this.valeur ;
	}
} 
