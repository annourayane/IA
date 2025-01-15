package blocksworld;

import java.util.*;
import modelling.*;

public class GestionnaireVariables {
    private MondeDesBlocs mondeDesBlocs;
    private List<BooleanVariable> variablesOnBB; // onb,b'
    private List<BooleanVariable> variablesOnTableBP; // on-tableb,p

    public GestionnaireVariables(int nombreDeBlocs, int nombreDePiles) {
        // Initialiser le MondeDesBlocs
        this.mondeDesBlocs = new MondeDesBlocs(nombreDeBlocs, nombreDePiles);

        // Initialiser les nouvelles listes de variables
        this.variablesOnBB = new ArrayList<>();
        this.variablesOnTableBP = new ArrayList<>();

        // Générer les variables onb,b' pour chaque couple de blocs différents {b, b'}
        for (int b = 0; b < nombreDeBlocs; b++) {
            for (int bPrime = 0; bPrime < nombreDeBlocs; bPrime++) {
                if (b != bPrime) {
                    variablesOnBB.add(new BooleanVariable("on" + b + "," + bPrime));
                }
            }
        }

        // Générer les variables on-tableb,p pour chaque bloc et chaque pile
        for (int b = 0; b < nombreDeBlocs; b++) {
            for (int p = 0; p < nombreDePiles; p++) {
                variablesOnTableBP.add(new BooleanVariable("on-table" + b + "," + -(p+1)));
            }
        }
    }

    // Méthode pour récupérer toutes les variables booléennes
    public Set<BooleanVariable> getToutesLesVariables() {
        Set<BooleanVariable> toutesLesVariables = new HashSet<>();

        // Ajouter les variables fixedb
        toutesLesVariables.addAll(mondeDesBlocs.getVariablesFixes());

        // Ajouter les variables freep
        toutesLesVariables.addAll(mondeDesBlocs.getPilesLibres());

        // Ajouter les variables onb,b'
        toutesLesVariables.addAll(variablesOnBB);

        // Ajouter les variables on-tableb,p
        toutesLesVariables.addAll(variablesOnTableBP);

        return toutesLesVariables;
    }

    // Méthode pour récupérer une instance correspondant à un état donné
    public Set<BooleanVariable> getInstance(List<List<Integer>> etat) {
        Set<BooleanVariable> instance = new HashSet<>();

        int nombreDeBlocs = mondeDesBlocs.getNombreDeBlocs();
        int nombreDePiles = mondeDesBlocs.getNombreDePiles();

        // Réinitialiser les variables fixedb
        for (int b = 0; b < nombreDeBlocs; b++) {
            boolean estFixe = true;
            for (List<Integer> pile : etat) {
                if (!pile.isEmpty() && pile.get(pile.size() - 1) == b) {
                    estFixe = false; // Un bloc en haut d'une pile n'est pas fixé
                }
            }
            if (estFixe) {
                instance.add(new BooleanVariable("fixed" + b)); // Ajoute "fixedb = true"
            }
        }

        // Réinitialiser les variables freep
        for (int p = 0; p < nombreDePiles; p++) {
            if (p < etat.size() && etat.get(p).isEmpty()) {
                instance.add(new BooleanVariable("free" + (p + 1))); // Ajoute "freep = true"
            }
        }

        // Réinitialiser les variables onb,b'
        for (int b = 0; b < nombreDeBlocs; b++) {
            for (int bPrime = 0; bPrime < nombreDeBlocs; bPrime++) {
                if (b != bPrime) {
                    boolean surBPrime = false;
                    for (List<Integer> pile : etat) {
                        int index = pile.indexOf(b);
                        if (index > 0 && pile.get(index - 1) == bPrime) {
                            surBPrime = true;
                            break;
                        }
                    }
                    if (surBPrime) {
                        instance.add(new BooleanVariable("on" + b + "," + bPrime));
                    }
                }
            }
        }

        // Réinitialiser les variables on-tableb,p
        for (int b = 0; b < nombreDeBlocs; b++) {
            for (int p = 0; p < nombreDePiles; p++) {
                boolean surTableDansPileP = false;
                if (p < etat.size() && !etat.get(p).isEmpty() && etat.get(p).get(0) == b) {
                    surTableDansPileP = true;
                }
                if (surTableDansPileP) {
                    instance.add(new BooleanVariable("on-table" + b + "," + -(p+1)));
                }
            }
        }

        return instance;
    }
}
