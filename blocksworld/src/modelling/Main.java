package modelling;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Création d'un domaine de valeurs possibles pour les variables
        Set<Object> domaine = new HashSet<>();
        domaine.add(1);
        domaine.add(2);
        domaine.add(3);

        // Instantiation de variables
        Variable v1 = new Variable("X1", domaine);
        Variable v2 = new Variable("X2", domaine);
        BooleanVariable boolVar = new BooleanVariable("Bool");

        // Création d'une instanciation de variables avec des valeurs spécifiques
        Map<Variable, Object> instantiation = new HashMap<>();
        instantiation.put(v1, 1);
        instantiation.put(v2, 2);
        instantiation.put(boolVar, true);

        // Définition des contraintes
        Constraint diffConstraint = new DifferenceConstraint(v1, v2);
        Constraint unaryConstraint = new UnaryConstraint(v1, domaine);
        Set<Object> s1 = new HashSet<>();
        s1.add(1);
        Set<Object> s2 = new HashSet<>();
        s2.add(2);
        Constraint implication = new Implication(v1, s1, v2, s2);

        // Test des contraintes
        System.out.println("État actuel des variables : " + instantiation);
        
        System.out.println("Vérification de la contrainte de différence (X1 != X2): " + 
            diffConstraint.isSatisfiedBy(instantiation));
        
        System.out.println("Vérification de la contrainte unaire (X1 dans le domaine): " + 
            unaryConstraint.isSatisfiedBy(instantiation));
        
        System.out.println("Vérification de la contrainte d'implication (si X1 dans " + 
            s1 + " alors X2 dans " + s2 + "): " + 
            implication.isSatisfiedBy(instantiation));

       
        instantiation.put(v1, 2); // Changer la valeur de X1 à 2
        System.out.println("\nNouveau état des variables : " + instantiation);
        System.out.println("Vérification de la contrainte de différence (X1=2, X2=2): " + 
            diffConstraint.isSatisfiedBy(instantiation)); // Devrait échouer

        instantiation.put(v1, 3); // Changer la valeur de X1 à 3
        System.out.println("\nNouveau état des variables : " + instantiation);
        System.out.println("Vérification de l'implication (X1=3): " + 
            implication.isSatisfiedBy(instantiation)); 
    }
}

