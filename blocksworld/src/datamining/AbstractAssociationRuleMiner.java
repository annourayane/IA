package datamining;

import modelling.*;
import java.util.*; 

public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {
    
    protected BooleanDatabase baseDeDonnees;

    /**
     * Constructeur pour la classe AbstractAssociationRuleMiner
     * @param baseDeDonnees La base de données booléenne utilisée pour l'extraction des règles d'association
     */
    public AbstractAssociationRuleMiner(BooleanDatabase baseDeDonnees) {
        this.baseDeDonnees = baseDeDonnees;
    }

    /**
     * Retourne la base de données associée à cet objet.
     * @return Une instance de BooleanDatabase représentant la base de données utilisée
     */
    @Override
    public BooleanDatabase getDatabase() {
        return this.baseDeDonnees;
    }

    /**
     * Méthode statique qui retourne la fréquence d'un ensemble d'items tel qu'il est stocké dans 
     * un ensemble d'itemsets fréquents.
     * @param items L'ensemble d'items pour lequel on souhaite connaître la fréquence.
     * @param itemsets L'ensemble d'itemsets fréquents, où chaque itemset contient ses items et leur fréquence.
     * @return La fréquence de l'ensemble d'items.
     * @throws IllegalArgumentException Si l'ensemble d'items n'est pas trouvé dans les itemsets.
     */
    public static float frequency(Set<BooleanVariable> items, Set<Itemset> itemsets) {
    for (Itemset itemset : itemsets) {
        // Vérifie si l'itemset correspond exactement à l'ensemble d'items
        if (itemset.getItems().equals(items)) {
            return itemset.getFrequency();
        }
    }
    // Si l'ensemble d'items n'est pas trouvé, lève une exception avec un message descriptif
    throw new IllegalArgumentException("L'ensemble d'items n'est pas présent dans les itemsets fréquents");
    }


    /**
     * Confiance=freˊquence(preˊmisse ∪ conclusion)​ / freˊquence(preˊmisse)
     * Méthode statique pour calculer la confiance d'une règle d'association.
     * @param premisse L'ensemble d'items représentant la prémisse de la règle.
     * @param conclusion L'ensemble d'items représentant la conclusion de la règle.
     * @param itemsets L'ensemble des itemsets fréquents, contenant les fréquences des itemsets.
     * @return La confiance de la règle d'association sous forme de float.
     * @throws IllegalArgumentException Si la fréquence de l'union de prémisse et conclusion
     * ou la fréquence de la prémisse ne peuvent être trouvées dans les itemsets.
     */
    public static float confidence(Set<BooleanVariable> premisse, Set<BooleanVariable> conclusion, Set<Itemset> itemsets) {
        // Créer l'union de la prémisse et de la conclusion
        Set<BooleanVariable> unionPremisseConclusion = new HashSet<>(premisse);
        unionPremisseConclusion.addAll(conclusion);

        // Calculer la fréquence de la prémisse
        float freqPremisse = frequency(premisse, itemsets);
        
        // Calculer la fréquence de l'union de la prémisse et de la conclusion
        float freqUnion = frequency(unionPremisseConclusion, itemsets);

        // Calculer la confiance
        return freqUnion / freqPremisse;
    }



    /**
     * une méthode qui extrait les règles d'association selon une fréquence et une confiance minimales.
     * @param frequenceMin La fréquence minimale pour les règles d'association
     * @param confianceMin La confiance minimale pour les règles d'association
     * @return Un ensemble de règles d'association répondant aux critères de fréquence et de confiance
     */
    @Override
    public abstract Set<AssociationRule> extract(float frequenceMin, float confianceMin);
}
