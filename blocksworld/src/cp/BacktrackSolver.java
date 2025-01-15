package cp;

import java.util.*;
import modelling.Constraint;  
import modelling.Variable;    

public class BacktrackSolver extends AbstractSolver {

    public BacktrackSolver(Set<Variable> variables, Set<Constraint> constraints) {
        super(variables, constraints);
    }

    @Override
    public Map<Variable, Object> solve() {
        // Lancer le backtracking avec une affectation vide et toutes les variables non assignées
        return backtrack(new HashMap<>(), new LinkedList<>(variables));
    }

    /**
     * Méthode récursive de backtracking
     * @param I L'affectation partielle actuelle des variables
     * @param V La liste des variables non assignées
     * @return Une solution complète si elle est trouvée, sinon null
     */
    public Map<Variable, Object> backtrack(Map<Variable, Object> I, LinkedList<Variable> V) {
        // Condition d'arrêt : si toutes les variables sont assignées, retourner l'affectation complète
        if (V.isEmpty()) {
            return I;
        }

        // Choisir une variable non encore instanciée
        Variable xi = V.removeFirst(); // Utiliser removeFirst() pour retirer la première variable

        // Choisir une valeur dans le domaine de xi
        for (Object vi : xi.getDomain()) {
            // Créer une nouvelle affectation partielle avec xi assignée à vi
            Map<Variable, Object> N = new HashMap<>(I);
            N.put(xi, vi);

            // Vérifier si cette nouvelle affectation est cohérente avec les contraintes
            if (isConsistent(N)) {
                // Appel récursif avec l'affectation étendue et les variables restantes
                Map<Variable, Object> R = backtrack(N, V);
                if (R != null) {  // On vérifie si une solution a été trouvée
                    return R;
                }
            }
        }

        // Remettre la variable non assignée dans la liste (backtrack)
        V.add(xi); // Mettre xi à nouveau dans la liste
        return null; // Retourner null si aucune solution n'a été trouvée à ce niveau
    }
}
