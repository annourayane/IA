package systeme_expert.tests;
import systeme_expert.moteur.*; 

import org.junit.Test;
import static org.junit.Assert.*;

public class PremisseEntiereTest {
	/**
	une méthode qui teste la méthode estVerifiee() de la classe PremisseEntiere 
	
	*/
    @Test
    public void testEstVerifiee() {
        System.out.println("Test launched...");

        // Création d'une base de faits
        BaseDeFaits baseDeFaits = new BaseDeFaits();
        baseDeFaits.ajouterFait("X", new FaitEntier("X", new Valeur(10))); // Ajout d'un fait entier

        // Création de différents cas de prémisse entière
        PremisseEntiere premisse1 = new PremisseEntiere("X", new Comparateur(">"), new Valeur(5));
        PremisseEntiere premisse2 = new PremisseEntiere("X", new Comparateur("<"), new Valeur(15));
        PremisseEntiere premisse3 = new PremisseEntiere("X", new Comparateur("="), new Valeur(10)); 
	PremisseEntiere premisse4 = new PremisseEntiere("Y", new Comparateur("="), new Valeur(10));
        // Vérification des prémissses dans la base de faits
        assertTrue(premisse1.estVerifiee(baseDeFaits)); 
        assertTrue(premisse2.estVerifiee(baseDeFaits)); 
        assertTrue(premisse3.estVerifiee(baseDeFaits)); 
	assertFalse(premisse4.estVerifiee(baseDeFaits)); 
        System.out.println("Test Passed.");
    }
}

