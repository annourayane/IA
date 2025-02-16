package systeme_expert.tests;
import systeme_expert.moteur.*; 
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class MoteurInferenceTest {

	 /**
	 une méthode qui teste la méthode de chinage avant de la classe Moteur Inference 
	 
	 */
    @Test
    public void testChainageAvant() {
        System.out.println("Test chinage avant launched...");

        // Création de la base de faits
        BaseDeFaits baseFaits = new BaseDeFaits();
        baseFaits.ajouterFait("A", new FaitBooleen("A", true));
        baseFaits.ajouterFait("B", new FaitBooleen("B", true));
        baseFaits.ajouterFait("C", new FaitBooleen("C", true));
        baseFaits.ajouterFait("D", new FaitBooleen("D", false));
        baseFaits.ajouterFait("E", new FaitBooleen("E", false));
        
            //les premisses 
        PremisseBooleenne premisse1 = new PremisseBooleenne("A",true); 
        PremisseBooleenne premisse2 = new PremisseBooleenne("B",true); 
        PremisseBooleenne premisse3 = new PremisseBooleenne("C",true); 
        PremisseBooleenne premisse4 = new PremisseBooleenne("Z",true); 
				
				//prémisses de la régle 1
        ArrayList<Premisse> premisses = new ArrayList<>();
        premisses.add(premisse1);
        premisses.add(premisse2);
        premisses.add(premisse3); 
    
				// prémisses de la régle 2
        ArrayList<Premisse> premisses2 = new ArrayList<>();
        premisses2.add(premisse1);
        premisses2.add(premisse4);
        
             // les conclusions 
        ConclusionSymbolique conclusion1 = new ConclusionSymbolique("Medcin","Riche");
        ConclusionSymbolique conclusion2 = new ConclusionSymbolique("Examen","Bon"); 
        ConclusionSymbolique conclusion3 = new ConclusionSymbolique("Joueur","Malade");  
             
             // Regle 
        RegleAvecPremisses regle1 = new RegleAvecPremisses(conclusion1,premisses); 
        RegleSansPremisses regle2 = new RegleSansPremisses(conclusion2); 
        RegleAvecPremisses regle3 = new RegleAvecPremisses(conclusion3,premisses2); 
				
				//base de régles 
        ArrayList<Regle> baseDeRegles = new ArrayList<>(); 
        baseDeRegles.add(regle1); 
        baseDeRegles.add(regle2); 
        baseDeRegles.add(regle3);

        // Exécution du chaînage avant
        MoteurInference.chainageAvant(baseFaits, baseDeRegles);
			
			//Vérification 
        assertTrue(baseFaits.existe("Medcin")); 
        assertTrue(baseFaits.existe("Examen"));
        assertFalse(baseFaits.existe("Joueur"));

        System.out.println("Test chinage avant Passed .");
    }


		/**
		 une méthode qui teste la méthode de chinage arriere de la classe Moteur Inference 
	 
	 	*/
 		@Test
    public void testChainageArriere() {
        System.out.println("Test chinage arrière launched...");
			//création de la base de faits 
        BaseDeFaits baseFaits = new BaseDeFaits();
        baseFaits.ajouterFait("A", new FaitBooleen("A", true));
        baseFaits.ajouterFait("B", new FaitBooleen("B", true));
        baseFaits.ajouterFait("C", new FaitBooleen("C", true));

			// création des prémisses 
        PremisseBooleenne premisse1 = new PremisseBooleenne("A",true); 
        PremisseBooleenne premisse2 = new PremisseBooleenne("B",true); 
        
        // les prémisses de la régle 1 
        ArrayList<Premisse> premisses = new ArrayList<>();
        premisses.add(premisse1);
        premisses.add(premisse2);

		  // création de la conclusion 
        ConclusionSymbolique conclusion1 = new ConclusionSymbolique("Medcin","Riche");
		  
		  // Régle 
        RegleAvecPremisses regle1 = new RegleAvecPremisses(conclusion1, premisses); 
			
		  // base de régle 
        ArrayList<Regle> baseDeRegles = new ArrayList<>(); 
        baseDeRegles.add(regle1); 

			// vérifications 
        boolean res1 = MoteurInference.chainageArriere(baseFaits, baseDeRegles, new FaitSymbolique("Medcin", "Riche"));
        boolean res2 = MoteurInference.chainageArriere(baseFaits, baseDeRegles, new FaitSymbolique("Infermier", "Riche"));
        assertFalse(res2); 
        assertTrue(res1);
      

        System.out.println("Test chinage arrière Passed.");
    }

}
