package datamining; 

import java.util.*; 
import modelling.*;
public abstract class AbstractItemsetMiner implements ItemsetMiner{
    protected BooleanDatabase base;
    
    // Comparateur statique pour les BooleanVariable
    public static final Comparator<BooleanVariable> COMPARATOR =
        (var1, var2) -> var1.getName().compareTo(var2.getName());

    public AbstractItemsetMiner(BooleanDatabase base){
        this.base = base; 
    }
    @Override 
    public BooleanDatabase getDatabase(){
        return base; 
    }

    /**
     * Méthode pour calculer la fréquence d'un ensemble donné d'items dans la base.
     * 
     * @param items l'ensemble d'items dont on veut calculer la fréquence.
     * @return la fréquence de l'ensemble d'items.
     */
    public float frequency(Set<BooleanVariable> items) {
        // Calcul de la fréquence
        int count = 0; // Compteur de transactions contenant les items
        int totalTransactions = base.getTransactions().size(); // Nombre total de transactions
        
        for (Set<BooleanVariable> transaction : base.getTransactions()) {
            // Vérifie si la transaction contient tous les items
            if (transaction.containsAll(items)) {
                count++;
            }
        }

        // Calcul de la fréquence
        return totalTransactions > 0 ? (float) count / totalTransactions : 0.0f;
    }
}
