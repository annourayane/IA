package datamining; 
/**
 * cette classe representera des extracteur fonctionnant sur le principe de l'algo apriori 
 */
import modelling.*; 
import java.util.*; 
public class Apriori extends AbstractItemsetMiner {
    public Apriori (BooleanDatabase base){
        super(base); 
    }

    /* 
    une methode prennant en argument une frequence et retournant 
    l'ensemble de tous les itemsets singletons(avec leur frequence)dans la frequence dans 
    la base est au moins égale a celle donné 
    */

   public Set<Itemset> frequentSingletons(float frequence) {
        Set<Itemset> singletonsFrequentItems = new HashSet<>();

        // Parcourir chaque item (variable booléenne) dans la base
        for (BooleanVariable item : base.getItems()) {
            // Créer un ensemble avec cet item unique (singleton)
            Set<BooleanVariable> singleton = new HashSet<>();
            singleton.add(item);

            // Calculer la fréquence de cet item unique dans la base
            float itemFrequence = frequency(singleton);

            // Vérifier si la fréquence de l'item est au moins égale à celle donnée
            if (itemFrequence >= frequence) {
                // Ajouter cet itemset à l'ensemble des items fréquents
                singletonsFrequentItems.add(new Itemset(singleton, itemFrequence));
            }
        }
        
        return singletonsFrequentItems;
   }
    /**
     *  une méthode statique combine, prenant en arguments deux en-
    sembles (triés) d’items (de type SortedSet<BooleanVariable>) et retournant un ensemble (trié) d’items
    (également de type SortedSet<BooleanVariable>)
     _les deux ensembles aient la même taille k,
     — les deux ensembles aient les mêmes k − 1 premiers items,
     — les deux ensembles aient des kes items différents.
    Dans tous les autres cas, la méthode devra renvoyer null.
     */
    public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> set1, SortedSet<BooleanVariable> set2) {
    // Vérifie que les ensembles ne sont pas vides
    if (set1.isEmpty() || set2.isEmpty() || set1.size() != set2.size()) {
        return null;
    }

    List<BooleanVariable> list1 = new ArrayList<>(set1);
    List<BooleanVariable> list2 = new ArrayList<>(set2);

    // Vérifie que les k-1 premiers items sont identiques
    for (int i = 0; i < list1.size() - 1; i++) {
        if (!list1.get(i).equals(list2.get(i))) {
            return null;
        }
    }

    // Vérifie que les derniers items sont différents
    if (list1.get(list1.size() - 1).equals(list2.get(list2.size() - 1))) {
        return null;
    }

    // Créer un nouvel ensemble combiné
    SortedSet<BooleanVariable> combinedSet = new TreeSet<>(AbstractItemsetMiner.COMPARATOR);
    combinedSet.addAll(set1);
    combinedSet.add(list2.get(list2.size() - 1)); // Ajoute le dernier item de set2

    return combinedSet;
    }


    /**
     * prenant en argu-
    ments un ensemble d’items de type Set<BooleanVariable> (en pratique, de taille k) et une collection
    d’ensembles d’items de type Collection<SortedSet<BooleanVariable>> (en pratique, les fréquents de taille k − 1), et retournant true si tous les sous-ensembles obtenus en supprimant exactement un élément
    de l’ensemble d’items sont contenus dans la collection ( false sinon). L’idée derrière cette méthode est
    que la propriété d’antimonotonie de la fréquence assure alors que les sous-ensembles propres de l’ensemble
    donné sont également fréquents
     */

    public static boolean allSubsetsFrequent(Set<BooleanVariable> items, Collection<SortedSet<BooleanVariable>> frequentItemsets) {
        // Itérer sur chaque item de l'ensemble donné
        for (BooleanVariable item : items) {
            // Créer un ensemble sans cet item
            Set<BooleanVariable> subset = new HashSet<>(items); // Clone l'ensemble d'origine
            subset.remove(item); // Supprime l'item courant pour créer le sous-ensemble
            
            // Vérifier si le sous-ensemble est contenu dans la collection des itemsets fréquents
            boolean found = false;
            for (SortedSet<BooleanVariable> frequentItemset : frequentItemsets) {
                if (frequentItemset.equals(subset)) { // Vérifie si le sous-ensemble correspond à un itemset fréquent
                    found = true; // Sous-ensemble trouvé
                    break; // Pas besoin de continuer à chercher
                }
            }
            // Si l'un des sous-ensembles n'est pas trouvé, retourner false
            if (!found) {
                return false; // Un sous-ensemble n'est pas fréquent
            }
        }
        return true; // Tous les sous-ensembles sont fréquents
    }


    /**
    * prend en argument une frequence et retournant l’ensemble des itemsets (non vides) ayant au moins cette fréquence dans
    la base.
    */
      // Méthode principale pour extraire les itemsets fréquents

    @Override
    public Set<Itemset> extract(float frequenceMinimale) {
    Set<Itemset> itemsetsFrequent = new HashSet<>(); // Ensemble des itemsets fréquents
    List<SortedSet<BooleanVariable>> niveauActuel = new ArrayList<>(); // Liste des itemsets de taille actuelle (k)

    // Initialiser avec les singletons fréquents (itemsets de taille 1)
    for (Itemset singleton : frequentSingletons(frequenceMinimale)) {
        SortedSet<BooleanVariable> itemsetTrie = new TreeSet<>(AbstractItemsetMiner.COMPARATOR);
        itemsetTrie.addAll(singleton.getItems());
        niveauActuel.add(itemsetTrie); // Ajouter le singleton à la liste de niveau actuel
        itemsetsFrequent.add(singleton); // Ajouter le singleton à l'ensemble des itemsets fréquents
    }

    // Boucle pour extraire les itemsets fréquents de tailles croissantes
    while (!niveauActuel.isEmpty()) {
        List<SortedSet<BooleanVariable>> niveauSuivant = new ArrayList<>(); // Liste des candidats pour le prochain niveau

        // Générer des candidats en combinant les itemsets de taille actuelle
        for (int i = 0; i < niveauActuel.size(); i++) {
            for (int j = i + 1; j < niveauActuel.size(); j++) {
                // Combinaison de deux itemsets pour créer un candidat de taille k+1
                SortedSet<BooleanVariable> candidat = combine(niveauActuel.get(i), niveauActuel.get(j));

                // Vérifier si le candidat est valide et si tous ses sous-ensembles sont fréquents
                if (candidat != null && allSubsetsFrequent(candidat, niveauActuel)) {
                    float frequence = frequency(candidat); // Calculer la fréquence du candidat
                    if (frequence >= frequenceMinimale) {
                        niveauSuivant.add(candidat); // Ajouter le candidat valide au niveau suivant
                        itemsetsFrequent.add(new Itemset(candidat, frequence)); // Ajouter le candidat à l'ensemble des itemsets fréquents
                    }
                }
            }
        }

        // Passer au niveau suivant (augmenter la taille des itemsets)
        niveauActuel = niveauSuivant;
    }

    return itemsetsFrequent;
    }

}