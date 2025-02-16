package systeme_expert.tests;
import systeme_expert.moteur.*; 
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ComparateurTest {
		/**
		une méthode qui teste la méthode comparer() de la classe Comparateur 
		
		
		*/
    @Test
    public void testComparer() {
        System.out.println("Test launched...");

        // Création de comparateurs avec différentes représentations
        Comparateur comparateur1 = new Comparateur("=");
        Comparateur comparateur2 = new Comparateur("!=");
        Comparateur comparateur3 = new Comparateur(">");
        Comparateur comparateur4 = new Comparateur("<");
        Comparateur comparateur5 = new Comparateur(">=");
        Comparateur comparateur6 = new Comparateur("<=");

        // Test de chaque comparateur avec des valeurs différentes
        assertTrue(comparateur1.comparer(5.0, 5.0));
        assertFalse(comparateur1.comparer(5.0, 10.0));

        assertTrue(comparateur2.comparer(5.0, 10.0));
        assertFalse(comparateur2.comparer(5.0, 5.0));

        assertTrue(comparateur3.comparer(10.0, 5.0));
        assertFalse(comparateur3.comparer(5.0, 10.0));

        assertTrue(comparateur4.comparer(5.0, 10.0));
        assertFalse(comparateur4.comparer(10.0, 5.0));

        assertTrue(comparateur5.comparer(10.0, 5.0));
        assertTrue(comparateur5.comparer(5.0, 5.0));
        assertFalse(comparateur5.comparer(5.0, 10.0));

        assertTrue(comparateur6.comparer(5.0, 10.0));
        assertTrue(comparateur6.comparer(5.0, 5.0));
        assertFalse(comparateur6.comparer(10.0, 5.0));

        System.out.println("Test passed.");
    }
}

