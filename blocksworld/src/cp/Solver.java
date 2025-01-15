package cp; 

import java.util.*; 
import modelling.*;
public interface Solver {
    public Map<Variable,Object> solve (); 
}