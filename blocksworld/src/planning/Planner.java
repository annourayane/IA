package planning;

import modelling.Variable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Planner {
    
    /**
     * Méthode pour générer un plan d'actions qui permet d'atteindre le but à partir de l'état initial.
     * 
     * @return une liste d'actions représentant le plan, ou null s'il n'y a pas de plan possible.
     */
    List<Action> plan();

    /**
     * Accesseur pour obtenir l'état initial.
     * 
     * @return un objet Map représentant l'état initial (variables associées à leurs valeurs).
     */
    Map<Variable, Object> getInitialState();

    /**
     * Accesseur pour obtenir l'ensemble des actions possibles.
     * 
     * @return un ensemble d'actions disponibles.
     */
    Set<Action> getActions();

    /**
     * Accesseur pour obtenir le but à atteindre.
     * 
     * @return un objet Goal représentant le but.
     */
    Goal getGoal();
}
