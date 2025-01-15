package planning;

import modelling.Variable;
import java.util.Map;
import java.util.HashMap;
public class BasicGoal implements Goal {
    private Map<Variable, Object> etatBut; // instanciation partielle du but

    
    public BasicGoal(Map<Variable, Object> etatBut) {
        this.etatBut = etatBut;
    }

    // Vérifie si un état donné satisfait le but
    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> etat) {
        // Parcourt chaque variable du but et vérifie que l'état associe la bonne valeur
        for (Map.Entry<Variable, Object> entree : etatBut.entrySet()) {
            Variable variable = entree.getKey();
            Object valeurAttendue = entree.getValue();

            // Si la variable n'est pas dans l'état ou n'a pas la valeur attendue, le but n'est pas satisfait
            if (!etat.containsKey(variable) || !etat.get(variable).equals(valeurAttendue)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "BasicGoal{" +
                "etatBut=" + etatBut +
                '}';
    }
    
}
