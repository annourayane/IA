package planning;

import modelling.Variable;
import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        // Création de variables pour la planification
        Variable x = new Variable("x", null);
        Variable y = new Variable("y", null);
        Variable z = new Variable("z", null);

        // État initial : x = 1, y = 1, z = 1
        Map<Variable, Object> initialState = new HashMap<>();
        initialState.put(x, 1);
        initialState.put(y, 1);
        initialState.put(z, 1);

        // Création des actions avec préconditions et effets
        // Action 1 : x = 1 → x = 2
        Map<Variable, Object> precondition1 = new HashMap<>();
        precondition1.put(x, 1);
        Map<Variable, Object> effect1 = new HashMap<>();
        effect1.put(x, 2);
        BasicAction action1 = new BasicAction(precondition1, effect1, 1);

        // Action 2 : y = 1 → y = 3 (cette action a un coût plus élevé)
        Map<Variable, Object> precondition2 = new HashMap<>();
        precondition2.put(y, 1);
        Map<Variable, Object> effect2 = new HashMap<>();
        effect2.put(y, 3);
        BasicAction action2 = new BasicAction(precondition2, effect2, 3);

        // Action 3 : z = 1 → z = 2
        Map<Variable, Object> precondition3 = new HashMap<>();
        precondition3.put(z, 1);
        Map<Variable, Object> effect3 = new HashMap<>();
        effect3.put(z, 2);
        BasicAction action3 = new BasicAction(precondition3, effect3, 1);

        // Action 4 : z = 2 → z = 3
        Map<Variable, Object> precondition4 = new HashMap<>();
        precondition4.put(z, 2);
        Map<Variable, Object> effect4 = new HashMap<>();
        effect4.put(z, 3);
        BasicAction action4 = new BasicAction(precondition4, effect4, 1);

        // Créer un ensemble d'actions
        Set<Action> actions = new HashSet<>(Arrays.asList(action1, action2, action3, action4));

        // Créer un but : x = 2, y = 3, z = 3
        Map<Variable, Object> goalState = new HashMap<>();
        goalState.put(x, 2);
        goalState.put(y, 3);
        goalState.put(z, 3);
        Goal goal = new BasicGoal(goalState);

        // Créer les planificateurs
        BFSPlanner bfsPlanner = new BFSPlanner(initialState, actions, goal);
        DFSPlanner dfsPlanner = new DFSPlanner(initialState, actions, goal);
        DijkstraPlanner dijkstraPlanner = new DijkstraPlanner(initialState, actions, goal);

        // Implémenter une heuristique  pour A*
        Heuristic heuristic = new Heuristic() {
            //l'heuristique est utilisée pour estimer le coût restant pour atteindre l'objectif depuis l'état actuel. 
            @Override
            public float estimate(Map<Variable, Object> state) {
                // Exemple d'heuristique plus précise : distance au but, mais en tenant compte du coût estimé
                float cost = 0;
                for (Map.Entry<Variable, Object> goalEntry : goalState.entrySet()) {
                    Variable variable = goalEntry.getKey();
                    Object goalValue = goalEntry.getValue();
                    Object currentValue = state.get(variable);

                    if (variable.equals(x)) {
                        // Prendre en compte la différence de valeur pour x
                        cost += Math.abs((int)goalValue - (int)currentValue);
                    } else if (variable.equals(y)) {
                        // y coûte plus cher à atteindre, donc on augmente le coût pour cette variable
                        cost += Math.abs((int)goalValue - (int)currentValue) * 2;
                    } else if (variable.equals(z)) {
                        // z est moins coûteux à atteindre
                        cost += Math.abs((int)goalValue - (int)currentValue);
                    }
                }
                return cost;
            }
        };

        AStarPlanner aStarPlanner = new AStarPlanner(initialState, actions, goal, heuristic);

        // Activer le comptage des nœuds explorés
        bfsPlanner.activateNodeCount(true);
        dfsPlanner.activateNodeCount(true);
        dijkstraPlanner.activateNodeCount(true);
        aStarPlanner.activateNodeCount(true);

        // Lancer les planifications et compter les nœuds explorés
        System.out.println("BFS Plan:");
        bfsPlanner.plan();
        System.out.println("Nœuds explorés (BFS): " + bfsPlanner.getNbrNoeuds());

        System.out.println("DFS Plan:");
        dfsPlanner.plan();
        System.out.println("Nœuds explorés (DFS): " + dfsPlanner.getNbrNoeuds());

        System.out.println("Dijkstra Plan:");
        dijkstraPlanner.plan();
        System.out.println("Nœuds explorés (Dijkstra): " + dijkstraPlanner.getNbrNoeuds());

        System.out.println("A* Plan:");
        aStarPlanner.plan();
        System.out.println("Nœuds explorés (A*): " + aStarPlanner.getNbrNoeuds());
    }
}
