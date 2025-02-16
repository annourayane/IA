package systeme_expert.tests;
import systeme_expert.moteur.*; 

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ValeurTest {
	/**
	une méthode qui teste la méthode evaluer() de la classe Valeur 
	
	*/ 
    @Test
    public void testEvaluer() {
        System.out.println("Test launched...");

        // Création d'une valeur positive
        double valeurPositive = 10.5;
        Valeur valeur1 = new Valeur(valeurPositive);
        assertEquals(valeurPositive, valeur1.evaluer(), 0.001);

        // Création d'une valeur négative
        double valeurNegative = -7.2;
        Valeur valeur2 = new Valeur(valeurNegative);
        assertEquals(valeurNegative, valeur2.evaluer(), 0.001);

        // Création d'une valeur nulle
        double valeurNulle = 0.0;
        Valeur valeur3 = new Valeur(valeurNulle);
        assertEquals(valeurNulle, valeur3.evaluer(), 0.001);

        System.out.println("Test passed.");
    }
}

