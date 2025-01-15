package planning; 
import modelling.Variable;
import java.util.Map;
public interface Action {
      /**
     * Vérifie si l'action est applicable dans un état donné.
     * 
     * @param state un état représenté sous forme de Map où chaque Variable est associée à une valeur
     * @return true si l'action est applicable, false sinon
     */
    public boolean isApplicable(Map<Variable, Object> state); //etat(clé valeur)

    /**
     * Applique l'action à un état donné et retourne le nouvel état.
     * 
     * @param state l'état actuel sous forme de Map
     * @return le nouvel état résultant de l'application de l'action (nouvelle instance)
     */
    public Map<Variable, Object> successor(Map<Variable, Object> state);

    /**
     * Retourne le coût de l'action.
     * 
     * @return le coût de l'action (un entier)
     */
    public int getCost();
}