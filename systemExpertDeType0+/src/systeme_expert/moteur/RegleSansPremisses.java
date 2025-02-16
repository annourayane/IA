package systeme_expert.moteur;
import java.util.ArrayList;



/**
 * 
 * Classe   qui represente une regle avec premisses dans la base des regles
 */


public class RegleSansPremisses extends Regle {
    /**
     * Constructeur de la regle
     * @param conclusion  est la conclusion de cette regle
    */
    public RegleSansPremisses(Conclusion conclusion) {
        super(conclusion);
    }

        /**
     * Méthode  pour vérifier si la règle est déclenchable
     * 
     * @param baseFaits La base de faits.
     * @return Vrai car tous les regle sans premisse sans verifier 
 
   */
    @Override
    public boolean estDeclenchable(BaseDeFaits baseFaits) {
        return true;
    }


     /**
     * Méthode  pour retourne la liste des premisses de la regle 
     * @return une liste vide car elle contient pas de premisse 
 
   */
    @Override
        public ArrayList<Premisse> getPremisses(){
        return new ArrayList<>();  // une liste vide mieux que null
    }


    /**
     * Méthode toString pour afficher la regle 
     * @return est la chaine de caractere qui represente l'affichage de la regle
 
   */
    @Override
    public String toString() {
        return "Regle sans premisses: " + conclusion;
    }
















}
