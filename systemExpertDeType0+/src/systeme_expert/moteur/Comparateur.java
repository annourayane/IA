package systeme_expert.moteur;


public class Comparateur {
    private String representation;

    public Comparateur(String representation) {
        this.representation = representation;
    }

    public String lireRepresentation() {
        return this.representation;
    }

    public boolean comparer(double valeur, double valeur2) {
        
      // une structure switch-case pour gérer les différentes comparaisons
        
        switch (this.representation) {
            case "=":
                return valeur == valeur2;
            case "!=":
                return valeur != valeur2;
            case ">":
                return valeur > valeur2;
            case "<":
                return valeur < valeur2;
            case ">=":
                return valeur >= valeur2;
            case "<=":
                return valeur <= valeur2;
            default:
                throw new IllegalArgumentException("Comparateur non valide : " + this.representation);
        }
    }
}

