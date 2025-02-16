package systeme_expert.grammaire;
import java.io.LineNumberReader;
import java.io.IOException;
public class Analyseur{
	//Attributs
	private LineNumberReader fichier;
	private int position;
	private String ligne;
	private Variable varAct;
	
	//Construceur
	public Analyseur(LineNumberReader fichier){
		this.fichier = fichier;
		this.ligne = "";
		this.position = 0;
		this.varAct = null;
	}
	
	/** Methode qui retourne l'identifiant suivant
    @ ensures varAct != null;
    @ ensures result == varAct;
    @ return  varAct, le prochain identifiant
    */	
    public Variable identifiantSuivant() throws IOException{
		if(!sautLigne()){
			//00
			this.varAct = InitVariable.getFDF();
			return this.varAct;
		}
		
		//Ignorer les espaces
		while(Character.isWhitespace(ligne.charAt(position))){
			position++;
			if(!sautLigne()){
				this.varAct = InitVariable.getFDF();
				return this.varAct;
			}
		}
		
		//Caractere actuel
		char c = ligne.charAt(position);
		//Caractere suivant
		char cs = 0;
		if(ligne.length() >= position+2){
			cs = ligne.charAt(position+1);
		}
		
		//Detecter le type du caractere lu
		switch (c){
		case '+': 
			position ++;
			this.varAct = InitVariable.getOpAddition();
			break;
		case '-': 
			position ++;
			this.varAct = InitVariable.getOpSoustraction();
			break;
		case '*': 
			position ++;
			this.varAct = InitVariable.getOpMultiplication();
			break;
		case '/': 
			position ++;
			this.varAct = InitVariable.getOpDivision();
			break;
		case '(': 
			position ++;
			this.varAct = InitVariable.getParentheseOuvrante();
			break;
		case ')': 
			position ++;
			this.varAct = InitVariable.getParentheseFermante();
			break;
		case '&':
			position ++;
			this.varAct = InitVariable.getEt();
			break;
		case '|':
			position ++;
			this.varAct = InitVariable.getOu();
			break;
		case '.': 
			position ++;
			this.varAct = InitVariable.getPoint();
			break;
		case ',': 
			position ++;
			this.varAct = InitVariable.getVirgule();
			break;
		case ':':
			position ++;
			this.varAct = InitVariable.getDeuxPoints();
			break;
		case '~':
			position ++;
			this.varAct = InitVariable.getSoit();
			break;
		case '#':
			position ++;
			this.varAct = InitVariable.getFDF();
			break;		
		//Caracteres composes 
		case '>':
			if(cs=='>'){
				position += 2;
				this.varAct = InitVariable.getSi();	
			}
			else if(cs=='='){
				position += 2;
				this.varAct = InitVariable.getSuperieurEgale();
			}
			else{
				position ++;
				this.varAct = InitVariable.getSuperieur();
			}
			break;
		case '<': 
			if(cs=='='){
				position += 2;
				this.varAct = InitVariable.getInferieurEgale();
			}
			else{
				position ++;
				this.varAct = InitVariable.getInferieur();
			}
			break;
		case '!':
			if(cs=='='){
				position += 2;
				this.varAct = InitVariable.getDifferent();
			}
			else{
				position ++;
				this.varAct = InitVariable.getNon();
			}
			break;
		case '=':
			char css = ligne.charAt(position+2);
			if(cs=='=' && css=='>'){
				position += 3;
				this.varAct = InitVariable.getAlors();
			}
			else{
				position ++;
				this.varAct = InitVariable.getEgale();
			}
			break;
		default :
			//En cas de chiffre
			if(Character.isDigit(c)){
				this.varAct = InitVariable.getChiffrePositif(c);
				position++;
				break;
			}
			//Pour rappele un Fait doit toujours debuter par une lettre 
			//En cas de fait booleen
			if(ligne.length() >= position + "Faits_booleens".length()){
				if(ligne.substring(position,position+"Faits_booleens".length()).equals("Faits_booleens")){
					this.varAct = InitVariable.getFaitsBooleens("Faits_booleens");
					position += "Faits_booleens".length();
					break;
				}
			}
			//En cas de fait entier
			if(ligne.length() >= position + "Faits_entiers".length()){
				if(ligne.substring(position,position+"Faits_entiers".length()).equals("Faits_entiers")){
					this.varAct = InitVariable.getFaitsEntiers("Faits_entiers");
					position += "Faits_entiers".length();
					break;
				}
			}
			//En cas de fait symbolique
			if(ligne.length() >= position + "Faits_symboliques".length()){
				if(ligne.substring(position,position+"Faits_symboliques".length()).equals("Faits_symboliques")){
					this.varAct = InitVariable.getFaitsSymboliques("Faits_symboliques");
					position += "Faits_symboliques".length();
					break;
				}
			}
			//En cas de fait
			if(Character.isLetter(c)){
				this.varAct = recupererFait();
				break;
			}

			else{
				//En cas de caractere non reconnu
				System.err.println("Erreur de Syntaxe: caractere non reconnu");
				this.varAct = InitVariable.getInconnu(Character.toString(c));
			}
		}
		return this.varAct;
	}
	
	/** Vérification de fin du fichier + saut de ligne
    @ ensures result == true ==> (ligne != null && position == 0)
    @ return false si ligne==null (fin du fichier) et false sinon
    */	
    public boolean sautLigne() throws IOException{
		while(true){
			if(this.position >= this.ligne.length()){
				this.ligne = this.fichier.readLine();
				this.position = 0;
				
				if(this.ligne == null)
					return false;		
			}
			else{
				return true;
			}
		}
	}	

	/**Récupérer les expressions qui représentent des faits
    @ requires Character.isLetter(ligne.charAt(position))
    @ ensures \result != null
    @ return fait
    */	
   	public Variable recupererFait(){
		String fait = "";
		char cs = ligne.charAt(position);
		while(true){
			if(Character.isDigit(cs) || Character.isLetter(cs))
			{
				fait += cs;
				position ++;
				cs = ligne.charAt(position);
			}
			else{
				break;
			}
		}
		return InitVariable.getFait(fait);
	}

	//Getteur
	public Variable getVarAct(){ return this.varAct;}
}

