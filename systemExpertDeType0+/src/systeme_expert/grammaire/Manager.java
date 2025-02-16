package systeme_expert.grammaire;
public class Manager{
    //Attributs
    private ConstructeurGrammaire constructeur;

    
    /** 
     * Constructeur
    @ param constructeur, une insance de la class qui s'occupe de construire une grammaire
    @ requires costructeur != null
    @ ensures this.constructeur != null
     */
    public Manager(ConstructeurGrammaire constructeur){
        this.constructeur = constructeur;
    }

    //Getteur
    public ConstructeurGrammaire getConstructeur(){ return this.constructeur;}

    //Demarer la construction de la grammaire et l'analyse de la base de connaissance
    public void demarerLaConstruction(){ this.constructeur.construire();}
}