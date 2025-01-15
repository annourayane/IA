package blocksworld;

import java.util.*; 
import modelling.*;
import planning.*; 
import javax.swing.JFrame;
import bwmodel.*;
import bwui.*;

public class App2 {
    public static void main(String[] args) {

        // Créer une instance du monde des blocs
        int nbBolc = 4 ; 
        int nbPile = 3 ;  
        ActionsMondeDesBlocs bwActions = new ActionsMondeDesBlocs(nbBolc,nbPile);
        
        // Récupérer la liste des actions générées depuis l'objet bw
        System.out.println("les actions possibles sont : "); 
        Set<Action> actions = bwActions.getActions();
        // Afficher le nombre total d'actions générées
        System.out.println("Nombre total d'actions générées : " + actions.size());
        
        System.out.println("les actions générés sont : "); 

        for (Action action : actions) {
            System.out.println(action);
        }

        
        // l'état initial
        Map<Variable, Object> etatInitiale = new HashMap<>(); 
        List<Variable> onbList = new ArrayList<Variable>(bwActions.getVariablesOnb()); 
        etatInitiale.put(onbList.get(0), -1);
        etatInitiale.put(onbList.get(1), 0);
        etatInitiale.put(onbList.get(2), 1);
        etatInitiale.put(onbList.get(3),-2);
        List<BooleanVariable> fixedList = new ArrayList<BooleanVariable>(bwActions.getVariablesFixes());
        etatInitiale.put(fixedList.get(0), true); 
        etatInitiale.put(fixedList.get(1), true);
        etatInitiale.put(fixedList.get(2), false);
        etatInitiale.put(fixedList.get(3), false);
       
        List<BooleanVariable> freepList = new ArrayList<BooleanVariable>(bwActions.getPilesLibres()); 
        etatInitiale.put(freepList.get(0), false);
        etatInitiale.put(freepList.get(1), false);
        etatInitiale.put(freepList.get(2), true);
       
       
        

        // l'état de but
        Map<Variable, Object> etatGoal = new HashMap<>(); 
        onbList = new ArrayList<>(bwActions.getVariablesOnb()); 
        etatGoal.put(onbList.get(0), -1);
        etatGoal.put(onbList.get(1), -3);
        etatGoal.put(onbList.get(2), 3);
        etatGoal.put(onbList.get(3), -2);
        
        fixedList = new ArrayList<BooleanVariable>(bwActions.getVariablesFixes());
        etatGoal.put(fixedList.get(0), false); 
        etatGoal.put(fixedList.get(1), false);
        etatGoal.put(fixedList.get(2), false);
        etatGoal.put(fixedList.get(3), true);
        
        // Lancer les planifications et compter les nœuds explorés
        freepList = new ArrayList<BooleanVariable>(bwActions.getPilesLibres()); 
        etatGoal.put(freepList.get(0), false);
        etatGoal.put(freepList.get(1), false);
        etatGoal.put(freepList.get(2), false);
        

        // Définir le but
        Goal goal = new BasicGoal(etatGoal);
        
        BFSPlanner bfsPlanner = new BFSPlanner(etatInitiale, actions, goal);
        DFSPlanner dfsPlanner = new DFSPlanner(etatInitiale, actions, goal);
        DijkstraPlanner dijkstraPlanner = new DijkstraPlanner(etatInitiale, actions, goal);

        bfsPlanner.activateNodeCount(true);
        dfsPlanner.activateNodeCount(true);
        dijkstraPlanner.activateNodeCount(true);


        
        // Lancer les planifications et compter les nœuds explorés
        System.out.println("BFS Plan:");
        long startBFS = System.currentTimeMillis(); // Temps de début
        long endBFS = System.currentTimeMillis(); // Temps de fin
        List<Action> solutionBfs = bfsPlanner.plan();
        System.out.println("Nœuds explorés (BFS): " + bfsPlanner.getNbrNoeuds());
        System.out.println("Temps de calcul (BFS): " + (endBFS - startBFS) + " ms");
        System.out.println("Le plan trouvé par le (BFS) est : " + solutionBfs); 

        System.out.println("DFS Plan:");
        long startDFS = System.currentTimeMillis(); // Temps de début
        long endDFS = System.currentTimeMillis(); // Temps de fin
        List<Action> solutionDFS = dfsPlanner.plan();
        System.out.println("Nœuds explorés (DFS): " + dfsPlanner.getNbrNoeuds());
        System.out.println("Temps de calcul (DFS): " + (endDFS - startDFS) + " ms");
        System.out.println("Le plan trouvé par le (DFS) est : " + solutionDFS); 

        System.out.println("Dijkstra Plan:");
        long startDijk = System.currentTimeMillis(); // Temps de début
        long endDijk = System.currentTimeMillis(); // Temps de fin
        List<Action> solutionDIJKSTRA = dijkstraPlanner.plan();
        System.out.println("Nœuds explorés (Dijkstra): " + dijkstraPlanner.getNbrNoeuds());
        System.out.println("Temps de calcul (Dijkstra): " + (endDijk - startDijk) + " ms");
        System.out.println("Le plan trouvé par le (Dijkstra) est : " + solutionDIJKSTRA); 

        // Heuristique 1 : Nombre de blocs mal placés
        Heuristic misplacedBlocksHeuristic = new Heuristic() {
            @Override
            public float estimate(Map<Variable, Object> state) {
                int count = 0;
                for (Map.Entry<Variable, Object> goalEntry : etatGoal.entrySet()) {
                    Variable var = goalEntry.getKey();
                    Object goalValue = goalEntry.getValue();
                    Object currentValue = state.get(var);
                    if (currentValue != null && !currentValue.equals(goalValue)) {
                        count++; // Le bloc est mal placé par rapport à l'état but
                    }
                }
                return count;
            }
        };

        // Heuristique 2 : Distance de Manhattan
        Heuristic manhattanDistanceHeuristic = new Heuristic() {
            @Override
            public float estimate(Map<Variable, Object> state) {
                int totalDistance = 0;
                for (Map.Entry<Variable, Object> goalEntry : etatGoal.entrySet()) {
                    Variable var = goalEntry.getKey();
                    Object goalValue = goalEntry.getValue();
                    Object currentValue = state.get(var);

                    // Calculer la "distance" entre les valeurs actuelles et de but si elles sont des positions
                    if (currentValue != null && goalValue instanceof Integer && currentValue instanceof Integer) {
                        int goalPosition = (Integer) goalValue;
                        int currentPosition = (Integer) currentValue;
                        totalDistance += Math.abs(goalPosition - currentPosition); // Distance de Manhattan
                    }
                }
                return totalDistance;
            }
        };
        // Planificateurs A* avec heuristiques
        AStarPlanner aStarPlannerMisplacedBlocks = new AStarPlanner(etatInitiale, actions, goal, misplacedBlocksHeuristic);
        AStarPlanner aStarPlannerManhattanDistance = new AStarPlanner(etatInitiale, actions, goal, manhattanDistanceHeuristic);

        aStarPlannerMisplacedBlocks.activateNodeCount(true);
        aStarPlannerManhattanDistance.activateNodeCount(true);

        // Lancer les planifications et afficher les résultats
        System.out.println("A* Plan avec Heuristique de blocs mal placés:");
        long startA1 = System.currentTimeMillis(); // Temps de début
        long endA1 = System.currentTimeMillis(); // Temps de fin
        List<Action> solutionASTAR1 = aStarPlannerMisplacedBlocks.plan();
        System.out.println("Nœuds explorés (A* avec Heuristique de blocs mal placés): " + aStarPlannerMisplacedBlocks.getNbrNoeuds());
        System.out.println("Temps de calcul (A* avec Heuristique de blocs mal placés): " + (endA1 - startA1) + " ms");
        System.out.println("Le plan trouvé par le (A* avec Heuristique de blocs mal placés) est : " + solutionASTAR1);


        System.out.println("A* Plan avec Heuristique de distance de Manhattan:");
        long startA2 = System.currentTimeMillis(); // Temps de début
        long endA2 = System.currentTimeMillis(); // Temps de fin
        List<Action> solutionASTAR2 = aStarPlannerManhattanDistance.plan();
        System.out.println("Nœuds explorés (A* avec Heuristique de distance de Manhattan): " + aStarPlannerManhattanDistance.getNbrNoeuds());
        System.out.println("Temps de calcul (A* avec Heuristique de distance de Manhattan): " + (endA2 - startA2) + " ms");
        System.out.println("Le plan trouvé par le (A* avec Heuristique de distance de Manhattan) est : " + solutionASTAR2);

        /// partie pour l'affichage 
        displaySolution("BFSPlanner", etatInitiale, bwActions, nbBolc , solutionBfs);
        displaySolution("A* avec Heuristique de blocs mal placés", etatInitiale, bwActions, nbBolc , solutionASTAR1);
        displaySolution("A* avec Heuristique de distance de Manhattan", etatInitiale, bwActions, nbBolc , solutionASTAR2);
        displaySolution("DijkstraPlanner", etatInitiale, bwActions, nbBolc , solutionDIJKSTRA);
        displaySolution("DFSPlanner", etatInitiale, bwActions, nbBolc , solutionDFS);
        
        
    }
    // Méthode pour afficher la configuration dans une fenêtre
    public static void displaySolution(String solverName, Map<Variable, Object> etatInitial, 
                                       ActionsMondeDesBlocs config, int nbBlocks, List<Action> solutionBfs) {
    if (solutionBfs != null && !solutionBfs.isEmpty()) {
        // Convertir l'état initial en BWState
        BWState<Integer> state = makeBWState(etatInitial, config, nbBlocks);

        BWIntegerGUI gui = new BWIntegerGUI(nbBlocks);

        // Création de la fenêtre
        JFrame frame = new JFrame("Simulation trouvée par " + solverName);
        BWComponent<Integer> component = gui.getComponent(state);
        frame.add(component);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

      // Simuler le plan action par action
    Map<Variable, Object> currentState = new HashMap<>(etatInitial); // État courant au format attendu
    BWState<Integer> guiState = makeBWState(currentState, config, nbBlocks); // État pour la GUI
    
    for (Action a : solutionBfs) {
        try {
            Thread.sleep(1_000); // Pause entre les actions
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Calcul du nouvel état
        currentState = a.successor(currentState); // Successeur au format Map<Variable, Object>
        if (currentState == null) {
            System.out.println("Erreur : état invalide après l'action " + a);
            break;
        }
        // Mettre à jour l'état pour la GUI
        guiState = makeBWState(currentState, config, nbBlocks); 
        component.setState(guiState); // Mise à jour graphique
        frame.repaint();
    }
    
            System.out.println("Simulation terminée pour le solveur : " + solverName);
        } else {
            System.out.println("Aucun plan trouvé pour le solveur : " + solverName);
        }
    }
    public static BWState<Integer> makeBWState(Map<Variable, Object> etat, ActionsMondeDesBlocs config, int nbBlocks) {
        BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(nbBlocks);
        for (int b = 0; b < nbBlocks; b++) {
            Variable onB = config.getVariablesOnb().get(b); 
            if (etat.containsKey(onB)) {
                int under = (int) etat.get(onB);
                if (under >= 0) { 
                    builder.setOn(b, under);
                }
            }
        }
        return builder.getState();
    }

}
