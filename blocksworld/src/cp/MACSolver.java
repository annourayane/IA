package cp;

import java.util.*;


import modelling.*;

public class MACSolver extends AbstractSolver{

    public MACSolver(Set<Variable> setVariables, Set<Constraint> setConstraints){
        super(setVariables, setConstraints);
    }

    public Map<Variable, Object> solve(){
        return mac(new HashMap<Variable, Object>(), new LinkedList<Variable>(super.variables),new HashMap<Variable, Set<Object>>());
    }

    public Map<Variable,Object> mac(Map<Variable, Object> instantiation, LinkedList<Variable>
noInstantiation,Map<Variable, Set<Object>> ed){
        if (noInstantiation.isEmpty()) {
            return instantiation;
        }
        else{
            ArcConsistency arc = new ArcConsistency(super.contraintes);
            if(!arc.ac1(ed)){
                return null;
            }
            Variable noInstantied = noInstantiation.poll();
            Map<Variable, Object> sub_mac = new HashMap<>();
            for(Object vi : noInstantied.getDomain()){
                Map<Variable, Object> etatNouvel = new HashMap<>(instantiation);
                etatNouvel.put(noInstantied, vi);
                if(isConsistent(etatNouvel)){
                    sub_mac = mac(etatNouvel,noInstantiation,ed);
                    if(sub_mac!=null){
                        return sub_mac;
                    }
                }
            }
            noInstantiation.add(noInstantied);
            return null;
        }
    }
    
}
