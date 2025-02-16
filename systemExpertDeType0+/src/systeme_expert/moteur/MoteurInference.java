package systeme_expert.moteur;
import java.util.ArrayList;

public class MoteurInference {




     /**
     * Choisis une règle parmi les règles déclenchables.
     * 
     * @param declenchables Liste des règles déclenchables.
     * @return La première règle déclenchable de la liste ou une nouvelle règle vide si la liste est vide.

   */



    private static Regle choisirRegle(ArrayList<Regle> declenchables) {
        // Récupérer la première règle déclenchable dans la liste
        if (!declenchables.isEmpty()) {
            return declenchables.get(0);
        }
        return new RegleSansPremisses(null);    
    }




    /**
     * Effectue le chaînage avant sur une base de faits et une base de règles données.
     * 
     * @param baseFaits La base de faits.
     * @param baseRegles La base de règles.
   */


    public static void chainageAvant(BaseDeFaits baseFaits, ArrayList<Regle> baseRegles) {
        boolean continuer = true;
        // Initialisation : Marquer toutes les regles comme activables
        for (Regle regle : baseRegles) {
            regle.setActivable(true);
        }

        while (continuer) {

            ArrayList<Regle> declenchables = new ArrayList<>();

            for (Regle regle : baseRegles) {

                if (regle.activable == true) {

                    boolean declenchable = regle.estDeclenchable(baseFaits); 
                    // Si la règle est déclenchable on va l'ajouter à la liste des règles déclenchables
                    if (declenchable) {
                        declenchables.add(regle);
                    } 

                }
            
            }

            // Si aucune règle n'est déclenchable, on termine
            if (declenchables.isEmpty()) {
                continuer = false;
                break;
            }


        
            Regle regleChoisi = choisirRegle(declenchables);
            //  pour eviter de passer plusiers fois sur une regle
            regleChoisi.setActivable(false); 
            regleChoisi.executer(baseFaits);

        }


    }

    /**
     * Effectue le chainage arrière pour déterminer si le fait donné peut être prouvé dans la base de faits
     * en utilisant la base de règles donnée.
     * 
     * @param baseFaits La base de faits.
     * @param baseRegles La base de règles.
     * @param f Le fait à prouver.
     * @return Vrai si le fait peut être prouvé, faux sinon.
 
    */
 
    public static boolean chainageArriere(BaseDeFaits baseFaits, ArrayList<Regle> baseRegles, Fait f) {
        // Si le fait f est déjà dans la base de faits, renvoyer vrai
        if (baseFaits.existe(f.getSymbole())) {
            Fait faitbase = baseFaits.rechercherFait(f.getSymbole());
            if (f.getValeur()==faitbase.getValeur()){
                return true;
            }
            
        }

        for (Regle regle : baseRegles) {
            regle.setActivable(true);
        }

        ArrayList<Regle> conflits = new ArrayList<>();
        for (Regle regle : baseRegles) {
            if (regle.activable == true && regle.getConclusion() != null
                    && (regle.getConclusion().getSymbole().equals(f.getSymbole()))
                    &&(regle.getConclusion().getValeur().equals(f.getValeur()))

                    && (regle.estDeclenchable(baseFaits))) {        
                conflits.add(regle);
            }
                    // Recherche des règles activables
        }

        if (conflits.isEmpty()) {
            return false;
        }

        Regle regleChoisi = choisirRegle(conflits);
        // pour eviter de passer plusiers fois sur une regle
        regleChoisi.setActivable(false);
        boolean resultatConjonction = true;

        for (Premisse premisse : regleChoisi.getPremisses()) {

                Fait fact = premisse.convert();

                resultatConjonction = resultatConjonction && chainageArriere(baseFaits, baseRegles, fact);
           

        }

        return resultatConjonction;

    }


}











