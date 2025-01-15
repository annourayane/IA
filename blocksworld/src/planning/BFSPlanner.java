package planning; 
import modelling.Variable; 
import java.util.*; 

public class BFSPlanner implements Planner {
    private Map<Variable, Object> initialState; 
    private Set<Action> actions; 
    private Goal goal;
    
    private int nbrNoeuds = 0; 
    private boolean countNodes = false; // par default desactiver le compteur  

    public BFSPlanner(Map<Variable,Object> initialState, Set<Action> actions, Goal goal) {
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

    // Méthode principale pour lancer la recherche BFS 
    @Override
    public List<Action> plan() {
        return bfs(this);
    }
    public void activateNodeCount(boolean activate){
        this.countNodes = activate;
    }
    // méthode pour récuperer le nombre de noeuds exploré 
    public int getNbrNoeuds() {
        return nbrNoeuds;
    }

    private List<Action> bfs(Planner problem) {
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
        Map<Map<Variable, Object>, Action> plan = new HashMap<>();
        Set<Map<Variable, Object>> closed = new HashSet<>(); 
        Queue<Map<Variable, Object>> open = new LinkedList<>();
        
        Map<Variable, Object> initialState = problem.getInitialState();
        closed.add(initialState);
        open.add(initialState);
        father.put(initialState, null);

        // Vérification si l'état initial satisfait le but
        if (problem.getGoal().isSatisfiedBy(initialState)) {
            return new ArrayList<>(); // Le plan est vide car l'état initial satisfait déjà le but
        }

        // Parcours en largeur (BFS)
        while (!open.isEmpty()) {
            Map<Variable, Object> instantiation = open.poll(); // Récupère et retire l'élément en tête de la file
            closed.add(instantiation);
            if(countNodes) {
                nbrNoeuds++; // Incrémente le nombre de nœuds explorés
            }
            for (Action action : problem.getActions()) {
                if (action.isApplicable(instantiation)) {
                    Map<Variable, Object> next = action.successor(instantiation);
                    
                    if (!closed.contains(next) && !open.contains(next)) {
                        father.put(next, instantiation); // Associe le next et instantiation (père et fils)
                        plan.put(next, action);

                        if (problem.getGoal().isSatisfiedBy(next)) {
                            return getBfsPlan(father, plan, next); // Reconstruit le plan
                        } else {
                            open.add(next); // Ajoute le prochain état à explorer dans la file
                        }
                    }
                }
            }
        }

        return null; // Retourne null si aucun plan n'est trouvé
    }

    // Méthode pour reconstruire le plan à partir des informations de recherche BFS
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
