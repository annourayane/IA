package planning;
import modelling.Variable;
import java.util.HashMap;
import java.util.Map;

public class BasicAction implements Action {
    private Map<Variable, Object> precondition;
    private Map<Variable, Object> effect;
    private int cost;

  
    public BasicAction(Map<Variable, Object> precondition, Map<Variable, Object> effect, int cost) {
        this.precondition = precondition;
        this.effect = effect;
        this.cost = cost;
    }

    
   
   @Override
public boolean isApplicable(Map<Variable, Object> state) {
    for (Variable var : this.precondition.keySet()) {
        // Vérifier que la variable est présente dans l'état
        if (!state.containsKey(var)) {
            return false;
        }
        // Comparer les valeurs avec equals()
        if (!this.precondition.get(var).equals(state.get(var))) {
            return false;
        }
    }
    return true;
}



    

    // Méthode pour appliquer l'action et retourner le nouvel état
    @Override
    public Map<Variable, Object> successor(Map<Variable, Object> state) {
        // Crée une copie du state pour ne pas modifier l'état original
        Map<Variable, Object> newState = new HashMap<>(state);

        // Applique les effets de l'action dans le nouvel état
        for (Map.Entry<Variable, Object> entry : effect.entrySet()) {
            Variable variable = entry.getKey();
            Object newValue = entry.getValue();
            newState.put(variable, newValue);
        }

        return newState;
    }

    // Méthode pour obtenir le coût de l'action
    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "BasicAction{" +
                "precondition=" + precondition +
                ", effect=" + effect +
                ", cost=" + cost +
                '}';
    }
    
}
