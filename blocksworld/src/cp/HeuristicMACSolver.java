package cp;

import java.util.*;
import modelling.*;

public class HeuristicMACSolver extends AbstractSolver{
    private VariableHeuristic variableHeuristic; // Heuristique de sélection des variables
    private ValueHeuristic valueHeuristic;       // Heuristique de sélection des valeurs

    // Constructeur prenant en arguments les variables, les contraintes, l'heuristique sur les variables et sur les valeurs
    public HeuristicMACSolver(Set<Variable>variable, Set<Constraint>constraint, 
                              VariableHeuristic variableHeuristic, ValueHeuristic valueHeuristic) {
        super(variable, constraint);
        this.variableHeuristic = variableHeuristic;
        this.valueHeuristic = valueHeuristic;
    }
    @Override
    public Map<Variable, Object> solve() {
        // Initialiser les domaines des variables
        Map<Variable, Set<Object>> domains = new HashMap<>();
        for(Variable variables : variables){
            domains.put(variables,variables.getDomain());
        }
        return mac(new HashMap<>(), variables, domains);
    }

    public Map<Variable, Object> mac(Map<Variable, Object> instantiation, Set<Variable> noInstantiation, 
                                     Map<Variable, Set<Object>> domains) {
        
        if (noInstantiation.isEmpty()) {
            return instantiation;
        } else {
            
            ArcConsistency arc = new ArcConsistency(super.contraintes);
            if (!arc.ac1(domains)) {
                return null; 
            }

            Variable meilleurVariable = variableHeuristic.best(noInstantiation, domains);

            List<Object> domaineOrdonnee = valueHeuristic.ordering(meilleurVariable, domains.get(meilleurVariable));

            for (Object value : domaineOrdonnee) {
                Map<Variable, Object> etatNouvel = new HashMap<>(instantiation);
                etatNouvel.put(meilleurVariable, value);
                Set<Variable> nouvelleVariable = new HashSet<>(noInstantiation);
                nouvelleVariable.remove(meilleurVariable);

                if (isConsistent(etatNouvel)) {
                    
                    Map<Variable, Object> result = new HashMap<>();
                    result = mac(etatNouvel, nouvelleVariable, domains);
                    if (result != null) {
                        return result; 
                    }
                }
            }

            return null; 
        }
    }
}

