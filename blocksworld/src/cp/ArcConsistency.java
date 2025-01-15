package cp;
import modelling.Variable;
import modelling.Constraint;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.*;

import java.util.HashSet;
import java.util.Collections;
import java.util.Iterator;




public class ArcConsistency{
    private Set<Constraint> constraints;

    public ArcConsistency(Set<Constraint> constraints){
         this.constraints = constraints;
         for (Constraint c : constraints){
            Set<Variable> scope = c.getScope();
            if (scope.size() != 1 && scope.size() != 2){
                throw new IllegalArgumentException("Ni unaire ni binaire : " + c);
            }
         }

    }
   // MÃ©thode pour appliquer la consistance des nÅ“uds (node consistency) 
    public boolean enforceNodeConsistency(Map<Variable, Set<Object>> domaines) {
    
    
        for (Variable x : new HashSet<>(domaines.keySet())) {
            Set<Object> domaine = domaines.get(x); 
        
            Set<Object> valeursASupprimer = new HashSet<>();
            boolean aUneContrainte = false;

            for (Object v : new HashSet<>(domaine)) {  
                boolean satisfait = false; 
                // Parcourir toutes les constraints unaires pour cette variable
                for (Constraint c : constraints) {
                    if (c.getScope().size() == 1 && c.getScope().contains(x)) {
                        aUneContrainte = true;

                    
                    if (c.isSatisfiedBy(Map.of(x, v))) {
                        satisfait = true; 

                        break; 
                    }
                }
            }
        if (aUneContrainte){
            // Si v n'est pas satisfait, ajouter Ã  la liste des valeur a  supprimer
            if (!satisfait) {
                valeursASupprimer.add(v); // Marquer pour suppression

            }
        }
        }

        domaine.removeAll(valeursASupprimer);
    }
    for (Variable x : new HashSet<>(domaines.keySet())){
        Set<Object> domaine = domaines.get(x); 
            if (domaine.isEmpty()) {

            return false; 
        }
    }
       
    return true;
    }




public boolean revise(Variable v1, Set<Object> D1, Variable v2, Set<Object> D2) {
    boolean deleted = false; 

    Iterator<Object> it = D1.iterator();
    while (it.hasNext()) {
        Object vi = it.next();
        boolean viable = false; 

       
        for (Object vj : D2) {
            Map<Variable, Object> assignment = new HashMap<>();
            assignment.put(v1, vi);
            assignment.put(v2, vj);

            boolean allSatisfied = true;
            
            for (Constraint c : constraints) {
                if (c.getScope().contains(v1) && c.getScope().contains(v2)) {
                    // Si la contrainte n'est pas satisfaite pour vi et vj, marquer comme non viable
                    if (!c.isSatisfiedBy(assignment)) {
                        allSatisfied = false;
                        break;
                    }
                }
            }

            // Si au moins une valeur vj de D2 satisfait la contrainte, vi est viable
            if (allSatisfied) {
                viable = true;
                break;
            }
        }

        // Si aucune valeur vj dans D2 ne rend vi viable, supprimer vi de D1
        if (!viable) {
            it.remove();
            deleted = true; // Indiquer qu'un changement a Ã©tÃ© effectuÃ©
        }
    }

    return deleted;
}

   // MÃ©thode principale de l'algorithme AC1
   public boolean ac1(Map<Variable, Set<Object>> domaines) {
        if (!enforceNodeConsistency(domaines)) {
            return false; // Si la consistance des nÅ“uds echoue, retourner faux
        }

        boolean change;
        
        do {
            change = false; // Initialiser change a faux

            
            for (Variable xi : domaines.keySet()) {
                for (Variable xj : domaines.keySet()) {
                if (!xi.equals(xj)) { // Ignorer les couples identiques
                        
                        boolean revised = revise(xi, domaines.get(xi), xj, domaines.get(xj));
                    
                     if (revised) {
                        change = true; // Un changement a eu lieu
                    }

                    }
                }
            }
        } while (change); 


        for (Variable x : new HashSet<>(domaines.keySet())){
            Set<Object> domaine = domaines.get(x); // Domaine de la variable x
            if (domaine.isEmpty()) {
                return false; 
            }
        }
        // retourner vrai si tout est stable
        return true;
    }


}