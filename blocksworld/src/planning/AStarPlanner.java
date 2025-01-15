package planning;

import java.util.*;
import modelling.Variable;

public class AStarPlanner implements Planner {
    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;
    private Heuristic heuristique;
    // les attributs qui permettent de compter le nombre des noeuds explorés 
    // Ce compteur sera incrémenté chaque fois que l'algorithme visite un nouveau nœud (par exemple,
    // chaque fois qu'un état est retiré de l'ensemble open et traité).
    private int nbrNoeuds = 0; 

    private boolean countNodes = false; // desactiver par default 
                                        // permet de faire le comtage ou pas 

    public AStarPlanner(Map<Variable, Object> initialState,
                        Set<Action> actions,
                        Goal goal,
                        Heuristic heuristique) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.heuristique = heuristique;
    }

    @Override
    public Map<Variable, Object> getInitialState() {
        return initialState;
    }

    @Override
    public Set<Action> getActions() {
        return actions;
    }

    @Override
    public Goal getGoal() {
        return goal;
    }

    public Heuristic getHeuristique() {
        return heuristique;
    }

    @Override 
    public List<Action> plan() {
        return aStar(this); 
    }
    public void activateNodeCount(boolean activate) {
        this.countNodes = activate; 
    }
    public int getNbrNoeuds() {
        return nbrNoeuds; 
    }

    private List<Action> aStar(Planner problem) {
        Map<Map<Variable,Object>,Action> plan = new HashMap<>();
        Map<Map<Variable,Object>,Map<Variable,Object>> father = new HashMap<>(); 
        Map<Map<Variable,Object>,Double> distance = new HashMap<>(); 
        Map<Map<Variable,Object>,Double> value = new HashMap<>(); 
        Set<Map<Variable,Object>> open = new HashSet<>(); 
        Map<Variable,Object> initialState = problem.getInitialState();
        
        open.add(initialState);
        father.put(initialState, null);
        distance.put(initialState, 0.0);
        
        // Convertir le float retourné par estimate en Double
        value.put(initialState, Double.valueOf(heuristique.estimate(initialState))); 

        while (!open.isEmpty()) {
            Map<Variable,Object> instantiation = chooseMinValue(open, value);
            if (countNodes) {    // si true alors on fait le comptage 
                nbrNoeuds++;
            }

            // Remplacer testIsSatisfiedBy par isSatisfiedBy (vérifier si cette méthode existe dans Goal)
            if (problem.getGoal().isSatisfiedBy(instantiation)) {
                return getBfsPlan(father, plan, instantiation);
            } else {
                open.remove(instantiation); 
                for (Action action : problem.getActions()) {
                    if (action.isApplicable(instantiation)) {
                        Map<Variable,Object> next = action.successor(instantiation);
                        if (!distance.containsKey(next)) {
                            distance.put(next, Double.POSITIVE_INFINITY);
                        }
                        Double newDistance = distance.get(instantiation) + action.getCost(); 
                        if (distance.get(next) > newDistance) {
                            distance.put(next, newDistance); 
                            value.put(next, newDistance + heuristique.estimate(next));
                            father.put(next, instantiation);
                            plan.put(next, action);
                            open.add(next); 
                        }
                    }
                }
            }
        }

        return null; // Si aucun chemin n'est trouvé
    }

    private Map<Variable, Object> chooseMinValue(Set<Map<Variable, Object>> open, Map<Map<Variable, Object>, Double> value) {
        Map<Variable, Object> minState = null;
        double minValue = Double.POSITIVE_INFINITY;

        for (Map<Variable, Object> state : open) {
            double stateValue = value.get(state);
            if (stateValue < minValue) {
                minValue = stateValue;
                minState = state;
            }
        }
        return minState;
    }

    // Méthode pour reconstruire le plan à partir des informations de recherche A*
    private List<Action> getBfsPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father,
                                    Map<Map<Variable, Object>, Action> plan,
                                    Map<Variable, Object> goalState) {
        Queue<Action> bfsPlan = new LinkedList<>();
        Map<Variable, Object> currentState = goalState;

        // Remonte les états à partir du but jusqu'à l'état initial
        while (currentState != null) {
            Action action = plan.get(currentState);
            if (action != null) {
                bfsPlan.add(action); // Ajoute l'action à la Queue
            }
            currentState = father.get(currentState); // Remonte vers l'état père
        }

        // Inverser la queue pour retourner le plan dans le bon ordre
        List<Action> finalPlan = new ArrayList<>(bfsPlan);
        Collections.reverse(finalPlan); // Inverse la liste pour obtenir le plan dans le bon ordre

        return finalPlan; // Retourne le plan dans l'ordre correct
    }
}

