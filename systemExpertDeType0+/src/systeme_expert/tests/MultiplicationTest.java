package systeme_expert.tests;
import systeme_expert.moteur.*; 
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MultiplicationTest {
	/**
	une méthode qui teste la méthode evaluer() de la classe Multiplication
	
	*/ 
    @Test
    public void testEvaluer() {
        System.out.println("Test launched...");

        // Cas de multiplication normale
        ExpressionArithmetique gauche1 = new Valeur(5);
        ExpressionArithmetique droite1 = new Valeur(3);
        Multiplication multiplication1 = new Multiplication(gauche1, droite1);
        assertEquals(15.0, multiplication1.evaluer(), 0.001);

        // Cas de multiplication avec un nombre négatif
        ExpressionArithmetique gauche2 = new Valeur(-10);
        ExpressionArithmetique droite2 = new Valeur(4);
        Multiplication multiplication2 = new Multiplication(gauche2, droite2);
        assertEquals(-40.0, multiplication2.evaluer(), 0.001);

        // Cas de multiplication avec zéro
        ExpressionArithmetique gauche3 = new Valeur(0);
        ExpressionArithmetique droite3 = new Valeur(7);
        Multiplication multiplication3 = new Multiplication(gauche3, droite3);
        assertEquals(0.0, multiplication3.evaluer(), 0.001);

        System.out.println("Test passed.");
    }
}

