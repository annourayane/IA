package blocksworld; 

import java.util.*; 
import modelling.*; 
public class MondeDesBlocsContraintes extends MondeDesBlocs {
    
    public List<Constraint> contraintes; // Liste de toutes les contraintes
    public List<List<Integer>> piles; // Liste de piles, ou chaque pile est une liste d'indices de blocs

    public MondeDesBlocsContraintes(int nombreDeBlocs, int nombreDePiles){
        super(nombreDeBlocs, nombreDePiles); // Appeler le constructeur de MondeDesBlocs
        this.contraintes = new ArrayList<>();
        this.piles = new ArrayList<>(); // Initialisation de la liste des piles
        definirContraintes();
    } 
    
    public void definirContraintes(){
        //      contrainte 1 : 
        // onb et onb' peuvent pas avoir la meme valeur 
        for (int i = 0; i < variablesOnb.size();i++){
            for (int j= i + 1 ; j<variablesOnb.size();j++){
                // on va creer une contrainte entre onb et entre onb'
                contraintes.add(new DifferenceConstraint(variablesOnb.get(i),variablesOnb.get(j)));
            }
        }

        // Contraintes 2 : Si onb = b', alors fixedb' doit etre a true
        for(int i = 0 ; i < nombreDeBlocs; i++){
            for(int j = 0 ; j < nombreDeBlocs; j++){
                if(i != j ){
                    Set<Object> domaineB = new HashSet<>();
                    domaineB.add(j); // onb = b'(j)
                    Set<Object> domaineFixedB = new HashSet<>();
                    domaineFixedB.add(true);
                    // ette contrainte d'implication dit que si le bloc i est sur le bloc j (onb = b'), alors le bloc j doit etre fixé  (fixedb = true).
                    Constraint implicationConstraint = new Implication(variablesOnb.get(i), domaineB, variablesFixedb.get(j), domaineFixedB);
                    contraintes.add(implicationConstraint);

                }
            }
        }

        // // Contraintes 3 : Si onb = -(p+1), alors freep doit etre a  false
        for (int i = 0 ; i < nombreDeBlocs; i++){
            for(int p = 0 ; p < nombreDePiles ; p++){
                Set<Object> domainep = new HashSet<>(); 
                domainep.add(-(p+1)); // onb = (-p+1) c a d la pile p
                Set<Object> domainefree = new HashSet<>(); 
                domainefree.add(false); // free p = false 
                Constraint implicationPileLibre = new Implication(variablesOnb.get(i),domainep, variablesFreep.get(p), domainefree);
                contraintes.add(implicationPileLibre);
            }
        }



    }
    // Méthodes pour obtenir toutes les contraintes
    public List<Constraint> getContraintes() {
        return contraintes;
    }
    // Méthodes pour récuperer toutes les Piles de la configuration 
    public List<List<Integer>> getPiles() {
        return piles;
    }

    // Ajout de la méthode pour définir les piles
    public void setPiles(List<List<Integer>> piles) {
        this.piles = piles;
    }

        // Méthode pour obtenir les blocs dans une pile
    public List<Integer> getBlocsDansPile(int pileIndex) {
        return piles.get(pileIndex);
    }

}










