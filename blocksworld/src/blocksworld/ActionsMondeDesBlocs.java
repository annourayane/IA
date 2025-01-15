package blocksworld;

import modelling.*;
import planning.*;
import java.util.*;

public class ActionsMondeDesBlocs extends MondeDesBlocs {
    private Set<Action> actions;

    public ActionsMondeDesBlocs(int nombreDeBlocs, int nombreDePiles) {
        super(nombreDeBlocs, nombreDePiles);
        this.actions = new HashSet<>();
        genererActions();
    }

    private void genererActions() {
        // Générer les actions de chaque type
        for (int b = 0; b < nombreDeBlocs; b++) {
            for (int bPrime = 0; bPrime < nombreDeBlocs; bPrime++) {
                if (b != bPrime) {
                    // Action de type 1 : déplacer un bloc du dessus d’un autre bloc vers un troisième bloc
                    for (int bDoublePrime = 0; bDoublePrime < nombreDeBlocs; bDoublePrime++) {
                        if (bDoublePrime != b && bDoublePrime != bPrime) {
                            actions.add(creerActionDeplacerBlocSurBloc(b, bPrime, bDoublePrime));
                        }
                    }
                }
            }
        }
        // Action de type 2 : déplacer un bloc b du dessus d’un bloc bPrime vers une pile vide p
        for (int b = 0; b < nombreDeBlocs; b++) {               // Pour chaque bloc b
            for (int bPrime = 0; bPrime < nombreDeBlocs; bPrime++) { // Pour chaque bloc source bPrime
                if (b != bPrime) { // Un bloc ne peut pas être sur lui-même
                    for (int p = 1; p <= nombreDePiles; p++) { // Pour chaque pile p
                        // Ajouter l'action de déplacement du bloc b du dessus du bloc bPrime vers la pile p
                        actions.add(creerActionDeplacerBlocSurPile(b, bPrime, p));
                    }
                }
            }
        }

        // Action de type 3 : déplacer un bloc b du dessous d’une pile p vers le dessus d’un bloc bPrime
        for (int b = 0; b < nombreDeBlocs; b++) {                // Pour chaque bloc b
            for (int p = 1; p <= nombreDePiles; p++) {           // Pour chaque pile p
                for (int bPrime = 0; bPrime < nombreDeBlocs; bPrime++) { // Pour chaque bloc destination bPrime
                    if (b != bPrime) { // Un bloc ne peut pas être placé sur lui-même
                        // Ajouter l'action de déplacement du bloc b de la pile p vers le dessus du bloc bPrime
                        actions.add(creerActionDeplacerBlocDepuisPile(b, p, bPrime));
                    }
                }
            }
        }

                // Action de type 4 : déplacer un bloc b du dessous d’une pile p vers une pile vide pPrime
        for (int b = 0; b < nombreDeBlocs; b++) {                   // Pour chaque bloc b
            for (int p = 1; p <= nombreDePiles; p++) {              // Pour chaque pile d'origine p
                for (int pPrime = 1; pPrime <= nombreDePiles; pPrime++) { // Pour chaque pile de destination pPrime
                    if (p != pPrime) { // Vérifier que la pile d'origine et la pile de destination sont différentes
                        // Ajouter l'action de déplacement du bloc b de la pile p vers la pile vide pPrime
                        actions.add(creerActionDeplacerBlocEntrePiles(b, p, pPrime));
                    }
                }
            }
        }

            
        
    }

    // Méthodes de création des actions pour chaque type

    // Action de type 1 : déplacer un bloc du dessus d’un autre bloc vers un troisième bloc
    private Action creerActionDeplacerBlocSurBloc(int b, int bPrime, int bDoublePrime) {
        String nomAction = "DeplacerBloc :" + b + "   , DeBloc :" + bPrime + "   , VersBloc  : " + bDoublePrime;
        System.out.println(nomAction); 
        Map<Variable, Object> preconditions = new HashMap<>();
        preconditions.put(new Variable("On" + b, new HashSet<>(Arrays.asList(bPrime))), bPrime); // Le bloc b est sur b'
        preconditions.put(new BooleanVariable("fixed" + b), false); // Le bloc b est déplaçable
        preconditions.put(new BooleanVariable("fixed" + bDoublePrime), false); // La destination b'' est libre

        Map<Variable, Object> effets = new HashMap<>();
        effets.put(new Variable("On" + b, new HashSet<>(Arrays.asList(bDoublePrime))), bDoublePrime); // Le bloc b est maintenant sur b''
        effets.put(new BooleanVariable("fixed" + bPrime), false); // Le bloc b' est maintenant libre
        effets.put(new BooleanVariable("fixed" + bDoublePrime), true); // La destination b'' est maintenant occupée

        return new BasicAction(preconditions, effets, 1); // Le coût est arbitraire, ici défini à 1
    }

