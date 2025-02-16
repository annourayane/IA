package systeme_expert.tests;
import systeme_expert.moteur.*; 
import org.junit.Test;
import static org.junit.Assert.*;

public class PremisseBooleanTest {
	/** 
	une méthode qui teste la méthode estVerifiee() de la classe PremisseBooleenne 
	
	*/

    @Test
    public void testEstVerifiee() {
    	System.out.println("Test launched...");
        // Création d'une base de faits
        BaseDeFaits baseDeFaits = new BaseDeFaits();
        // Ajout de plusieurs faits à la base de faits
        baseDeFaits.ajouterFait("A", new FaitBooleen("A", true));
        baseDeFaits.ajouterFait("B", new FaitBooleen("B", false));
        baseDeFaits.ajouterFait("C", new FaitBooleen("C", true));
        baseDeFaits.ajouterFait("D", new FaitBooleen("D", false));

        // Création de différentes prémisse booléennes avec des valeurs attendues
        PremisseBooleenne premisseA = new PremisseBooleenne("A", true);
        PremisseBooleenne premisseB = new PremisseBooleenne("B", false);
        PremisseBooleenne premisseR = new PremisseBooleenne("R", true);
        PremisseBooleenne premisseF = new PremisseBooleenne("F", false);

        // Vérification si chaque prémisse est vérifiée dans la base de faits
        assertTrue(premisseA.estVerifiee(baseDeFaits));
        assertTrue(premisseB.estVerifiee(baseDeFaits));
        assertFalse(premisseR.estVerifiee(baseDeFaits));
        assertFalse(premisseF.estVerifiee(baseDeFaits));
        System.out.println("Test Passed.");
    }
}

