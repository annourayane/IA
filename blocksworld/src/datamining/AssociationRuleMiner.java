package datamining;

import java.util.Set;
import modelling.*;

public interface AssociationRuleMiner {
    
    /**
     * Retourne la base de données booléenne associée au miner de règles d'association.
     * @return Une instance de BooleanDatabase représentant la base de données utilisée
     */
    BooleanDatabase getDatabase();

    /**
     * Extrait l'ensemble des règles d'association ayant une fréquence et une confiance
     * supérieures aux seuils minimaux spécifiés.
     * @param frequenceMin Le seuil minimal de fréquence pour les règles d'association
     * @param confianceMin Le seuil minimal de confiance pour les règles d'association
     * @return Un ensemble de règles d'association (Set<AssociationRule>) satisfaisant les seuils de fréquence et de confiance
     */
    Set<AssociationRule> extract(float frequenceMin, float confianceMin);
}
