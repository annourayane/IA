package cp;

import java.util.*;
import modelling.*;


public abstract class AbstractSolver implements Solver {
    protected Set<Variable> variables;  // Ensemble des variables du problème
    protected Set<Constraint> contraintes;  // Ensemble des contraintes à respecter

    // Constructeur prenant les variables et les contraintes en paramètres
    public AbstractSolver(Set<Variable> variables, Set<Constraint> contraintes) {
        this.variables = variables;
        this.contraintes = contraintes;
    }

    /**
     * Méthode pour vérifier si une affectation partielle satisfait les contraintes locales.
     * 
     * @param affectation Une affectation partielle de variables
     * @return true si toutes les contraintes locales sont satisfaites, sinon false.
     */
    public boolean isConsistent(Map<Variable, Object> affectation) {
        // Parcourir toutes les contraintes
        for (Constraint contrainte : contraintes) {
            // Vérifie si toutes les variables de la contrainte sont présentes dans l'affectation
            if (affectation.keySet().containsAll(contrainte.getScope())) {
                // Si la contrainte est violée, retourner false
                if (!contrainte.isSatisfiedBy(affectation)) {
                    return false;
                }
            }
        }
        // Si toutes les contraintes locales sont satisfaites, retourner true
        return true;
    }

    @Override
    public abstract Map<Variable, Object> solve();  // Méthode abstraite à implémenter par les sous-classes
}
