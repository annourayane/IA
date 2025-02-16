package systeme_expert.tests;

import systeme_expert.moteur.*; 
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TestGlobal {
	/**
	Ce Test est Consacré a la classe Addition
	
	*/
    @Test   
    public void testEvaluerAddition() {
        System.out.println("Test Class Addition launched...");

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

        System.out.println("Test Class Addition passed.");
    }
    	/**
    	Ce Test est consacré a la classe Compaarateur 
    	
    	*/
    @Test 
    public void testComparer() {
        System.out.println("Test Class Comparateur launched...");

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

        System.out.println("Test Class Comparateur passed.");
    }
    
    /** 
    Ce Test est consacré a la classe Division ( cas de : dévision normal) 
    
    */

    @Test   
    public void testDivisionNormal() {
        System.out.println("Test Division Normal launched ...");
        ExpressionArithmetique gauche = new Valeur(10);
        ExpressionArithmetique droite = new Valeur(2);
        Division division = new Division(gauche, droite);
        assertEquals(5.0, division.evaluer(), 0.001);
        System.out.println("Test Division Normal passed.");
    }
    /** 
    Ce Test est consacré a la classe Division ( cas de : dévision Dévision par zéro) 
    
    */
    @Test  
    public void testDivisionParZero() {  // class division 
        System.out.println("Test Division by Zero launched...");
        ExpressionArithmetique gauche = new Valeur(10);
        ExpressionArithmetique droite = new Valeur(0);
        Division division = new Division(gauche, droite);
        assertEquals(Double.POSITIVE_INFINITY, division.evaluer(), 0.001);
        System.out.println("Test Division by Zero passed.");
    }
    /** 
    Ce Test est consacré a la classe Multiplication
    
    */
    @Test
    public void testEvaluerMultiplication() {  
        System.out.println("Test Class Multiplication launched...");

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

        System.out.println("Test Class Multiplication passed.");
    }
    /** 
    Ce Test est consacré a la classe Soustraction 
    
    */
    @Test   
    public void testEvaluerSoustraction() {     
        System.out.println("Test Class Soustraction launched...");

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

        System.out.println("Test Class Soustraction passed.");
    } 
    /** 
    Ce Test est consacré a la classe Valeur 
    
    */
    @Test   // class valeur 
    public void testEvaluerValeur() {
        System.out.println("Test Class Valeur launched...");

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

        System.out.println("Test Class Valeur passed.");
    }
    /** 
    Ce Test est consacré a la classe PremisseBooleenne 
    
    */
    @Test   
    public void testEstVerifieePremisseBoolean() {   
        System.out.println("Test Class Premisse Boolean launched...");
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
        System.out.println("Test Class Premisse Boolean  Passed.");
    }
    /** 
    Ce Test est consacré a la classe PremisseEntiere 
    
    */
    @Test   
    public void testEstVerifieePremisseEntiere() {
        System.out.println("Test Class Premisse Entiere launched...");

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
        System.out.println("Test Class Premisse Entiere Passed.");
    }
	/**
	Ce Test est consacré a la classe PremisseSymbolique 
	
	*/
    @Test   
    public void testEstVerifieePremisseSymbolique() {
        System.out.println("Test Class Premisse Symbolique launched...");
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

        System.out.println("Test Class Premisse Symbolique Passed.");
    }
	/**
	Ce test est consacré pour la méthode chainageAvant() de la classe MoteurInference 
	
	*/
    @Test
    public void testChainageAvant() {
        System.out.println("Test Chainage Avant launched...");

        // Création de la base de faits
        BaseDeFaits baseFaits = new BaseDeFaits();
        baseFaits.ajouterFait("A", new FaitBooleen("A", true));
        baseFaits.ajouterFait("B", new FaitBooleen("B", true));
        baseFaits.ajouterFait("C", new FaitBooleen("C", true));
        baseFaits.ajouterFait("D", new FaitBooleen("D", false));
        baseFaits.ajouterFait("E", new FaitBooleen("E", false));
        
        // Les prémisse
        PremisseBooleenne premisse1 = new PremisseBooleenne("A",true); 
        PremisseBooleenne premisse2 = new PremisseBooleenne("B",true); 
        PremisseBooleenne premisse3 = new PremisseBooleenne("C",true); 
        PremisseBooleenne premisse4 = new PremisseBooleenne("Z",true); 

        ArrayList<Premisse> premisses = new ArrayList<>();
        premisses.add(premisse1);
        premisses.add(premisse2);
        premisses.add(premisse3); 
    
        ArrayList<Premisse> premisses2 = new ArrayList<>();
        premisses2.add(premisse1);
        premisses2.add(premisse4);

        // Les conclusions
        ConclusionSymbolique conclusion1 = new ConclusionSymbolique("Medcin","Riche");
        ConclusionSymbolique conclusion2 = new ConclusionSymbolique("Examen","Bon"); 
        ConclusionSymbolique conclusion3 = new ConclusionSymbolique("Joueur","Malade");  

        // Regles
        RegleAvecPremisses regle1 = new RegleAvecPremisses(conclusion1,premisses); 
        RegleSansPremisses regle2 = new RegleSansPremisses(conclusion2); 
        RegleAvecPremisses regle3 = new RegleAvecPremisses(conclusion3,premisses2); 

        ArrayList<Regle> baseDeRegles = new ArrayList<>(); 
        baseDeRegles.add(regle1); 
        baseDeRegles.add(regle2); 
        baseDeRegles.add(regle3);

        // Exécution du chaînage avant
        MoteurInference.chainageAvant(baseFaits, baseDeRegles);

        assertTrue(baseFaits.existe("Medcin")); 
        assertTrue(baseFaits.existe("Examen"));
        assertFalse(baseFaits.existe("Joueur"));

        System.out.println("Test Chainage Avant Passed.");
    }
    /**
    Ce Test est consacré a la méthode chainage arriére de la classe MoteurInference 
    
    */

    @Test
    public void testChainageArriere() {
        System.out.println("Test Chainage Arrière launched...");

        BaseDeFaits baseFaits = new BaseDeFaits();
        baseFaits.ajouterFait("A", new FaitBooleen("A", true));
        baseFaits.ajouterFait("B", new FaitBooleen("B", true));
        baseFaits.ajouterFait("C", new FaitBooleen("C", true));

        PremisseBooleenne premisse1 = new PremisseBooleenne("A",true); 
        PremisseBooleenne premisse2 = new PremisseBooleenne("B",true); 
        ArrayList<Premisse> premisses = new ArrayList<>();
        premisses.add(premisse1);
        premisses.add(premisse2);

        ConclusionSymbolique conclusion1 = new ConclusionSymbolique("Medcin","Riche");

        RegleAvecPremisses regle1 = new RegleAvecPremisses(conclusion1, premisses); 

        ArrayList<Regle> baseDeRegles = new ArrayList<>(); 
        baseDeRegles.add(regle1); 

        boolean res1 = MoteurInference.chainageArriere(baseFaits, baseDeRegles, new FaitSymbolique("Medcin", "Riche"));
        boolean res2 = MoteurInference.chainageArriere(baseFaits, baseDeRegles, new FaitSymbolique("Infermier", "Riche"));
        assertFalse(res2); 
        assertTrue(res1);

        System.out.println("Test Chainage Arrière Passed.");
    }
}

