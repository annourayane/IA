package planning; 
import modelling.Variable; 
import java.util.*; 

public class DFSPlanner implements Planner {
    private Map<Variable, Object> initialState; 
    private Set<Action> actions; 
    private Goal goal;
    private int nbrNoeuds = 0; 
    private boolean countNodes = false; 

    public DFSPlanner(Map<Variable,Object> initialState,Set<Action> actions,Goal goal){
        this.initialState = initialState;
        this.actions = actions; 
        this.goal = goal;
    }
    // Accesseurs pour obtenir l'état initial, les actions et le but
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
        // Initialisation du plan 
        Stack<Action> plan = new Stack<>();
        //Initialisation de closed avec l'etat initiale 
        Set<Map<Variable,Object>> closed = new HashSet<>();
        closed.add(initialState); 
        // appel de la méthode dfs avec les parametre 
        return dfs(this,initialState,plan,closed);


    }
    public void activateNodeCount(boolean activate) {
        this.countNodes = activate;
    }

    public int getNbrNoeuds(){
        return nbrNoeuds;
    }
    private List<Action> dfs(Planner problem,Map<Variable,Object> instantiation,Stack<Action> plan,Set<Map<Variable, Object>> closed ){
        if(problem.getGoal().isSatisfiedBy(instantiation)){
            // si oui return le plan courant sous forme de list 
            return new ArrayList<>(plan);
        }
        else {
            for (Action action : problem.getActions()){
                if (action.isApplicable(instantiation)) {
                    Map<Variable,Object> next = action.successor(instantiation);
                    if(!closed.contains(next)){
                        plan.push(action); 
                        closed.add(next);
                        // Incrémenter le compteur de nœuds explorés si le comptage est activé
                        if(countNodes) {
                            nbrNoeuds++; 
                        }
                        List<Action> subplan = dfs(problem,next,plan,closed);
                        if(subplan != null){
                            return subplan;
                        }else {
                            plan.pop();
                        }
                    }
                }
            }
        }
        return null; 

    }
}