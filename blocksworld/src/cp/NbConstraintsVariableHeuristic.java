package cp;

import java.util.*;
import modelling.*;

public class NbConstraintsVariableHeuristic implements VariableHeuristic {
    private boolean ensemblesDeConstraintes;
    private Set<Constraint> constraints; 

    public NbConstraintsVariableHeuristic(Set<Constraint> constraints,boolean ensemblesDeConstraintes) {
        this.constraints = constraints;
        this.ensemblesDeConstraintes = ensemblesDeConstraintes;
    }

    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains) {
        Variable bestVar = null;
        int bestCount = ensemblesDeConstraintes ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Variable var : variables) {
            int count = countConstraints(var);
            if ((ensemblesDeConstraintes && count > bestCount) || (!ensemblesDeConstraintes && count < bestCount)) {
                bestVar = var;
                bestCount = count;
            }
        }

        return bestVar;
    }

    private int countConstraints(Variable var) {
        
        int count = 0;
        for (Constraint c : constraints) {
            if (c.getScope().contains(var)) {
                count++;
            }
        }
        return count;
    }
}
