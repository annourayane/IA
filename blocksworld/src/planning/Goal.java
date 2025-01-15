package planning; 
import modelling.Variable;
import java.util.Map;

public interface Goal {
    /**
     * Vérifie si un état donné satisfait l'objectif.
     * 
     * @param state un état représenté sous forme de Map où chaque Variable est associée à une valeur
     * @return true si l'état satisfait l'objectif, false sinon
     */
    boolean isSatisfiedBy(Map<Variable,Object> state);  
}