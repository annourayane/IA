
package modelling;

import java.util.Set;

public class Variable {
    private String nom;
    private Set<Object> domaine;


    public Variable(String nom, Set<Object> domaine) {
        this.nom = nom;
        this.domaine = domaine;
    }


    public String getName() {
        return this.nom;
    }

    public Set<Object> getDomain() {
        return this.domaine;
    }


     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Vérifie si c'est le même objet
        if (obj == null || !(obj instanceof Variable)) return false; // Accepter n'importe quel type dérivé de Variable
        Variable variable = (Variable) obj; // Cast l'objet en Variable
        return this.nom.equals(variable.nom); // Compare les noms
    }
   


    @Override
    public int hashCode() {
        return this.nom.hashCode(); 
    }

    @Override
    public String toString() {
        return nom; 
    }
}

