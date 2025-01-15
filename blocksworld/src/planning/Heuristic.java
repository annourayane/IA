package planning;

import java.util.Map;
import modelling.Variable;

public interface Heuristic {

    /**
     * Estime le coût d'un plan optimal depuis l'état donné jusqu'au but.
     * 
     * @param state L'état actuel représenté par une Map de Variables et leurs valeurs.
     * @return L'estimation (float) du coût restant pour atteindre le but depuis cet état.
     */
    float estimate(Map<Variable, Object> state);
}
