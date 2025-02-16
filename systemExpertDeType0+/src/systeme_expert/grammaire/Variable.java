package systeme_expert.grammaire;
public class Variable{
	//Attributs
	private Type type;
	private String representation;
	
	//Constructeur
	public Variable(Type type, String representation){
		this.type = type;
		this.representation = representation;	
	}
	
	//Accesseurs
	public Type getType(){ return this.type;}
	public String getRepresentaion(){ return this.representation;}
	
	//Verification du type
	public boolean estSi(){ return this.type == Type.si;}
	
	public boolean estAlors(){ return this.type == Type.alors;}
	
	public boolean estEt(){ return this.type == Type.et;}
	
	public boolean estOu(){ return this.type == Type.ou;}
	
	public boolean estNon(){ return this.type == Type.non;}
		
	public boolean estFaitsEntiers(){ return this.type == Type.faitsEntiers;}
	
	public boolean estFaitsSymboliques(){ return this.type == Type.faitsSymboliques;}
	
	public boolean estFaitsBooleens(){ return this.type == Type.faitsBooleens;}
	
	public boolean estFDF(){ return this.type == Type.FDF;}
	
	public boolean estSuperieur(){ return this.type == Type.superieur;}
	
	public boolean estSuperieurEgale(){ return this.type == Type.superieurEgale;}
	
	public boolean estInferieur(){ return this.type == Type.inferieur;}
	
	public boolean estInferieurEgale(){ return this.type == Type.inferieurEgale;}

	public boolean estEgale(){ return this.type == Type.egale;}

	public boolean estDifferent(){ return this.type == Type.different;}

	public boolean estParentheseOuvrante(){ return this.type == Type.parentheseOuvrante;}

	public boolean estParentheseFermante(){ return this.type == Type.parentheseFermante;}

	public boolean estOpAddition(){ return this.type == Type.opAdddition;}

	public boolean estOpSoustraction(){ return this.type == Type.opSoustraction;}

	public boolean estOpMultiplication(){ return this.type == Type.opMultiplication;}

	public boolean estOpDivision(){ return this.type == Type.opDivision;}

	public boolean estPoint(){ return this.type == Type.point;}

	public boolean estDeuxPoints(){ return this.type == Type.deuxPoints;}

	public boolean estFait(){ return this.type == Type.fait;}

	public boolean estChiffrePositif(){ return this.type == Type.chiffrePositif;}
	
	public boolean estVirgule(){ return this.type == Type.virgule;}

	public boolean estSoit(){ return this.type == Type.soit;}
	
	public boolean estInconnu(){ return this.type == Type.inconnu;}
	
	public boolean estSigneDeComparaison(){ return (this.estEgale() || this.estDifferent() || this.estSuperieur() || 
	this.estSuperieurEgale() || this.estInferieur() || this.estInferieurEgale());}

	public boolean estOperateurAdditif(){ return (this.estOpAddition() || this.estOpSoustraction());}

	public boolean estOperateurMultiplicatif(){ return (this.estOpMultiplication() || this.estOpDivision());}
}
