package systeme_expert.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import systeme_expert.moteur.*; 
public class AdditionTest {
		
		/** 
		  une méthode qui teste la méthode evaluer() de la classe Addition 
		  
		 */ 

    @Test
    public void testEvaluer() {
        System.out.println("Test launched...");

        // Cas d'addition normale
        ExpressionArithmetique gauche1 = new Valeur(5);
        ExpressionArithmetique droite1 = new Valeur(3);
        Addition addition1 = new Addition(gauche1, droite1);
        assertEquals(8.0, addition1.evaluer(), 0.001);

        // Cas d'addition avec un nombre négatif
        ExpressionArithmetique gauche2 = new Valeur(-10);
        ExpressionArithmetique droite2 = new Valeur(20);
        Addition addition2 = new Addition(gauche2, droite2);
        assertEquals(10.0, addition2.evaluer(), 0.001);

        // Cas d'addition avec zéro
        ExpressionArithmetique gauche3 = new Valeur(0);
        ExpressionArithmetique droite3 = new Valeur(7);
        Addition addition3 = new Addition(gauche3, droite3);
        assertEquals(7.0, addition3.evaluer(), 0.001);

        System.out.println("Test passed.");
    }
}

