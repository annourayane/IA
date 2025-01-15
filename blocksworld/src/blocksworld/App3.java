package blocksworld;

import java.util.*;
import modelling.*;
import cp.*;
import javax.swing.JFrame;
import bwmodel.*;
import bwui.*;
public class App3 {
    public static void main(String[] args){
// contraintes demendant une configuration reguliere 

        // Création du monde des blocs avec contraintes de configuration régulière
        System.out.println("\n"); 
        System.out.println("***********Exemple de contraintes demendant une configuration réguliere  : "); 
        int nbBloc1 = 5 ; 
        int nbPile1 = 3 ; 
        ContraintesConfigurationReguliere bw = new ContraintesConfigurationReguliere(nbBloc1, nbPile1); 

        // Initialisation des variables et des contraintes
        List<Variable> onbList = new ArrayList<>(bw.getVariablesOnb()); 
        List<BooleanVariable> fixedList = new ArrayList<>(bw.getVariablesFixes());
        List<BooleanVariable> freepList = new ArrayList<>(bw.getPilesLibres());

        // Génération des contraintes régulières
        bw.definirContraintesRegulieres();
        //Set<Constraint> contraintesRegulieres = bw.getContraintesRegulieres().getContraintes();
        // Récupération des contraintes de base et des contraintes régulières
        List<Constraint> contraintesDeBase = bw.getContraintes();
        Set<Constraint> contraintesRegulieres = bw.getContraintesRegulieres();

        // Combinaison des deux ensembles de contraintes en un seul ensemble
        Set<Constraint> toutesLesContraintes = new HashSet<>(contraintesDeBase); // Ajout des contraintes de base
        toutesLesContraintes.addAll(contraintesRegulieres); // Ajout des contraintes régulières


        // Initialisation du solveur de backtracking
        Set<Variable> toutesLesVariables = new HashSet<>();
        toutesLesVariables.addAll(onbList);
        toutesLesVariables.addAll(fixedList);
        toutesLesVariables.addAll(freepList);

        // Instancier BacktrackSolver avec toutes les variables et contraintes régulières
        BacktrackSolver solver = new BacktrackSolver(toutesLesVariables, toutesLesContraintes);

        // Mesurer le temps avant de résoudre
        long startTime = System.currentTimeMillis();

        // Trouver la solution avec le backtracking solver
        Map<Variable, Object> solution = solver.solve();

        // Mesurer le temps après avoir trouvé la solution
        long endTime = System.currentTimeMillis();

        // Calculer et afficher le temps de calcul
        
        long timeElapsed = endTime - startTime;
        System.out.println("Temps de calcul pour le BacktrackSolver : " + timeElapsed + " ms");

        // Afficher la solution si elle existe
        if (solution != null) {
            System.out.println("Solution trouvée par le (BACKTRACKSOLVER) : " + solution);
        } else {
            System.out.println("Aucune solution n'a été trouvée par le (BACKTRACKSOLVER).");
        }
        System.out.println("\n");
        // Vérification finale de la configuration régulière
        boolean configurationReguliere = true;
        for (Constraint contrainte : toutesLesContraintes) {
            if (!contrainte.isSatisfiedBy(solution)) {
                configurationReguliere = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }
        
        // Affichage du résultat final
        if (configurationReguliere) {
            System.out.println("La configuration trouvé est régulière.");
        } else {
            System.out.println("La configuration trouvé n'est pas régulière.");
        }
        System.out.println("\n\n"); 

        // Instancier MACSolver avec toutes les variables et contraintes régulières
        MACSolver macSolver = new MACSolver(toutesLesVariables, toutesLesContraintes);

        // Mesurer le temps avant de résoudre
        long macStartTime = System.currentTimeMillis();

        // Trouver la solution avec le backtracking solver
        Map<Variable, Object> macSolution = macSolver.solve();

        // Mesurer le temps après avoir trouvé la solution
        long macEndTime = System.currentTimeMillis();

        // Calculer et afficher le temps de calcul
        long macTimeElapsed = macEndTime - macStartTime;
        System.out.println("Temps de calcul pour le macSolver : " + macTimeElapsed + " ms");

        // Afficher la solution si elle existe
        if (solution != null) {
            System.out.println("Solution trouvée par le (MACSOLVER) : " + macSolution);
        } else {
            System.out.println("Aucune solution n'a été trouvée par le (MACSOLVER).");
        }
        System.out.println("\n"); 
        // Vérification finale de la configuration régulière
        boolean macConfigurationReguliere = true;
        for (Constraint contrainte : toutesLesContraintes) {
            if (!contrainte.isSatisfiedBy(macSolution)) {
                macConfigurationReguliere = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }
        
        // Affichage du résultat final
        if (macConfigurationReguliere) {
            System.out.println("La configuration trouvé  est régulière.");
        } else {
            System.out.println("La configuration trouvé n'est pas régulière.");
        }
        System.out.println("\n"); 


        // ---- Résolution avec HeuristicMACSolver ----
        // Choisir et instancier une heuristique de sélection de variables
        // Exemple avec DomainSizeVariableHeuristic pour les petits domaines en priorité
        VariableHeuristic variableHeuristic = new DomainSizeVariableHeuristic(false);

        // Exemple avec NbConstraintsVariableHeuristic pour maximiser les contraintes
        // VariableHeuristic variableHeuristic = new NbConstraintsVariableHeuristic(toutesLesContraintes, true);

        // Instancier RandomValueHeuristic pour la sélection aléatoire de valeurs
        ValueHeuristic valueHeuristic = new RandomValueHeuristic(new Random());

        // Instancier le solveur avec heuristiques
        HeuristicMACSolver heuristicMACSolver = new HeuristicMACSolver(
                toutesLesVariables,
                toutesLesContraintes,
                variableHeuristic,
                valueHeuristic
        );

        // Résoudre et mesurer le temps de calcul
        long heuristicStartTime = System.currentTimeMillis();
        Map<Variable, Object> heuristicSolution = heuristicMACSolver.solve();
        long heuristicEndTime = System.currentTimeMillis();
        long heuristicTimeElapsed = heuristicEndTime - heuristicStartTime;

        // Afficher les résultats
        System.out.println("Temps de calcul pour le HeuristicMACSolver : " + heuristicTimeElapsed + " ms\n");

        if (heuristicSolution != null) {
            System.out.println("Solution trouvée par le (HeuristicMACSolver) : " + heuristicSolution);
        } else {
            System.out.println("Aucune solution n'a été trouvée par le (HeuristicMACSolver).");
        }
        System.out.println("\n");
        // Vérification finale de la configuration régulière
        boolean configurationReguliereHeuristic = true;
        for (Constraint contrainte : toutesLesContraintes) {
            if (!contrainte.isSatisfiedBy(heuristicSolution)) {
                configurationReguliereHeuristic = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }

        // Affichage du résultat final
        if (configurationReguliereHeuristic) {
            System.out.println("La configuration trouvé est régulière.");
        } else {
            System.out.println("La configuration trouvé n'est pas régulière.");
        }
    //////////////////////////////////////////////////////////////////////////////////////////////
    // ---- Exemple de contraintes demandant une configuration croissante ----
        System.out.println("\n\n");
        System.out.println("***********Exemple de contraintes demandant une configuration croissante :");
        int nbBloc2 = 5 ; 
        int nbPile2 = 3 ; 
        // Création du monde des blocs avec contraintes de configuration croissante
        ContraintesConfigurationCroissante bw2 = new ContraintesConfigurationCroissante(nbBloc2, nbPile2);

        // Initialisation des variables et des contraintes
        List<Variable> onbList2 = new ArrayList<>(bw2.getVariablesOnb());
        List<BooleanVariable> fixedList2 = new ArrayList<>(bw2.getVariablesFixes());
        List<BooleanVariable> freepList2 = new ArrayList<>(bw2.getPilesLibres());

        // Génération des contraintes croissantes
        bw2.definirContraintesCroissantes();
        
        // Récupération des contraintes de base et des contraintes croissantes
        List<Constraint> contraintesDeBase2 = bw2.getContraintes();
        Set<Constraint> contraintesCroissantes = bw2.getContraintesCroissantes();
        


        // Combinaison des contraintes de base et croissantes
        Set<Constraint> toutesLesContraintes2 = new HashSet<>(contraintesDeBase2);
        toutesLesContraintes2.addAll(contraintesCroissantes);

        // Initialisation des variables pour les solveurs
        Set<Variable> toutesLesVariables2 = new HashSet<>();
        toutesLesVariables2.addAll(onbList2);
        toutesLesVariables2.addAll(fixedList2);
        toutesLesVariables2.addAll(freepList2);

        

        // ---- BacktrackSolver ----
        BacktrackSolver solverCroi = new BacktrackSolver(toutesLesVariables2, toutesLesContraintes2);
        long startTimeCroi = System.currentTimeMillis();
        Map<Variable, Object> solutionCroi = solverCroi.solve();
        long endTimeCroi = System.currentTimeMillis();
        long timeElapsedCroi = endTimeCroi - startTimeCroi;
        
        System.out.println("Temps de calcul pour le BacktrackSolver (configuration croissante) : " + timeElapsedCroi + " ms");
        if (solutionCroi != null) {
            System.out.println("Solution trouvée par le (BACKTRACKSOLVER) pour la configuration croissante : " + solutionCroi);
        } else {
            System.out.println("Aucune solution n'a été trouvée par le (BACKTRACKSOLVER) pour la configuration croissante.");
        }
        System.out.println("\n");
        boolean configurationCroissanteBacktrack = true;
        for (Constraint contrainte : toutesLesContraintes2) {
            if (!contrainte.isSatisfiedBy(solutionCroi)) {
                configurationCroissanteBacktrack = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }

        // Affichage du résultat final
        if (configurationCroissanteBacktrack) {
            System.out.println("La configuration trouvé est Croissante.");
        } else {
            System.out.println("La configuration trouvé n'est pas Croissante.");
        }
        System.out.println("\n");

        // ---- MACSolver ----
        MACSolver macSolverCroi = new MACSolver(toutesLesVariables2, toutesLesContraintes2);
        long startTimeMacCroi = System.currentTimeMillis();
        Map<Variable, Object> solutionMacCroi = macSolverCroi.solve();
        long endTimeMacCroi = System.currentTimeMillis();
        long timeElapsedMacCroi = endTimeMacCroi - startTimeMacCroi;
        
        System.out.println("Temps de calcul pour le MACSolver (configuration croissante) : " + timeElapsedMacCroi + " ms");
        if (solutionMacCroi != null) {
            System.out.println("Solution trouvée par le (MACSOLVER) pour la configuration croissante : " + solutionMacCroi);
        } else {
            System.out.println("Aucune solution n'a été trouvée par le (MACSOLVER) pour la configuration croissante.");
        }
        System.out.println("\n");
        boolean configurationCroissanteMacSolver = true;
        for (Constraint contrainte : toutesLesContraintes2) {
            if (!contrainte.isSatisfiedBy(solutionMacCroi)) {
                configurationCroissanteMacSolver = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }

        // Affichage du résultat final
        if (configurationCroissanteMacSolver) {
            System.out.println("La configuration trouvé est Croissante.");
        } else {
            System.out.println("La configuration trouvé n'est pas Croissante.");
        }
        System.out.println("\n");

        VariableHeuristic variableHeuristic2 = new DomainSizeVariableHeuristic(true);

        // Exemple avec NbConstraintsVariableHeuristic pour maximiser les contraintes
        // VariableHeuristic variableHeuristic = new NbConstraintsVariableHeuristic(toutesLesContraintes, true);

        // Instancier RandomValueHeuristic pour la sélection aléatoire de valeurs
        ValueHeuristic valueHeuristic2 = new RandomValueHeuristic(new Random());

        // Instancier le solveur avec heuristiques
        HeuristicMACSolver heuristicMACSolver2 = new HeuristicMACSolver(
                toutesLesVariables2,
                toutesLesContraintes2,
                variableHeuristic2,
                valueHeuristic2
        );

      
        long startTimeHeuristicCroi = System.currentTimeMillis();
        Map<Variable, Object> solutionHeuristicCroi = heuristicMACSolver2.solve();
        long endTimeHeuristicCroi = System.currentTimeMillis();
        long timeElapsedHeuristicCroi = endTimeMacCroi - startTimeMacCroi;
        
        System.out.println("Temps de calcul pour le HeuristicMacSolver (configuration croissante) : " + timeElapsedMacCroi + " ms");
        if (solutionMacCroi != null) {
            System.out.println("Solution trouvée par le (HeuristicMacSolver) pour la configuration croissante : " + solutionHeuristicCroi);
        } else {
            System.out.println("Aucune solution n'a été trouvée par le (HeuristicMACSolver) pour la configuration croissante.");
        }
        System.out.println("\n");
        boolean configurationCroissanteHeuristicMacSolver = true;
        for (Constraint contrainte : toutesLesContraintes2) {
            if (!contrainte.isSatisfiedBy(solutionHeuristicCroi)) {
                configurationCroissanteHeuristicMacSolver = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }

        // Affichage du résultat final
        if (configurationCroissanteHeuristicMacSolver) {
            System.out.println("La configuration trouvé est Croissante.");
        } else {
            System.out.println("La configuration trouvé n'est pas Croissante.");
        }
   

        //////////////////////////////////////////////////////////////////////////////////////////////
// ---- Exemple de contraintes demandant une configuration régulière ET croissante ----
        System.out.println("\n\n");
        System.out.println("***********Exemple de contraintes demandant une configuration régulière ET croissante :");
        int nbBloc3 = 6 ; 
        int nbPile3 = 3 ; 
        // Création des deux mondes des blocs pour combiner les contraintes
        ContraintesConfigurationReguliere bwReguliere = new ContraintesConfigurationReguliere(nbBloc3, nbPile3);
        ContraintesConfigurationCroissante bwCroissante = new ContraintesConfigurationCroissante(nbBloc3, nbPile3);

        // Initialisation des variables
        List<Variable> onbListCombined = new ArrayList<>(bwReguliere.getVariablesOnb());
        onbListCombined.addAll(bwCroissante.getVariablesOnb());

        List<BooleanVariable> fixedListCombined = new ArrayList<>(bwReguliere.getVariablesFixes());
        fixedListCombined.addAll(bwCroissante.getVariablesFixes());

        List<BooleanVariable> freepListCombined = new ArrayList<>(bwReguliere.getPilesLibres());
        freepListCombined.addAll(bwCroissante.getPilesLibres());

        // Définir les contraintes régulières et croissantes
        bwReguliere.definirContraintesRegulieres();
        bwCroissante.definirContraintesCroissantes();

        // Combinaison des contraintes régulières et croissantes
        Set<Constraint> toutesLesContraintesCombinees = new HashSet<>(bwReguliere.getContraintes());
        toutesLesContraintesCombinees.addAll(bwReguliere.getContraintesRegulieres());
        toutesLesContraintesCombinees.addAll(bwCroissante.getContraintes());
        toutesLesContraintesCombinees.addAll(bwCroissante.getContraintesCroissantes());

        // Combinaison des variables
        Set<Variable> toutesLesVariablesCombinees = new HashSet<>();
        toutesLesVariablesCombinees.addAll(onbListCombined);
        toutesLesVariablesCombinees.addAll(fixedListCombined);
        toutesLesVariablesCombinees.addAll(freepListCombined);

        // ---- BacktrackSolver ----
        BacktrackSolver solverComb = new BacktrackSolver(toutesLesVariablesCombinees, toutesLesContraintesCombinees);
        long startTimeComb = System.currentTimeMillis();
        Map<Variable, Object> solutionComb = solverComb.solve();
        long endTimeComb = System.currentTimeMillis();
        long timeElapsedComb = endTimeComb - startTimeComb;

        System.out.println("Temps de calcul pour le BacktrackSolver (configuration combinée) : " + timeElapsedComb + " ms");
        if (solutionComb != null) {
            System.out.println("Solution trouvée par le (BACKTRACKSOLVER) pour la configuration combinée : " + solutionComb);
        } else {
            System.out.println("Aucune solution n'a été trouvée par le (BACKTRACKSOLVER) pour la configuration combinée.");
        }
        System.out.println("\n");
        boolean configurationCroissanteReguliereBackTrack = true;
        for (Constraint contrainte : toutesLesContraintesCombinees) {
            if (!contrainte.isSatisfiedBy(solutionComb)) {
                configurationCroissanteReguliereBackTrack = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }

        // Affichage du résultat final
        if (configurationCroissanteReguliereBackTrack) {
            System.out.println("La configuration trouvé est Croissante et Réguliere a la fois .");
        } else {
            System.out.println("La configuration trouvé n'est pas régulière ou n'est pas Croissante.");
        }
        System.out.println("\n");
        


        // ---- MACSolver ----
        MACSolver macSolverComb = new MACSolver(toutesLesVariablesCombinees, toutesLesContraintesCombinees);
        long startTimeMacComb = System.currentTimeMillis();
        Map<Variable, Object> solutionMacComb = macSolverComb.solve();
        long endTimeMacComb = System.currentTimeMillis();
        long timeElapsedMacComb = endTimeMacComb - startTimeMacComb;

        System.out.println("Temps de calcul pour le MACSolver (configuration combinée) : " + timeElapsedMacComb + " ms");
        if (solutionMacComb != null) {
            System.out.println("Solution trouvée par le (MACSOLVER) pour la configuration combinée : " + solutionMacComb);
        } else {
            System.out.println("Aucune solution n'a été trouvée par le (MACSOLVER) pour la configuration combinée.");
        }
        System.out.println("\n");

        boolean configurationCroissanteReguliereMacSolver = true;
        for (Constraint contrainte : toutesLesContraintesCombinees) {
            if (!contrainte.isSatisfiedBy(solutionMacComb)) {
                configurationCroissanteReguliereMacSolver = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }

        // Affichage du résultat final
        if (configurationCroissanteReguliereMacSolver) {
            System.out.println("La configuration trouvé est Croissante et Réguliere a la fois .");
        } else {
            System.out.println("La configuration trouvé n'est pas régulière ou n'est pas Croissante.");
        }
        System.out.println("\n");


        // Instancier le solveur avec heuristiques
        HeuristicMACSolver heuristicMACSolver3 = new HeuristicMACSolver(
                toutesLesVariablesCombinees,
                toutesLesContraintesCombinees,
                variableHeuristic2,
                valueHeuristic2
        );

      
        long startTimeHeuristicCroiReg = System.currentTimeMillis();
        Map<Variable, Object> solutionHeuristicCroiReg = heuristicMACSolver3.solve();
        long endTimeHeuristicCroiReg = System.currentTimeMillis();
        long timeElapsedHeuristicCroiReg = endTimeHeuristicCroiReg - startTimeHeuristicCroiReg;
        
        System.out.println("Temps de calcul pour le HeuristicMacSolver (configuration combinée ) : " + timeElapsedHeuristicCroiReg + " ms");
        if (solutionMacCroi != null) {
            System.out.println("Solution trouvée par le (HeuristicMacSolver) pour la configuration Combinée  : " + solutionHeuristicCroiReg);
        } else {
            System.out.println("Aucune solution n'a été trouvée par le (HeuristicMACSolver) pour la configuration Combinée .");
        }
        System.out.println("\n");
        boolean configurationCroissanteReguliereHeuristicMacSolver = true;
        for (Constraint contrainte : toutesLesContraintes2) {
            if (!contrainte.isSatisfiedBy(solutionHeuristicCroiReg)) {
                configurationCroissanteReguliereHeuristicMacSolver = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }

        // Affichage du résultat final
        if (configurationCroissanteReguliereHeuristicMacSolver) {
            System.out.println("La configuration trouvé est Croissante et Reguliere a la fois .");
        } else {
            System.out.println("La configuration trouvé n'est pas Croissante ou n'est pas reguliere .");
        }



        /// partie pour les affichages 
        displaySolutionReguliere("BacktrackSolver(Reguliere)", solution, bw, nbBloc1);
        displaySolutionReguliere("MACSolver(Reguliere)", macSolution, bw, nbBloc1);
        displaySolutionReguliere("HeuristicMACSolver(Reguliere)", heuristicSolution, bw, nbBloc1);

        // Exemple pour les configurations croissantes
        displaySolutionCroissante("BacktrackSolver (Croissante)", solutionCroi, bw2, nbBloc2);
        displaySolutionCroissante("MACSolver (Croissante)", solutionMacCroi, bw2, nbBloc2);
        displaySolutionCroissante("HeuristicMACSolver (Croissante)", solutionHeuristicCroi, bw2, nbBloc2);

        // Exemple pour les configurations régulières et croissantes combinées
        displaySolutionCombine("BacktrackSolver (Croissante Et Reguliere )", solutionComb, onbListCombined, nbBloc3);
        displaySolutionCombine("MACSolver (Croissante Et Reguliere )", solutionMacComb, onbListCombined, nbBloc3);
        displaySolutionCombine("HeuristicMACSolver (Croissante Et Reguliere )", solutionHeuristicCroiReg, onbListCombined, nbBloc3);
        


    }
       // Méthode pour afficher la configuration dans une fenêtre
    public static void displaySolutionReguliere(String solverName, Map<Variable, Object> solution, 
                                       ContraintesConfigurationReguliere config, int nbBlocks) {
        if (solution != null) {
            BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(nbBlocks);
            for (int b = 0; b < nbBlocks; b++) {
                Variable onB = config.getVariablesOnb().get(b); // Get instance of Variable for "on_b"
                int under = (int) solution.get(onB);
                if (under >= 0) { // If the value is a block (as opposed to a stack)
                    builder.setOn(b, under);
                }
            }
            BWState<Integer> state = builder.getState();

            BWIntegerGUI gui = new BWIntegerGUI(nbBlocks);

            // Création d'une fenêtre pour afficher la configuration
            JFrame frame = new JFrame("Configuration trouvée par " + solverName);
            BWComponent<Integer> component = gui.getComponent(state);
            frame.add(component);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null); // Centrer la fenêtre
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement la fenêtre en cours
            frame.setVisible(true);
        } else {
            System.out.println("Aucune solution trouvée pour le solveur : " + solverName);
        }
    }

    public static void displaySolutionCroissante(String solverName, Map<Variable, Object> solution, 
                                       ContraintesConfigurationCroissante config, int nbBlocks) {
        if (solution != null) {
            BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(nbBlocks);
            for (int b = 0; b < nbBlocks; b++) {
                Variable onB = config.getVariablesOnb().get(b); // Get instance of Variable for "on_b"
                int under = (int) solution.get(onB);
                if (under >= 0) { // If the value is a block (as opposed to a stack)
                    builder.setOn(b, under);
                }
            }
            BWState<Integer> state = builder.getState();

            BWIntegerGUI gui = new BWIntegerGUI(nbBlocks);

            // Création d'une fenêtre pour afficher la configuration
            JFrame frame = new JFrame("Configuration trouvée par " + solverName);
            BWComponent<Integer> component = gui.getComponent(state);
            frame.add(component);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null); // Centrer la fenêtre
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement la fenêtre en cours
            frame.setVisible(true);
        } else {
            System.out.println("Aucune solution trouvée pour le solveur : " + solverName);
        }
    }


    public static void displaySolutionCombine(String solverName, Map<Variable, Object> solution, 
                                       List<Variable> varsOnb, int nbBlocks) {
        if (solution != null) {
            BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(nbBlocks);
            for (int b = 0; b < nbBlocks; b++) {
                Variable onB = varsOnb.get(b); // Get instance of Variable for "on_b"
                int under = (int) solution.get(onB);
                if (under >= 0) { // If the value is a block (as opposed to a stack)
                    builder.setOn(b, under);
                }
            }
            BWState<Integer> state = builder.getState();

            BWIntegerGUI gui = new BWIntegerGUI(nbBlocks);

            // Création d'une fenêtre pour afficher la configuration
            JFrame frame = new JFrame("Configuration trouvée par " + solverName);
            BWComponent<Integer> component = gui.getComponent(state);
            frame.add(component);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null); // Centrer la fenêtre
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement la fenêtre en cours
            frame.setVisible(true);
        } else {
            System.out.println("Aucune solution trouvée pour le solveur : " + solverName);
        }
    }

}