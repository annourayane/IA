package systeme_expert.moteur;

import java.util.HashMap;

public class BaseDeFaits {
	private HashMap<String, Fait> dictionnaire;

    
	
	public BaseDeFaits() {
		dictionnaire = new HashMap<String, Fait>();
	}


    /**
     * Méthode pour rechercher un fait dans la base des faits
     * @param nom   est le symbole du fait .
     * @return  le fait qui son symbole correspond au parametre nom
   */
	
	public Fait rechercherFait(String nom){
		return this.dictionnaire.get(nom);
	}

	    /**
     * Méthode pour verifier l'existance d'un  fait dans la base des faits
     * @param nom   est le symbole du fait .
     * @return true si le fait existe faus sinon
   */
	public boolean existe(String nom){
		return this.dictionnaire.containsKey(nom);
	}
    /**
     * Methode pour ajouter un fait a la base des faits
     * 
     * @param nom et le nom du fait .
     * @param fait et le fait lui meme.
    */
	
	public void ajouterFait(String nom, Fait fait){
		this.dictionnaire.put(nom, fait);
	}

    /**
     * Methode pour obtenir la valeur reel d'un fait entier de la base des faits
     * @param nom et le nom du fait (le symbole) .
     * @return la valeur du fait entier .

    */

	public double getEntier(String nom) {
    if (dictionnaire.containsKey(nom)) {
        Fait fait = dictionnaire.get(nom);
        if (fait instanceof FaitEntier) {
            //System.out.println(fait);
            return ((FaitEntier) fait).getValeur();
        }
    }
    else{
        System.out.println("Fact not found");
    }
    // Gérer le cas où le fait n'existe pas ou n'est pas un fait entier
    return 0; //  on retourne 0 si c'est le  cas
	}


    /**
     * Methode pour obtenir la valeur booleen d'un fait booleen de la base des faits
     * @param nom et le nom du fait (le symbole) .
     * @return la valeur du fait booleen .

    */

	public boolean getBooleen(String nom) {
    Fait fait = rechercherFait(nom);
    if (fait instanceof FaitBooleen) {
        return ((FaitBooleen) fait).getValeur();
    } else {
        // Gerer le cas ou le fait n'est pas de type booleen
        return false; 
    }
	}


    /**
     * Methode pour obtenir la valeur symbolique d'un fait symbolique de la base des faits
     * @param nom et le nom du fait (le symbole) .
     * @return la valeur du fait symbolique .

    */

    public String getSymbole(String nom) {
    Fait fait = rechercherFait(nom);
    if (fait instanceof FaitSymbolique) {
        return ((FaitSymbolique) fait).getValeur();
    } else {
        // Gerer le cas ou le fait n'est pas de type symbole
        return null;
    }
}



    /**
     * Méthode toString pour afficher la regle 
     * @return est la chaine de caractere qui represente l'affichage de la base des faits
 
   */
	
	public String toString(){
		String affichage = "Base De Fait:";
		for(Fait fait : this.dictionnaire.values() ){
			affichage += "\n\t" + fait.toString();
		}
		return affichage;
	}

}