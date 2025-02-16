package systeme_expert.moteur;
public class PremisseEntiere extends Premisse {

    private ExpressionArithmetique valeur;
    private Comparateur comparateur;


    /**
    * Constructeur de la premisse
    * @param symbole  est le symbole de la premisse .
    * @param valeur   est la valeur de cette premisse .
    * 
    */

    public PremisseEntiere(String symbole, Comparateur comparateur, ExpressionArithmetique valeur) {
        super(symbole);
        this.comparateur = comparateur;
        this.valeur = valeur;
    }
    /**
    * Méthode pour obtenir la valeur de la premisse entier
    */
    public ExpressionArithmetique getValeur() {
        return valeur;
    }

    /**
    * Méthode pour obtenir le comparatuer de la premisse entier
    */
    public Comparateur getComparateur() {
        return comparateur;
    }
    /**
    * Méthode pour vérifier si la prémisse entiere est vérifiée dans la base de faits
    */
    
    @Override
    public boolean estVerifiee(BaseDeFaits baseFaits) {
        if (!baseFaits.existe(getSymbole())) {
        // Le fait n'existe pas dans la base de faits, donc la prémisse n'est pas vérifiée
        return false;
     }

     Fait fait = baseFaits.rechercherFait(getSymbole());

    
        if (!(fait instanceof FaitEntier)) {
        // Gérer le cas où le fait n'est pas de type FaitEntier
        return false; 
        }

    // Convertir le fait en FaitEntier
        FaitEntier faitEntier = (FaitEntier) fait;

    // Comparaison de la valeur du fait avec la valeur attendue de la prémisse
        Comparateur comparateur = this.getComparateur();
        double valeurAttendue = this.getValeur().evaluer();
        double valeurFait = baseFaits.getEntier(getSymbole()); 
        

      
        if (valeurAttendue != 0) {
            return comparateur.comparer(valeurFait, valeurAttendue);
         } else {
        // Gérer le cas où le dénominateur est zéro
            return false;
         }
    
    }
    /**
    * Méthode pour convertir une premisse entier  vers un fait entier
    */
    @Override
     public  Fait convert () {
       return new FaitEntier(super.getSymbole(),this.valeur);
     }
    /**
     * Méthode toString pour obtenir une représentation textuelle de la premisse

   */
    @Override
    public String toString() {
        return symbole + " " + comparateur.lireRepresentation() + valeur;
    }
     
     



}
