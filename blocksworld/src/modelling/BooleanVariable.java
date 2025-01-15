package modelling; 

import java.util.*; 
public class BooleanVariable extends Variable {
    public BooleanVariable(String nom) {
        super(nom, new HashSet<Object>()); 
        //on ajoute true et false au domaine 
        super.getDomain().add(true); 
        super.getDomain().add(false); 
    }
}