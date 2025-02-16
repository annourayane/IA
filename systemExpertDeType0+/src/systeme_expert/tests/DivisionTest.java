package systeme_expert.tests;
import systeme_expert.moteur.*; 

import org.junit.Test;
import static org.junit.Assert.*;
public class DivisionTest {
		/** 
		une méthode qui teste la devision normale 
		
		*/ 
    @Test
    public void testDivisionNormal() {
        System.out.println("Lancer le test pour la dévision normal...");
        ExpressionArithmetique gauche = new Valeur(10);
        ExpressionArithmetique droite = new Valeur(2);
        Division division = new Division(gauche, droite);
        assertEquals(5.0, division.evaluer(), 0.001);
        System.out.println("Test passé.");
    }
    /** 
    une méthode qui teste la dévision par zéro 
    
    */ 

    @Test
    public void testDivisionParZero() {
        System.out.println("Lancer test pour la dévision par zero...");
        ExpressionArithmetique gauche = new Valeur(10);
        ExpressionArithmetique droite = new Valeur(0);
        Division division = new Division(gauche, droite);
        assertEquals(Double.POSITIVE_INFINITY, division.evaluer(), 0.001);
        System.out.println("Test passé.");
    }
	 /** 
	 une méthode qui teste la dévision négative 
	 
	 */ 
    @Test
    public void testDivisionNegatif() {
        System.out.println("Lancer test pour la dévision négative ...");
        ExpressionArithmetique gauche = new Valeur(-10);
        ExpressionArithmetique droite = new Valeur(2);
        Division division = new Division(gauche, droite);
        assertEquals(-5.0, division.evaluer(), 0.001);
        System.out.println("Test passé.");
    }


}

