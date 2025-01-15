package blocksworld;

import java.util.*;
import modelling.*; 

public class MondeDesBlocs {
    protected int nombreDeBlocs; 
    protected int nombreDePiles; 
    // Liste des variables onb 
    protected List<Variable> variablesOnb; 
    // Liste des variables fixedb 
    protected List<BooleanVariable> variablesFixedb;
    // Liste des variables freep 
    protected List<BooleanVariable> variablesFreep; 

    public MondeDesBlocs(int nombreDeBlocs, int nombreDePiles) {
        this.nombreDeBlocs = nombreDeBlocs;
        this.nombreDePiles = nombreDePiles; 

        // Initialiser les listes de variables
        this.variablesOnb = new ArrayList<>();
        this.variablesFixedb = new ArrayList<>();
        this.variablesFreep = new ArrayList<>();

        // Creation des variables onb pour chaque bloc 
        for (int i = 0; i < nombreDeBlocs; i++) {
            // Nom de la variable onb pour le bloc i
            String nomOnb = "On" + i; 
            // Domaine de la variable 
            Set<Object> domaineOnb = new HashSet<>(); // Correction ici : HashSet<>()

            // Ajouter les blocs au domaine 
            for (int j = 0; j < nombreDeBlocs; j++) {
                if (j != i) { // Un bloc ne peut pas etre sur lui-mÃªme 
                    domaineOnb.add(j); // Correction ici : j au lieu de i
                }
            }

            // Ajouter les piles au domaine avec le signe -
            for (int p = 0; p < nombreDePiles; p++) {
                domaineOnb.add(-(p + 1)); 
            }

            // Ajouter la variable onb avec le nom et domaine a  la liste
            variablesOnb.add(new Variable(nomOnb, domaineOnb));
        }

        // Creattion des variables fixedb pour chaque bloc 
        for (int i = 0; i < nombreDeBlocs; i++) {
            variablesFixedb.add(new BooleanVariable("fixed" + i)); 
        }

        // Creaation des variables freep pour chaque pile 
        for (int i = 1; i <= nombreDePiles; i++) {
            variablesFreep.add(new BooleanVariable("free" + i)); 
        }
    }

    // Methodes pour obtenir les variables
    public List<Variable> getVariablesOnb() {
        return variablesOnb;
    }

    public List<BooleanVariable> getVariablesFixes() {
        return variablesFixedb;
    }

    public List<BooleanVariable> getPilesLibres() {
        return variablesFreep;
    }

    public int getNombreDePiles(){
        return nombreDePiles; 
    }

    public int getNombreDeBlocs(){
        return nombreDeBlocs; 
    }
}