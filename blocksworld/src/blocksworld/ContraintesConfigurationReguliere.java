package blocksworld;

import java.util.*;
import modelling.*; 
public class ContraintesConfigurationReguliere extends MondeDesBlocsContraintes {
    public Set<Constraint> contraintesRegulieres; 
    public ContraintesConfigurationReguliere(int nombreDeBlocs, int nombreDePiles) {
        super(nombreDeBlocs, nombreDePiles);
        this.contraintesRegulieres = new HashSet<>(); 
        definirContraintesRegulieres(); 
    }

    // Cette méthode vérifie les écarts entre les blocs dans chaque pile
    public Set<Constraint> definirContraintesRegulieres() {
        for (Variable variable1 : this.getVariablesOnb()){
            for (Variable variable2 : this.getVariablesOnb()){
                if(!variable1.equals(variable2)){
                   Set<Object> domain = new HashSet<>(); 
                        for (Variable variable : this.getPilesLibres()){
                            domain.add(-Integer.parseInt(variable.getName().substring(4))); 
                        }
                        int ecart = Math.abs(Integer.parseInt(variable1.getName().substring(2))-Integer.parseInt(variable2.getName().substring(2)));
                        for (Variable variable3 : this.getVariablesOnb()){
                            if (!variable1.equals(variable3) && ! variable2.equals(variable3)){
                                int ecart2 = Math.abs(Integer.parseInt(variable2.getName().substring(2))-Integer.parseInt(variable3.getName().substring(2)));
                                if (ecart == ecart2){
                                    domain.add(Integer.parseInt(variable3.getName().substring(2)));
                                }
                            }
                        }
                    Implication imp = new Implication(variable1, Set.of(Integer.parseInt(variable2.getName().substring(2))), variable2, domain);
                    contraintesRegulieres.add(imp); 
                }
            }
        }
        return contraintesRegulieres; 
    }
    public Set<Constraint> getContraintesRegulieres(){
        return contraintesRegulieres; 
    }
}
