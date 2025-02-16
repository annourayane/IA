package systeme_expert.grammaire;
import systeme_expert.moteur.Fait;
import systeme_expert.moteur.FaitBooleen;
import systeme_expert.moteur.FaitEntier;
import systeme_expert.moteur.FaitSymbolique;
import java.util.HashMap;
import java.io.IOException;
import java.util.Map;

public class Declaration implements EstFait{
	//Attributs
	private Analyseur contenu;
	private Variable varAct;
	private HashMap<String,Fait> faitsDeclares;

	/** Constructeur
    @requires contenu != null
    @ensures faitsDeclares != null
    */
	public Declaration(Analyseur contenu) throws IOException{
		this.contenu = contenu;
		this.varAct = this.contenu.identifiantSuivant();
		this.faitsDeclares = new HashMap<>();
	}

	// Méthodes qui détectent si un bloc de texte est une déclaration (booléens, entiers ou symboliques)
    
    /**
    @ensures result = varAct.estFaitsBooleens() || varAct.estFaitsEntiers() || varAct.estFaitsSymboliques()
    @return true si le contenu lu et soit une declaration booleenne ou entiere ou symbolique et false sinon
    */
   	public boolean estDeclaration() throws IOException{
		return (estDeclarationBooleenne() && estDeclarationEntiere() && estDeclarationsSymbolique());
	}

	/**
    @ensures result = estFaitsBooleens() && varAct.estDeuxPoints() && estFaitsBooleens();
    @return true si le contenu lu est une declaraion booleenne 
    */
	public boolean estDeclarationBooleenne() throws IOException{
		if(!this.varAct.estFaitsBooleens()){
			return false;
		}

		this.varAct = this.contenu.identifiantSuivant();

		if(!this.varAct.estDeuxPoints()){
			return false;
		}
		this.varAct = this.contenu.identifiantSuivant();

		if(!this.estFaitsBooleens()){
			return false;
		}

		this.varAct = this.contenu.identifiantSuivant();
		return true;
	}

	/**
    @ensures result = estFaitsEntiers() && varAct.estDeuxPoints() && estFaitsEntiers();
    @return true si le contenu lu est une declaraion entiere 
    */
	public boolean estDeclarationEntiere() throws IOException{
		
		if(!this.varAct.estFaitsEntiers()){
			return false;
		}
		this.varAct = this.contenu.identifiantSuivant();

		if(!this.varAct.estDeuxPoints()){
			return false;
		}
		this.varAct = this.contenu.identifiantSuivant();

		if(!this.estFaitsEntiers()){
			return false;
		}

		this.varAct = this.contenu.identifiantSuivant();

		return true;
	}

	/**
    @ensures result = estFaitsSymbolique() && varAct.estDeuxPoints() && estFaitsSymbolique();
    @return true si le contenu lu est une declaraion symbolique 
    */
	public boolean estDeclarationsSymbolique() throws IOException{
		if(!this.varAct.estFaitsSymboliques()){
			return false;
		}

		this.varAct = this.contenu.identifiantSuivant();

		if(!this.varAct.estDeuxPoints()){
			return false;
		}

		this.varAct = this.contenu.identifiantSuivant();

		if(!this.estFaitsSymboliques()){
			return false;
		}
		return true;
	}

	/** Méthodes qui verifie si le mot détecté est un fait et si oui, elle l'ajoute à l'ensemble des faits déclarés
	 
    @return true si le contenu lu est un fait Booleen, entier ou symbolique  et false sinon 
    */
   	public boolean estFaitBooleen() throws IOException{
		if(!this.varAct.estFait()){
			return false;
		}

		if(this.faitsDeclares.containsKey(this.varAct.getRepresentaion())){
			System.err.println("Erreur: " + this.varAct.getRepresentaion() + " existe deja dans l'ensemble des faits declares!");
			System.exit(1);
		}
		else{
			this.faitsDeclares.put(this.varAct.getRepresentaion(),new FaitBooleen(this.varAct.getRepresentaion()));
		}

		this.varAct = this.contenu.identifiantSuivant();
		return true;
	}

	public boolean estFaitEntier() throws IOException{
		if(!this.varAct.estFait()){
			return false;
		}

		if(this.faitsDeclares.containsKey(this.varAct.getRepresentaion())){
			System.err.println("Erreur: " + this.varAct.getRepresentaion() + " existe deja dans l'ensemble des faits declares!");
			System.exit(1);
		}
		else{
			this.faitsDeclares.put(this.varAct.getRepresentaion(),new FaitEntier(this.varAct.getRepresentaion()));
		}

		this.varAct = this.contenu.identifiantSuivant();
		return true;
	}

	public boolean estFaitSymbolique() throws IOException{
		if(!this.varAct.estFait()){
			return false;
		}

		if(this.faitsDeclares.containsKey(this.varAct.getRepresentaion())){
			System.err.println("Erreur: " + this.varAct.getRepresentaion() + " existe deja dans l'ensemble des faits declares!");
			System.exit(1);
		}
		else{
			this.faitsDeclares.put(this.varAct.getRepresentaion(),new FaitSymbolique(this.varAct.getRepresentaion()));
		}

		this.varAct = this.contenu.identifiantSuivant();
		return true;
	}
	
	//Methodes qui detectes si l'ensemble de mots sont des faits (booleens,entiers ou symbolique)
	public boolean estFaitsBooleens() throws IOException{
		if(!this.estFaitBooleen()){
				return false;
			}

		while(this.varAct.estVirgule()){
			this.varAct = this.contenu.identifiantSuivant();
			if(!this.estFaitBooleen()){
				return false;
			}
		}
		return this.varAct.estPoint();
	}

	public boolean estFaitsEntiers() throws IOException{
		if(!this.estFaitEntier()){
				return false;
			}
			
		while(this.varAct.estVirgule()){
			this.varAct = this.contenu.identifiantSuivant();

			if(!this.estFaitEntier()){
				return false;
			}
		}

		return this.varAct.estPoint();
	}

	public boolean estFaitsSymboliques() throws IOException{
		if(!this.estFaitSymbolique()){
				return false;
			}
			
		while(this.varAct.estVirgule()){
			this.varAct = this.contenu.identifiantSuivant();

			if(!this.estFaitSymbolique()){
				return false;
			}
		}

		return this.varAct.estPoint();
	}

	/** Méthode qui retourne l'ensemble des faits déclarés
    @ensures result = faitDeclares
    @return faitDeclares
    */
	public HashMap<String,Fait> getFaitDeclaree(){
		return this.faitsDeclares;
	}
}
