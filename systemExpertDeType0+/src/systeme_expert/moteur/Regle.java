package systeme_expert.moteur;
import java.util.ArrayList;

/**
 * 
 * Classe abstraite representant une regle dans la base des regles
 */
public abstract class Regle {
   
    protected Conclusion conclusion;
    protected boolean activable;

        
        /**
         * Constructeur de la regle
         * @param conclusion  est la conclusion de cette regle
         */
    public Regle(Conclusion conclusion) {
        this.conclusion = conclusion;
        this.activable = false;
    }
        /**
     * Méthode abstraite pour vérifier si la règle est déclenchable
     * 
     * @param baseFaits La base de faits.
     * @return Vrai si la regle est declenche, faux sinon.
 
   */
    public abstract boolean estDeclenchable(BaseDeFaits baseFaits);
        /**
     * Méthode pour retourner l'attribut activable
     * @return  la valeur de l'attribut activable
 
   */

    public boolean estActivable(){
		return this.activable;
	}
     /**
     * Méthode pour modifer la valeur de  l'attribut activable
 
   */


    public void setActivable (boolean valeur) {
        this.activable = valeur ;
    }

    /**
     * methode pour executer une regle (elle l'ajoute a la base des faits )
     * 
     * @param baseFaits La base de faits.

   */

    public void executer(BaseDeFaits baseFaits) {
    if (estDeclenchable(baseFaits)) {
        // Ajouter la conclusion à la base de faits
        conclusion.ajouterAFaits(baseFaits);
    }
    }

    /**
     * Méthode get pour retourne la conclusion de la regle
     * @return  la conclusion de la regle 
 
   */


    public Conclusion getConclusion () {
        return this.conclusion;
    }


    /**
     * Méthode abstraite pour retourne la liste des premisses de la regle 
     * @return liste des premisse de la regle 
 
   */
    public abstract ArrayList<Premisse> getPremisses();

    /**
     * Méthode abstraite to string
 
   */

    @Override
    public abstract String toString();
}
