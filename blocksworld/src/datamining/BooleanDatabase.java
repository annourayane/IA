package datamining; 

import java.util.*; 
import modelling.BooleanVariable; 

public class BooleanDatabase {
    private Set<BooleanVariable> ensembleItems; 
    private List<Set<BooleanVariable>> transactions;

    public BooleanDatabase(Set<BooleanVariable> ensembleItems) {
        this.ensembleItems = ensembleItems; 
        this.transactions = new ArrayList<>(); 
    }

    /**
     * Une méthode qui permet d'ajouter une transaction  
     */
    public void add(Set<BooleanVariable> transaction) { 
        transactions.add(transaction); 
    }

    public Set<BooleanVariable> getItems() {
        return ensembleItems;
    }

    public List<Set<BooleanVariable>> getTransactions() {
        return transactions; 
    }
}

