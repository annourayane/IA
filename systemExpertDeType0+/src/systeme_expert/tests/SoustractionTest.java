package systeme_expert.tests;
import systeme_expert.moteur.*; 
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SoustractionTest {
	/**
	une méthode qui teste la méthode evaluer() de la classe Soustraction 
	
	*/

    @Test
    public void testEvaluer() {
        System.out.println("Test launched...");

        // Cas de soustraction normale
        ExpressionArithmetique gauche1 = new Valeur(10);
        ExpressionArithmetique droite1 = new Valeur(2);
        Soustraction soustraction1 = new Soustraction(gauche1, droite1);
        assertEquals(8.0, soustraction1.evaluer(), 0.001);

        // Cas de soustraction avec un nombre négatif
        ExpressionArithmetique gauche2 = new Valeur(5);
        ExpressionArithmetique droite2 = new Valeur(10);
        Soustraction soustraction2 = new Soustraction(gauche2, droite2);
        assertEquals(-5.0, soustraction2.evaluer(), 0.001);

        // Cas de soustraction avec zéro
        ExpressionArithmetique gauche3 = new Valeur(0);
        ExpressionArithmetique droite3 = new Valeur(7);
        Soustraction soustraction3 = new Soustraction(gauche3, droite3);
        assertEquals(-7.0, soustraction3.evaluer(), 0.001);

        System.out.println("Test passed.");
    }
}
