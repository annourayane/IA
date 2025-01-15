package blocksworld;

import java.util.*; 
import modelling.*; 

public class App {
    public static void main(String[] args){
        // Création du monde des blocs
        ContraintesConfigurationReguliere bw = new ContraintesConfigurationReguliere(11, 6); 
        Map<Variable, Object> assignment = new LinkedHashMap<Variable, Object>(); 
        
        // Initialisation des variables Onb
        List<Variable> onbList = new ArrayList<>(bw.getVariablesOnb()); 
        for (int i = 0; i < onbList.size(); i++) {
            System.out.println("get(" + i + ") : " + onbList.get(i)); 
        }
        

        // exemple d'une configuration 
        
        assignment.put(onbList.get(0), -2);
        assignment.put(onbList.get(1), -1);
        assignment.put(onbList.get(2), 0);
        assignment.put(onbList.get(3), 1);
        assignment.put(onbList.get(4), 2);
        assignment.put(onbList.get(5), -4);
        assignment.put(onbList.get(6), 4);
        assignment.put(onbList.get(7), 5);
        assignment.put(onbList.get(8), 6);
        assignment.put(onbList.get(9), -3);
        assignment.put(onbList.get(10), 9);

        // Initialisation des variables Fixes
        List<BooleanVariable> fixedList = new ArrayList<>(bw.getVariablesFixes()); 
        for (int i = 0; i < fixedList.size(); i++) {
            System.out.println("get(" + i + ") : " + fixedList.get(i)); 
        }
        

        // Affectation des valeurs aux variables Fixes
        assignment.put(fixedList.get(0), true); 
        assignment.put(fixedList.get(1), true);
        assignment.put(fixedList.get(2), true);
        assignment.put(fixedList.get(3), false);
        assignment.put(fixedList.get(4), true);
        assignment.put(fixedList.get(5), false);
        assignment.put(fixedList.get(6), true);
        assignment.put(fixedList.get(7), false);
        assignment.put(fixedList.get(8), false);
        assignment.put(fixedList.get(9), true);
        assignment.put(fixedList.get(10), false);

        // Initialisation des variables Freep (Piles libres)
        List<BooleanVariable> freepList = new ArrayList<>(bw.getPilesLibres()); 
        for (int i = 0; i < freepList.size(); i++) {
            System.out.println("get(" + i + ") : " + freepList.get(i));
        }

        // Affectation des valeurs aux variables Freep
        if (freepList.size() == 6) {  // Assurez-vous qu'il y a bien 6 éléments
            
            assignment.put(freepList.get(0), false);
            assignment.put(freepList.get(1), false);
            assignment.put(freepList.get(2), false);
            assignment.put(freepList.get(3), false);
            assignment.put(freepList.get(4), true);
            assignment.put(freepList.get(5), true);
        } else {
            System.out.println("Erreur : Nombre de piles libres inattendu");
        }
         
        // Génération des contraintes de régularité
        bw.definirContraintesRegulieres();
        Set<Constraint> contraintesRegulieres = bw.contraintesRegulieres;

        // Test de chaque contrainte pour voir si la configuration est régulière
        boolean configurationReguliere = true;
        for (Constraint contrainte : contraintesRegulieres) {
            if (!contrainte.isSatisfiedBy(assignment)) {
                configurationReguliere = false;
                System.out.println("Contrainte non satisfaite : " + contrainte);
            }
        }

        // Affichage du résultat final
        if (configurationReguliere) {
            System.out.println("La configuration est régulière.");
        } else {
            System.out.println("La configuration n'est pas régulière.");
        }

        // Test des contraintes croissantes
        ContraintesConfigurationCroissante contraintesCroissantes = new ContraintesConfigurationCroissante(11, 6);
        Set<Constraint> contraintesCroissantesSet = contraintesCroissantes.getContraintesCroissantes();
        
        // Vérification des contraintes croissantes
        boolean configurationCroissante = true;
        for (Constraint contrainte : contraintesCroissantesSet) {
            if (!contrainte.isSatisfiedBy(assignment)) {
                configurationCroissante = false;
                System.out.println("Contrainte croissante non satisfaite : " + contrainte);
            }
        }
                // Affichage du résultat final
        if (configurationCroissante) {
            System.out.println("La configuration est Croissante.");
        } else {
            System.out.println("La configuration n'est pas Croissante.");
        }
    }
}
