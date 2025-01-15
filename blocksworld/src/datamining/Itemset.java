package datamining; 

import java.util.*; 
import modelling.BooleanVariable; 

public class Itemset {
    private Set<BooleanVariable> ensembleItems; 
    private float frequence; 
    public Itemset(Set<BooleanVariable> ensembleItems,float frequence ){
        if (frequence < 0.0 || frequence > 1.0) {
            throw new IllegalArgumentException("La fréquence doit être comprise entre 0.0 et 1.0");
        }
        this.ensembleItems = ensembleItems; 
        this.frequence = frequence; 
    }
    public Set<BooleanVariable> getItems(){
        return ensembleItems; 
    }
    public float getFrequency(){
        return frequence; 
    }  

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itemset { ");
        sb.append("Items: [");
        
        // Ajouter tous les noms des variables booléennes
        ensembleItems.forEach(item -> sb.append(item.getName()).append(", "));
        if (!ensembleItems.isEmpty()) {
            sb.setLength(sb.length() - 2); // Supprimer la dernière virgule et espace
        }
        
        sb.append("], ");
        sb.append("Frequency: ").append(String.format("%.2f", frequence)); // Formater la fréquence avec 2 décimales
        sb.append(" }");
        return sb.toString();
    }
}

