package systeme_expert.tests;
import systeme_expert.moteur.*; 
import org.junit.Test;
import static org.junit.Assert.*;

public class PremisseSymboliqueTest {
	/**
	une méthode qui teste la méthode estVerifiee() de la classe PremisseSymbolique 
	
	*/
    @Test
    public void testEstVerifiee() {
        System.out.println("Test launched...");
        // Création d'une base de faits
        BaseDeFaits baseDeFaits = new BaseDeFaits();
        // Ajout de plusieurs faits à la base de faits
        baseDeFaits.ajouterFait("X", new FaitSymbolique("X", "valeur1"));
        baseDeFaits.ajouterFait("Y", new FaitSymbolique("Y", "valeur2"));
        baseDeFaits.ajouterFait("Z", new FaitSymbolique("Z", "valeur3"));

        // Création de différentes prémisse symboliques avec des valeurs attendues
        PremisseSymbolique premisseX = new PremisseSymbolique("X", "valeur1");
        PremisseSymbolique premisseY = new PremisseSymbolique("Y", "valeur2");
        PremisseSymbolique premisseZ = new PremisseSymbolique("Z", "valeur3");
        PremisseSymbolique premisseF = new PremisseSymbolique("F", "valeur1");

        // Vérification si chaque prémisse est vérifiée dans la base de faits
        assertTrue(premisseX.estVerifiee(baseDeFaits));
        assertTrue(premisseY.estVerifiee(baseDeFaits));
        assertTrue(premisseZ.estVerifiee(baseDeFaits));
        assertFalse(premisseF.estVerifiee(baseDeFaits));

        System.out.println("Test Passed.");
    }
}

