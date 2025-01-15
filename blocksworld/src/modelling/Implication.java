package modelling;
import java.util.*;

public class Implication implements Constraint {
    private Variable v1, v2;
    private Set<Object> S1, S2;

    public Implication(Variable v1, Set<Object> S1, Variable v2, Set<Object> S2) {
        this.v1 = v1;
        this.S1 = S1;
        this.v2 = v2;
        this.S2 = S2;
    }

    @Override
    public Set<Variable> getScope() {
        Set<Variable> scope = new HashSet<>();
        scope.add(v1);
        scope.add(v2);
        return scope;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> assignment) {
        Object value1 = assignment.get(v1);
        Object value2 = assignment.get(v2);

        if (value1 == null || value2 == null) {
            throw new IllegalArgumentException("Values must be assigned to both variables.");
        }

        if (S1.contains(value1)) {
            return S2.contains(value2);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Implication(" + v1.getName() + " ∈ " + S1 + " ⇒ " + v2.getName() + " ∈ " + S2 + ")";
    }
}
