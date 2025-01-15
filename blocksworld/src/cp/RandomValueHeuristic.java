package cp;

import java.util.*;
import modelling.*;

public class RandomValueHeuristic implements ValueHeuristic {
    private Random generateurAleatoire; 

    
    public RandomValueHeuristic(Random generateurAleatoire) {
        this.generateurAleatoire = generateurAleatoire;
    }

    @Override
    public List<Object> ordering(Variable variable, Set<Object> domain) {
        
        List<Object> values = new ArrayList<>(domain);
        
        Collections.shuffle(values, generateurAleatoire);
        return values; 
    }
}
