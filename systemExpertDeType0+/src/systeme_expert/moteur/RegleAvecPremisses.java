package systeme_expert.moteur;
import java.util.ArrayList;


/**
 * 
 * Classe   qui represente une regle avec premisses dans la base des regles
 */



public class RegleAvecPremisses extends Regle {
    private ArrayList<Premisse> premisses;

    
     /**
    * Constructeur de la regle
    * @param conclusion  est la conclusion de cette regle
    * @param premisse  est la premisse de cette regle
    * 
    */
    public RegleAvecPremisses(Conclusion conclusion, ArrayList<Premisse> premisses) {
        super(conclusion);
        this.premisses = premisses;
    }


    /**
     * Méthode  pour vérifier si la règle est déclenchable
     * 
     * @param baseFaits La base de faits.
     * @return Vrai si la regle est declenche, faux sinon.
 
   */
    @Override
    public boolean estDeclenchable(BaseDeFaits baseFaits) {
        // Si la regle n'a pas de premisses, elle est toujours declenchable
        if (premisses == null || premisses.isEmpty()) {
            return true;
        }
        
        // Verifie si toutes les premisses sont verifiees
        for (Premisse premisse : premisses) {
            if (!premisse.estVerifiee(baseFaits)) {
                return false; // Si une premisse n'est pas verifiee, la regle n'est pas declenchable
            }
        }
        return true; 
    }

    /**
     * Méthode  pour retourne la liste des premisses de la regle 
     * @return liste des premisse de la regle 
 
   */
    @Override
    public ArrayList<Premisse> getPremisses(){
        return this.premisses;
    }


    /**
     * Méthode toString pour afficher la regle 
     * @return est la chaine de caractere qui represente l'affichage de la regle
 
   */
            
    @Override
    public String toString() {
        String result = "SI ";
        for (Premisse premisse : premisses) {
            result += premisse + " ";
        }
        result += "ALORS " + conclusion;
        return result;
    }


 


















}
