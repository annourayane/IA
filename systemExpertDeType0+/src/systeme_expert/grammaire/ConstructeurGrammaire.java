package systeme_expert.grammaire;
import java.io.LineNumberReader;
import java.io.IOException;
import java.util.ArrayList;
import systeme_expert.moteur.Regle;

public class ConstructeurGrammaire{
    //Attributs
    private Analyseur contenu;
    private LineNumberReader fichier;
    private Declaration declarations;
    private Regles regles;
    private ArrayList<Regle> baseDesRegles;

    //Constructeur
    public ConstructeurGrammaire(LineNumberReader fichier){
        this.fichier = fichier;
        this.contenu = new Analyseur(fichier);
        this.declarations = null;
        this.baseDesRegles = null;
    }

    /** Construire une grammaire et extraire l'ensemble des règles depuis la base de connaissance
    @ensures result = null si Erreur de syntaxe ou de lecture
    @ensures result = regles.getEnsembleDesRegles() qui represenete la base des regles si this.estBaseDeConnaissance() renvoie true
    */
    public void construire(){
        try{
            if(!this.estBaseDeConnaissance()){
                System.err.println("Erreur: ce fichier n'est pas une base de connaissance ou il contient une erreur syntaxique.");
            this.baseDesRegles = null;
            return;
            }
        }
        catch(IOException e){
            System.err.println("Erreur lors de la lecture du fichier.");
            this.baseDesRegles = null;
            return;
        }


        this.baseDesRegles = regles.getEnsembleDesRegles(); 
    }

    /** Méthode qui vérifie si le fichier est une base de connaissance
    @ensures result = false si this.delarations n'est pas une declaration
    @ensures result = false si this.regles n'est pas une regle 
    @return true si l'entrée est une base de Connaissance et false sinon
    */    
    public boolean estBaseDeConnaissance()throws IOException{
        declarations = new Declaration(contenu);
        if(!this.declarations.estDeclaration()){
            return false;
        }
        
        this.regles = new Regles(this.contenu, this.declarations.getFaitDeclaree());
            
        if(!this.regles.estRegles()){
            return false;
        }

        return this.contenu.getVarAct().estFDF();
    }

    /** Méthode qui retourne l'ensemble des règles
    @return regles, ensemble des regles
    */    
    public ArrayList<Regle> getBaseDesRegles(){ return this.baseDesRegles;}

}
