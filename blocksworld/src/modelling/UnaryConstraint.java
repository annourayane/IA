package modelling;
import java.util.*;

public class UnaryConstraint implements Constraint{
    private Variable v;
    private Set<Object> S;

    public UnaryConstraint(Variable v, Set<Object> S) {
        this.v = v;
        this.S = S;
    }

    @Override
    public Set<Variable> getScope() {
        Set<Variable> scope = new HashSet<>();
        scope.add(v);
        return scope;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> assignment) {
        Object value = assignment.get(v);

        if (value == null) {
            throw new IllegalArgumentException("Value must be assigned to the variable.");
        }

        return S.contains(value);
    }

    @Override
    public String toString() {
        return "UnaryConstraint(" + v.getName() + " ∈ " + S + ")";
    }

}
