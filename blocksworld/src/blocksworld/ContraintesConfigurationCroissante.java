package blocksworld;

import java.util.*;
import modelling.*;

public class ContraintesConfigurationCroissante extends MondeDesBlocsContraintes {
    public Set<Constraint> contraintesCroissantes;

    public ContraintesConfigurationCroissante(int nombreDeBlocs, int nombreDePiles) {
        super(nombreDeBlocs, nombreDePiles);
        this.contraintesCroissantes = new HashSet<>();
        definirContraintesCroissantes();  // Définir les contraintes de croissante
    }

    public Set<Constraint> definirContraintesCroissantes() {
    // Créer les contraintes croissantes pour chaque bloc
    for (Variable bloc1 : this.getVariablesOnb()) {
        int numBloc1 = Integer.parseInt(bloc1.getName().substring(2));

        // Contrainte Unary : le bloc peut être placé uniquement sur la table ou sur un bloc plus petit
        Set<Object> domaineBloc1 = new HashSet<>();
        for (Variable pileLibre : this.getPilesLibres()) {
            domaineBloc1.add(-Integer.parseInt(pileLibre.getName().substring(4))); // Ajouter les piles libres (table)
        }

        for (Variable bloc2 : this.getVariablesOnb()) {
            int numBloc2 = Integer.parseInt(bloc2.getName().substring(2));
            if (numBloc2 < numBloc1) {
                domaineBloc1.add(numBloc2); // Ajouter les blocs plus petits au domaine
            }
        }

        // Créer la contrainte Unary pour limiter les positions de bloc1
        UnaryConstraint unaryConstraint = new UnaryConstraint(bloc1, domaineBloc1);
        contraintesCroissantes.add(unaryConstraint);

        // Créer les contraintes d'implication pour chaque pair (bloc1, bloc2) avec numBloc1 > numBloc2
        for (Variable bloc2 : this.getVariablesOnb()) {
            int numBloc2 = Integer.parseInt(bloc2.getName().substring(2));

            if (numBloc1 > numBloc2) {
                // Domaine cible pour bloc2 dans l'implication (bloc2 doit être dans un domaine croissant)
                Set<Object> domaineImplication = new HashSet<>(domaineBloc1); // Copie du domaine de bloc1
                domaineImplication.add(numBloc2); // Ajouter le bloc2 au domaine pour assurer croissance

                // Si bloc1 est sur bloc2, alors bloc2 doit être sur une valeur plus basse ou la table
                Implication implicationConstraint = new Implication(bloc1, Set.of(numBloc2), bloc2, domaineImplication);
                contraintesCroissantes.add(implicationConstraint);
            }
        }
    }

    return contraintesCroissantes;
    }



    // Getter pour récupérer les contraintes croissantes
    public Set<Constraint> getContraintesCroissantes() {
        return contraintesCroissantes;
    }
}
