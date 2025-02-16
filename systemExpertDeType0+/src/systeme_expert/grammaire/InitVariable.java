package systeme_expert.grammaire;
public class InitVariable{
	//Creation des variables
	private static Variable si = new Variable (Type.si,">>");
	private static Variable alors = new Variable (Type.alors,"==>");
	private static Variable et = new Variable (Type.et,"&");
	private static Variable ou = new Variable (Type.ou,"|");
	private static Variable non = new Variable (Type.non,"!");
	private static Variable faitsEntiers = new Variable (Type.faitsEntiers,"Faits_entiers");
	private static Variable faitsSymboliques = new Variable (Type.faitsSymboliques,"Faits_symboliques");
	private static Variable faitsBooleens = new Variable (Type.faitsBooleens,"Faits_booleens");
	private static Variable FDF = new Variable (Type.FDF,"#");
	private static Variable superieur = new Variable (Type.superieur,">");
	private static Variable superieurEgale = new Variable (Type.superieurEgale,">=");
	private static Variable inferieur = new Variable (Type.inferieur,"<");
	private static Variable inferieurEgale = new Variable (Type.inferieurEgale,"<=");
	private static Variable egale = new Variable (Type.egale,"=");
	private static Variable different = new Variable (Type.different,"!=");
	private static Variable parentheseOuvrante = new Variable (Type.parentheseOuvrante,"(");
	private static Variable parentheseFermante = new Variable (Type.parentheseFermante,")");
	private static Variable opAdddition = new Variable (Type.opAdddition,"+");
	private static Variable opSoustraction = new Variable (Type.opSoustraction,"-");
	private static Variable opMultiplication = new Variable (Type.opMultiplication,"*");
	private static Variable opDivision = new Variable (Type.opDivision,"/");
	private static Variable point = new Variable (Type.point,".");
	private static Variable deuxPoints = new Variable (Type.deuxPoints,":");
	private static Variable virgule = new Variable (Type.virgule,",");
	private static Variable soit = new Variable (Type.soit,"~");
	
	
	//Getteurs
	public static Variable getSi(){ return si;}
	public static Variable getAlors(){ return alors;}
	public static Variable getEt(){ return et;}
	public static Variable getOu(){ return ou;}
	public static Variable getNon(){ return non;}
	public static Variable getFaitsEntiers(){ return faitsEntiers;}
	public static Variable getFaitsSymboliques(){ return faitsSymboliques;}
	public static Variable getFaitsBooleens(){ return faitsBooleens;}
	public static Variable getFDF(){ return FDF;}
	public static Variable getSuperieur(){ return superieur;}
	public static Variable getSuperieurEgale(){ return superieurEgale;}
	public static Variable getInferieur(){ return inferieur;}
	public static Variable getInferieurEgale(){ return inferieurEgale;}
	public static Variable getEgale(){ return egale;}
	public static Variable getDifferent(){ return different;}
	public static Variable getParentheseOuvrante(){ return parentheseOuvrante;}
	public static Variable getParentheseFermante(){ return parentheseFermante;}
	public static Variable getOpAddition(){ return opAdddition;}
	public static Variable getOpSoustraction(){ return opSoustraction;}
	public static Variable getOpMultiplication(){ return opMultiplication;}
	public static Variable getOpDivision(){ return opDivision;}
	public static Variable getPoint(){ return point;}
	public static Variable getDeuxPoints(){ return deuxPoints;}
	public static Variable getVirgule(){ return virgule;}
	public static Variable getSoit(){ return soit;}

	/**
	 * Methode qui renvoie un caractere inconnu
	@param representation
	@requires representation != null
	@ensures result != null
	@return Variable(Type.inconnu, representation)
	 */ 
	public static Variable getInconnu(String representation){ return new Variable(Type.inconnu, representation); }
	
	/**
	 * Methode qui renvoie un fait 
	@requires representation != null
	@ensures result != null
	@return Variable(Type.fait, representation)
	 */ 
	public static Variable getFait(String representation){ return new Variable(Type.fait, representation); }
	public static Variable getFaitsBooleens(String representation){ return new Variable(Type.faitsBooleens, representation); }
	public static Variable getFaitsEntiers(String representation){ return new Variable(Type.faitsEntiers, representation); }
	public static Variable getFaitsSymboliques(String representation){ return new Variable(Type.faitsSymboliques, representation); }
	
	/**
	 * Methode qui renvoie un chiffre positif
	@param representation
	@requires representation != null
	@ensures result != null
	@return Variable(Type.chiffrePositif, representation)
	 */ 
	public static Variable getChiffrePositif(char representation){ 
		String strRepresentation = ""+representation;
		return new Variable(Type.chiffrePositif, strRepresentation); }
	
}
