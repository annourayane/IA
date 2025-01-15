package datamining; 


import java.util.*; 
public interface ItemsetMiner {
    /**
     * recuperer les bdd transactionnelles
     */
    public BooleanDatabase getDatabase(); 
    /**
     * prend en argument une frequence et retournant l’ensemble des itemsets (non vides) ayant au moins cette fréquence dans
    la base.
    */
    public Set<Itemset> extract(float frequence); 

}