    // Action de type 2 : déplacer un bloc b du dessus d’un bloc bPrime vers une pile vide p
    private Action creerActionDeplacerBlocSurPile(int b, int bPrime, int p) {
    String nomAction = "DeplacerBloc : " + b + "  ,DeBloc : " + bPrime + "  ,VersPile :" + p;
    System.out.println(nomAction);
    Map<Variable, Object> preconditions = new HashMap<>();
    // Le bloc b est sur le bloc bPrime
    preconditions.put(new Variable("On" + b, new HashSet<>(Arrays.asList(bPrime))), bPrime);
    // Le bloc b doit être déplaçable
    preconditions.put(new BooleanVariable("fixed" + b), false);
    // La pile p doit être libre
    preconditions.put(new BooleanVariable("free" + p), true);

    Map<Variable, Object> effets = new HashMap<>();
    // Le bloc b est maintenant sur la pile p
    effets.put(new Variable("On" + b, new HashSet<>(Arrays.asList(-p))), -p);
    // Le bloc bPrime devient libre
    effets.put(new BooleanVariable("fixed" + bPrime), false);
    // La pile p est maintenant occupée
    effets.put(new BooleanVariable("free" + p), false);

    return new BasicAction(preconditions, effets, 1); // Le coût est défini à 1
    }

    // Méthode pour créer l'action de déplacer un bloc b de la pile p vers le dessus d'un bloc bPrime
    private Action creerActionDeplacerBlocDepuisPile(int b, int p, int bPrime) {
    String nomAction = "DeplacerBloc : " + b + "  , DePile : " + p + "  , VersBloc : " + bPrime;
    System.out.println(nomAction); 
    Map<Variable, Object> preconditions = new HashMap<>();
    preconditions.put(new Variable("On" + b, new HashSet<>(Arrays.asList(-p))), -p); // Le bloc b est sur la pile p
    preconditions.put(new BooleanVariable("fixed" + b), false); // Le bloc b est déplaçable
    preconditions.put(new BooleanVariable("fixed" + bPrime), false); // Le bloc bPrime est libre

    Map<Variable, Object> effets = new HashMap<>();
    effets.put(new Variable("On" + b, new HashSet<>(Arrays.asList(bPrime))), bPrime); // Le bloc b est maintenant sur bPrime
    effets.put(new BooleanVariable("fixed" + bPrime), true); // Le bloc bPrime est maintenant occupé
    effets.put(new BooleanVariable("free" + p), true); // La pile p est maintenant libre

    return new BasicAction(preconditions, effets, 1); // Le coût est arbitraire, ici défini à 1
    }

    // Méthode pour créer l'action de déplacer un bloc b de la pile p vers une pile vide pPrime
    private Action creerActionDeplacerBlocEntrePiles(int b, int p, int pPrime) {
    String nomAction = "DeplacerBloc :" + b + "  ,DePile : " + p + "  , VersPile : " + pPrime;
    System.out.println(nomAction); 
    Map<Variable, Object> preconditions = new HashMap<>();
    preconditions.put(new Variable("On" + b, new HashSet<>(Arrays.asList(-p))), -p); // Le bloc b est sur la pile p
    preconditions.put(new BooleanVariable("fixed" + b), false); // Le bloc b est déplaçable
    preconditions.put(new BooleanVariable("free" + pPrime), true); // La pile de destination pPrime est libre

    Map<Variable, Object> effets = new HashMap<>();
    effets.put(new Variable("On" + b, new HashSet<>(Arrays.asList(-pPrime))), -pPrime); // Le bloc b est maintenant sur la pile pPrime
    effets.put(new BooleanVariable("free" + p), true); // La pile d'origine p est maintenant libre
    effets.put(new BooleanVariable("free" + pPrime), false); // La pile de destination pPrime est maintenant occupée

    return new BasicAction(preconditions, effets, 1); // Le coût est arbitraire, ici défini à 1
    }

    
    public Set<Action> getActions() {
        return actions;
    }
}
