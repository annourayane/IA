
package blocksworld;

import java.util.*;
import modelling.*;
import datamining.*;
import bwgeneratordemo.Demo;

public class App4 {
    public static void main(String[] args) {
        // Étape 1 : Création du gestionnaire
        GestionnaireVariables gestionnaire = new GestionnaireVariables(5, 3);

        // Étape 2 : Création de la base de données booléenne
        Set<BooleanVariable> toutesLesVariables = gestionnaire.getToutesLesVariables();
        BooleanDatabase base = new BooleanDatabase(toutesLesVariables);

        // Utilisation de la librairie bwgenerator pour générer des états aléatoires
        Random random = new Random();
        int nombreInstances = 10000;
        for (int i = 0; i < nombreInstances; i++) {
            // Dessine un état aléatoire
            List<List<Integer>> state = Demo.getState(random);

            // Convertit l'état en une instance booléenne
            Set<BooleanVariable> instance = gestionnaire.getInstance(state);

            // Ajoute l'instance à la base de données
            base.add(instance);
        }

        System.out.println("Base de données générée avec " + base.getTransactions().size() + " transactions.");

        // Étape 3 : Extraction des motifs fréquents
        Apriori apriori = new Apriori(base);
        float frequenceMinimale = 2.0f / 3.0f;  // Fréquence minimale de 2/3
        Set<Itemset> motifsFrequents = apriori.extract(frequenceMinimale);

        System.out.println("\nMotifs fréquents (fréquence >= " + frequenceMinimale + "):");
        for (Itemset motif : motifsFrequents) {
            System.out.println(motif);
        }

        // Étape 4 : Extraction de règles d'association
        BruteForceAssociationRuleMiner miner = new BruteForceAssociationRuleMiner(base);
        float minFrequency = 2.0f / 3.0f;  // Fréquence minimale de 2/3
        float minConfidence = 0.95f;  // Confiance minimale de 95%

        // Extraction des règles d'association
        Set<AssociationRule> regles = miner.extract(minFrequency, minConfidence);

        // Affichage des règles d'association extraites
        System.out.println("\nRègles d'association (fréquence >= " + minFrequency + " et confiance >= " + minConfidence + "):");
        for (AssociationRule regle : regles) {
            System.out.println("Prémisse : " + regle.getPremise() + " => Conclusion : " + regle.getConclusion() +
                    " | Fréquence : " + regle.getFrequency() + " | Confiance : " + regle.getConfidence());
        }
    }
}

 