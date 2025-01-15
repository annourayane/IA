package cp;

import modelling.*;
import java.util.*;

public interface VariableHeuristic {
    Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains);
}
