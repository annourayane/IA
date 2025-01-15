package planning;

import java.util.*;
import modelling.Variable;

public class DijkstraPlanner implements Planner {
    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;

    private int nbrNoeuds = 0;
    private boolean countNodes = false; 
    public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
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

    @Override
    public List<Action> plan() {
        return dijkstra(this);
    }
    public void activateNodeCount(boolean activate) {
        this.countNodes = activate;
    }
    public int getNbrNoeuds() {
        return nbrNoeuds; 
    }
    // Implémentation de l'algorithme de Dijkstra
    private List<Action> dijkstra(Planner problem) {
        Map<Map<Variable, Object>, Action> plan = new HashMap<>();
        Map<Map<Variable, Object>, Double> distance = new HashMap<>();
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
        Set<Map<Variable, Object>> goals = new HashSet<>();
        Set<Map<Variable, Object>> open = new HashSet<>();

        Map<Variable, Object> initialState = problem.getInitialState();
        father.put(initialState, null);
        distance.put(initialState, 0.0);
        open.add(initialState);

        while (!open.isEmpty()) {
            Map<Variable, Object> instantiation = chooseMinDistance(open, distance);
            open.remove(instantiation);
            // Si true on incrémente le compteur des noeuds explorés 
            if (countNodes){
                nbrNoeuds++; 
            }
            if (problem.getGoal().isSatisfiedBy(instantiation)) {
                goals.add(instantiation);
            }

            for (Action action : problem.getActions()) {
                if (action.isApplicable(instantiation)) {
                    Map<Variable, Object> next = action.successor(instantiation);
                    
                    // Initialiser la distance de l'état s'il n'a jamais été rencontré
                    if (!distance.containsKey(next)) {
                        distance.put(next, Double.POSITIVE_INFINITY);
                    }

                    double newDistance = distance.get(instantiation) + action.getCost();
                    if (distance.get(next) > newDistance) {
                        distance.put(next, newDistance);
                        father.put(next, instantiation);
                        plan.put(next, action);
                        open.add(next);
                    }
                }
            }
        }

        if (goals.isEmpty()) {
            return null;
        } else {
            return getDijkstraPlan(father, plan, goals, distance);
        }
    }

    // Choisir l'état avec la plus petite distance
    private Map<Variable, Object> chooseMinDistance(Set<Map<Variable, Object>> open, Map<Map<Variable, Object>, Double> distance) {
        Map<Variable, Object> minState = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (Map<Variable, Object> state : open) {
            double stateDistance = distance.get(state);
            if (stateDistance < minDistance) {
                minDistance = stateDistance;
                minState = state;
            }
        }

        return minState;
    }

    // Reconstruction du plan de Dijkstra
    private List<Action> getDijkstraPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father,
                                         Map<Map<Variable, Object>, Action> plan,
                                         Set<Map<Variable, Object>> goals,
                                         Map<Map<Variable, Object>, Double> distance) {
        Queue<Action> dijkstraPlan = new ArrayDeque<>();
        Map<Variable, Object> goalState = null;
        double minDistance = Double.POSITIVE_INFINITY;

        // Trouver le but avec la plus petite distance
        for (Map<Variable, Object> goal : goals) {
            if (distance.get(goal) < minDistance) {
                minDistance = distance.get(goal);
                goalState = goal;
            }
        }

        // Si aucun but n'a été trouvé, retourner un plan vide
        if (goalState == null) {
            return new ArrayList<>();
        }

        // Remonter les pères et reconstruire le plan
        while (father.get(goalState) != null) {
            dijkstraPlan.add(plan.get(goalState));
            goalState = father.get(goalState);
        }

        // Inverser la file pour obtenir le plan dans le bon ordre
        List<Action> finalPlan = new ArrayList<>(dijkstraPlan);
        Collections.reverse(finalPlan); // Inverser pour avoir l'ordre correct
        return finalPlan;
    }
}
