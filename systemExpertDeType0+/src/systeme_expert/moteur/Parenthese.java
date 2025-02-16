package systeme_expert.moteur;
public class Parenthese extends ExpressionArithmetique {
    protected ExpressionArithmetique gauche;
    protected ExpressionArithmetique droite;
    protected ExpressionArithmetique expression;


    public Parenthese( ExpressionArithmetique expression) {
        this.expression = expression;
    }

    @Override
    public double evaluer() {
        return expression.evaluer();
    }
    
    @Override
    public String toString(){
    return "("+this.expression+")";
    }


}
