package modelling;
import java.util.*;

public class DifferenceConstraint implements Constraint{
    private Variable v1, v2;

    public DifferenceConstraint(Variable v1, Variable v2) {
        this.v1 = v1;
        this.v2 = v2;
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

        return !value1.equals(value2);
    }
    @Override
    public String toString() {
        return "DifferenceConstraint(" + v1.getName() + " ≠ " + v2.getName() + ")";
    }
}
