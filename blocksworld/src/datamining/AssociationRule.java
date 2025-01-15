package datamining;

import modelling.BooleanVariable;
import java.util.Set;

public class AssociationRule {
    
    private Set<BooleanVariable> premise;   // Prémisse de la règle
    private Set<BooleanVariable> conclusion; // Conclusion de la règle
    private float frequency;                  // Fréquence de la règle
    private float confidence;                 // Confiance de la règle


    public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, float frequency, float confidence) {
        this.premise = premise;
        this.conclusion = conclusion;
        this.frequency = frequency;
        this.confidence = confidence;
    }

    // Accesseurs pour les propriétés de la règle d'association

    public Set<BooleanVariable> getPremise() {
        return premise;
    }

  
    public Set<BooleanVariable> getConclusion() {
        return conclusion;
    }

    public float getFrequency() {
        return frequency;
    }

  
    public float getConfidence() {
        return confidence;
    }
}
