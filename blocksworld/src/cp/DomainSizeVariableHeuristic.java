package cp;

import java.util.*;
import modelling.*;

public class DomainSizeVariableHeuristic implements VariableHeuristic {
    private boolean prefererGrandDomaine; 

    
    public DomainSizeVariableHeuristic(boolean prefererGrandDomaine) {
        this.prefererGrandDomaine = prefererGrandDomaine;
    }

    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains) {
        Variable bestVar = null;
        int bestDomainSize = prefererGrandDomaine ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Variable var : variables) {
            int domainSize = domains.get(var).size();

            
            if ((prefererGrandDomaine && domainSize > bestDomainSize) ||
                (!prefererGrandDomaine && domainSize < bestDomainSize)) {
                bestVar = var;
                bestDomainSize = domainSize;
            }
        }

        return bestVar;
    }
}
