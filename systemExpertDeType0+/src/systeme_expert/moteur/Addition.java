package systeme_expert.moteur;
public class Addition extends ExpressionArithmetique {
    private ExpressionArithmetique gauche;
    private ExpressionArithmetique droite;

    public Addition(ExpressionArithmetique gauche, ExpressionArithmetique droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    @Override
    public double evaluer() {
        return gauche.evaluer() + droite.evaluer();
    }
    @Override
    public String toString(){
		return  this.gauche.evaluer() + " + " + this.droite.evaluer() ;
	}
    
    
    
    
    
}
