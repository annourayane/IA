package datamining;

import modelling.*;
import java.util.*;

public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner {
    /**
     * Constructeur pour la classe BruteForceAssociationRuleMiner.
     * @param baseDeDonnees La base de données booléenne utilisée pour l'extraction des règles d'association.
     */
    public BruteForceAssociationRuleMiner(BooleanDatabase baseDeDonnees) {
        // Appelle le constructeur de la classe parente AbstractAssociationRuleMiner
        super(baseDeDonnees);
    }



     /**
     * Génère tous les sous-ensembles d'un ensemble d'items donné, sauf l'ensemble vide et l'ensemble lui-même.
     * @param items L'ensemble d'items pour lequel on souhaite générer les sous-ensembles.
     * @return Un ensemble de sous-ensembles d'items, sans l'ensemble vide et sans l'ensemble complet.
     */
    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> items) {
    Set<Set<BooleanVariable>> result = new HashSet<>(); // Ensemble qui contiendra tous les sous-ensembles
    generateSubsets(new ArrayList<>(items), new HashSet<>(), result, 0); // Appel initial à la méthode récursive
    
    result.remove(items); // Retirer l'ensemble complet, car il ne doit pas être un sous-ensemble candidat
    result.remove(Collections.emptySet()); // Retirer l'ensemble vide, car il ne peut pas être une prémisse significative
    
    return result; // Retourne l'ensemble des sous-ensembles candidats
    }

    private static void generateSubsets(List<BooleanVariable> items, Set<BooleanVariable> currentSubset, 
                                    Set<Set<BooleanVariable>> result, int index) {
    // Condition de terminaison : lorsque l'index atteint la taille de l'ensemble initial, 
    // on a parcouru tous les éléments et formé un sous-ensemble complet
    if (index == items.size()) {
        result.add(new HashSet<>(currentSubset)); // Ajouter une copie du sous-ensemble courant à l'ensemble des résultats
        return; // Fin de l'appel récursif pour cet index
    }

    // Cas où on inclut l'élément courant dans le sous-ensemble
    currentSubset.add(items.get(index)); // Ajout de l'élément courant
    generateSubsets(items, currentSubset, result, index + 1); // Appel récursif avec l'élément inclus

    // Cas où on exclut l'élément courant du sous-ensemble
    currentSubset.remove(items.get(index)); // Suppression de l'élément pour explorer l'autre possibilité
    generateSubsets(items, currentSubset, result, index + 1); // Appel récursif avec l'élément exclu
    }

    /**
     * Méthode qui extrait les règles d'association répondant aux critères de fréquence et de confiance minimales.
     * Cette méthode doit être implémentée pour effectuer l'extraction en utilisant la force brute.
     * @param frequenceMin La fréquence minimale pour les règles d'association.
     * @param confianceMin La confiance minimale pour les règles d'association.
     * @return Un ensemble de règles d'association répondant aux critères de fréquence et de confiance.
     */


    @Override

    public Set<AssociationRule> extract(float minFrequency, float minConfidence) {

        Set<AssociationRule> regles = new HashSet<>();
        Apriori apriori = new Apriori(baseDeDonnees); 
        
        Set<Itemset> frequentItemsets = apriori.extract(minFrequency);

       
        for (Itemset itemset : frequentItemsets) {

            Set<BooleanVariable> items = itemset.getItems();

           
            Set<Set<BooleanVariable>> premises = allCandidatePremises(items); //sous ensembles de items

            for (Set<BooleanVariable> premise : premises) {
                Set<BooleanVariable> conclusion = new HashSet<>(items);
                conclusion.removeAll(premise); //ENLEVERl premisse de l'ensemble 

                float frequency = AbstractAssociationRuleMiner.frequency(items,frequentItemsets);
                float confidence = AbstractAssociationRuleMiner.confidence(premise,conclusion,frequentItemsets);

                if (frequency >= minFrequency && confidence >= minConfidence) {
                    regles.add(new AssociationRule(premise, conclusion, frequency, confidence));
                }
            }
        }

        return regles;
    }




}
