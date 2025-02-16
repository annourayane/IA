package systeme_expert.grammaire;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import systeme_expert.moteur.*;

public class Regles implements EstFait{
    private Variable varAct;
    private Analyseur contenu;
    private HashMap<String,Fait> faitsDeclares;

    private ArrayList<Regle> regles;
    private Conclusion conclusion;
    private ExpressionArithmetique expArithmetique;
    private ExpressionArithmetique terme;
    private ExpressionArithmetique facteur;
    private ExpressionArithmetique nombre;
    private String valeurFaitSymbolique;
    private ArrayList<Premisse> premisses;
    private String fait;
    private Comparateur comparateur;

    /** Constructeur
    @param contenu, c'est le contenu du fichier
    @param faitsDeclares, ce sont l'ensemble des faits declares
    @requires contenu != null
    @requires faitsDeclares != null
    @ensures this.contenu = contenu
    @ensures this.varAct = contenu.identifiantSuivant()
    @ensures this.faitsDeclares = faitsDeclares
    @ensures this.premisses = new HashSetPremisse()
     */ 
    public Regles(Analyseur contenu, HashMap<String,Fait> faitsDeclares) throws IOException{
        this.contenu = contenu;
        this.varAct = contenu.identifiantSuivant();
        this.faitsDeclares = faitsDeclares;
        this.premisses = new ArrayList<Premisse>();
        this.fait = "";
        this.regles = new ArrayList<Regle>();
    }

    /** Verifie si les bloc de text lu represente des regles
    @ensures varAct =  contenu.identifiantSuivant()
    @ensures result = varAct.estFDF();
    @return true si le contenu lu contient que des regles et false sinon
     */ 
    public boolean estRegles() throws IOException{
        while(estRegle()){
            this.varAct = contenu.identifiantSuivant();
            if(varAct.estFDF()){
                return true;
            }
        }

        return this.varAct.estFDF();
    }

    /** 
     * Verifie si une ligne est une regle
     
      @return true si c'est une regle et false sinon
     */ 
    public boolean estRegle() throws IOException{
        this.conclusion = null;
        this.premisses.clear();
        return (estRegleSansPremisse() || estRegleAvecPremisse());
    }

    /** 
     * Verifie si une ligne de texte est une regle sans premisse
     
      @return true si c'est une regle de forme "~<conclusion>." et false sinon
     */
    public boolean estRegleSansPremisse() throws IOException{
        if(!this.varAct.estSoit()){
            return false;
        }

        this.varAct = contenu.identifiantSuivant();

        if(!estConclusion()){
            return false;
        }

        if(!varAct.estPoint()){
            return false;
        }

        this.regles.add(new RegleSansPremisses(this.conclusion));
        return true;
    }

    /** 
     * Verifie si une ligne de texte est une regle avec premisse
     
      @return true si c'est une regle de forme ">> <condition> ==> <conclusion>." et false sinon
     */
    public boolean estRegleAvecPremisse() throws IOException{
        if(!varAct.estSi()){
            return false;
        }

        this.varAct = contenu.identifiantSuivant();

        if(!estCondition()){
            return false;
        }

        if(!varAct.estAlors()){
            return false;
        }

        this.varAct = contenu.identifiantSuivant();

        if(!estConclusion()){
            return false;
        }

        if(!varAct.estPoint()){
            return false;
        } 

        this.regles.add(new RegleAvecPremisses(this.conclusion,(ArrayList<Premisse>) this.premisses.clone()));

        return true;
    }

    /** 
     * Verifie si une phrase represente une condition
     
      @return true si "<premisse>{(& ou |) <premisse>}." et false sinon
     */
    public boolean estCondition() throws IOException{
        if(!estPremisse()){
            return false;
        }
    
        while(varAct.estEt() || varAct.estOu()){
            this.varAct = contenu.identifiantSuivant();
            if(!estPremisse()){
                return false;
            }
        }

        return true;
    }

    /** 
     * Verifie si une expression represente une premisse
     
      @return true si l'expression est une premisse booleenne, entiere ou symbolique et false sinon
     */
    public boolean estPremisse() throws IOException{
        return (estPremisseBooleenne() || estPremisseEntiere() || estPremisseSymbolique());
    }

    /** 
     * Verifie si une expression represente une premisse booleenne
     
      @return true si l'expression est de la forme "(!)<fait booleen>" et false sinon
     */
    public boolean estPremisseBooleenne() throws IOException{
        boolean negation = false;
        if(varAct.estNon()){
            this.varAct = contenu.identifiantSuivant();
            negation = true;
        }

        if(!estFaitBooleen()){
            return false;
        }

        this.premisses.add(new PremisseBooleenne(this.fait,!negation));

        return true;        
    }

    /** 
     * Verifie si une expression represente une premisse entiere
     
      @return true si l'expression est de la forme "<fait entier> <comparateur> <expression arithmetique>"
      et false sinon
     */
    public boolean estPremisseEntiere() throws IOException{
        if(!estFaitEntier()){
            return false;
        }

        if(!varAct.estSigneDeComparaison()){
            return false;
        }
        this.comparateur = new Comparateur(varAct.getRepresentaion());
        this.varAct = contenu.identifiantSuivant();

        if(!estExpressionArithmetique()){
            return false;
        }

        this.premisses.add(new PremisseEntiere(this.fait, this.comparateur , this.expArithmetique));

        return true;
    }
    
    /** 
     * Verifie si une expression represente une premisse symbolique
     
      @return true si l'expression est de la forme "<fait symbolique> (= ou !=) <chaine de caractere>"
      et false sinon
     */
    public boolean estPremisseSymbolique() throws IOException{
        if(!estFaitSymbolique()){
            return false;
        }

        if(!varAct.estEgale() && !varAct.estDifferent()){
            return false;
        }
        this.varAct = contenu.identifiantSuivant();

        if(!estChaineDeCaracteres()){
            return false;
        }

        this.premisses.add(new PremisseSymbolique(this.fait, this.valeurFaitSymbolique));

        return true;
    }

    /** 
     * Verifie si une expression est arithmetique
     
      @return true si l'expression est de la forme "(-) <terme> {(+ ou -) <terme>}"
      et false sinon
     */
    public boolean estExpressionArithmetique() throws IOException{
        if(varAct.estOpAddition() || varAct.estOpSoustraction()){
            if(varAct.estOpSoustraction()){
                int signe = -1;
            }
            this.varAct = contenu.identifiantSuivant();
        }
        if(!estTerme()){
            return false;
        }

        Variable op = null;
        while(varAct.estOperateurAdditif()){
            ExpressionArithmetique gauche = expArithmetique;
            op = varAct;
            this.varAct = contenu.identifiantSuivant();

            if(!estTerme()){
                return false;
            }

            ExpressionArithmetique droit = expArithmetique;
            if(op.estOpAddition()){
                expArithmetique = new Addition(gauche,droit);
            }
            else{
                expArithmetique = new Soustraction(gauche,droit);
            }
            
        }

        return true;
    }

    /** 
     * Methode qui detecte les termes
     
      @return true si l'expression est de la forme "<facteur> {(* ou /) <facteur>}"
      et false sinon
     */
    public boolean estTerme() throws IOException{
        if(!estFacteur()){
            return false;
        }

        Variable op = null;
        while(varAct.estOperateurMultiplicatif()){
            ExpressionArithmetique gauche = expArithmetique;
            op = varAct;
            this.varAct = contenu.identifiantSuivant();
            if(!estFacteur()){
                return false;
            }

            ExpressionArithmetique droit = expArithmetique;
            if(op.estOpMultiplication()){
                expArithmetique = new Multiplication(gauche,droit);
            }
            else if(op.estOpDivision()){
                expArithmetique = new Division(gauche,droit);
            }
            
        }
        return true;
    }

    /** 
     * Methode qui detecte les facteur
     
      @return true si l'expression est de la forme "(<expression arithmetique)"
      et false sinon
     */
    public boolean estFacteur() throws IOException{
        boolean estExpArt = false;

        if(varAct.estParentheseOuvrante()){
            this.varAct = contenu.identifiantSuivant();
            if(estExpressionArithmetique()){
                if(varAct.estParentheseFermante()){
                    estExpArt = true;
                    ExpressionArithmetique exp = expArithmetique;
                    expArithmetique = new Parenthese(exp);
                    this.varAct = contenu.identifiantSuivant();
                }
            }
        }


        boolean estChiffre = estNombre();
        if(estChiffre){
            facteur = nombre;
        }
        return (estExpArt || estFaitEntier() || estChiffre); 
    }

    /** 
     * Methode qui detecte si un facteur est un nombre
     
      @return true si facteur = nombre reel et false sinon
     */
    public boolean estNombre() throws IOException{
        String strValeur = "";
        double valeur = 0;
        if(!varAct.estChiffrePositif()){
            return false;
        }
        strValeur = varAct.getRepresentaion();
        valeur = Double.parseDouble(strValeur);

        this.varAct = contenu.identifiantSuivant();

        while(varAct.estChiffrePositif()){
            strValeur += varAct.getRepresentaion();
            valeur = Double.parseDouble(strValeur);
            this.varAct = contenu.identifiantSuivant();
        }
        nombre = new Valeur(valeur);
        expArithmetique = new Valeur(valeur);

        return true;
    }


    /** 
     * Methode qui detecte les chaines de caracteres
     
      @return true si estFaitSymbolique() ou varAct.estFait() et false sinon
     */
    public boolean estChaineDeCaracteres() throws IOException{
        if(!estFaitSymbolique() && !varAct.estFait()){
            return false;
        }

        if(estFaitSymbolique()){
            String valeurStr = (String) this.faitsDeclares.get(varAct.getRepresentaion()).getValeur();
            this.valeurFaitSymbolique = valeurStr; 
        }
        else{
            this.valeurFaitSymbolique = varAct.getRepresentaion();
        }

        this.varAct = contenu.identifiantSuivant();

        return true;
    }

    /** 
     * Methode qui verifie si une expression est un fait booleen
     
      @return true si varAct appartient a l'ensemble des fait booleens declares
      et false sinon
     */
    public boolean estFaitBooleen() throws IOException{
        if(!this.faitsDeclares.containsKey(varAct.getRepresentaion())){
            return false;
        }

        if(this.faitsDeclares.get(varAct.getRepresentaion()).getFaitType() != "Fait Booleen"){
            return false;
        }
        
        this.fait = varAct.getRepresentaion();
        this.varAct = contenu.identifiantSuivant();

        return true;
    }

     /** 
     * Methode qui verifie si une expression est un fait entier
     
      @return true si varAct appartient a l'ensemble des fait entiers declares
      et false sinon
     */
    public boolean estFaitEntier() throws IOException{
        if(!this.faitsDeclares.containsKey(varAct.getRepresentaion())){
            return false;
        }

        if(this.faitsDeclares.get(varAct.getRepresentaion()).getFaitType() != "Fait Entier"){
            return false;
        }

        this.fait = varAct.getRepresentaion();
        for(Regle r: regles){
            if(r.getConclusion().getSymbole().equals(fait)){
                double expDouble = (double) r.getConclusion().getValeur();;
                expArithmetique = new Valeur(expDouble);
                break;
            }
        }
        this.varAct = contenu.identifiantSuivant();

        return true;
    }

     /** 
     * Methode qui verifie si une expression est un fait symbolique
     
      @return true si varAct appartient a l'ensemble des fait symboliques declares
      et false sinon
     */
    public boolean estFaitSymbolique() throws IOException{
        if(!this.faitsDeclares.containsKey(varAct.getRepresentaion())){
            return false;
        }

        if(this.faitsDeclares.get(varAct.getRepresentaion()).getFaitType() != "Fait Symbolique"){
            return false;
        }

        this.fait = varAct.getRepresentaion();
        this.varAct = contenu.identifiantSuivant();
        
        return true;
    }

     /** 
     * Methode qui verifie si une expression est une conclusion
     
      @return true si l'expression en question est une conclusion booleenne, entiere ou symboliques
      et false sinon
     */
    public boolean estConclusion() throws IOException{
        return (estConclusionBooleenne() || estConclusionEntiere() || estConclusionSymbolique());
    }

     /** 
     * Methode qui verifie si une expression est une conclusion booleenne
     
      @return true si l'expression est de forme "(!) <fait booleen>" et false sinon
     */
    public boolean estConclusionBooleenne() throws IOException{
        boolean negation = false;
        if(varAct.estNon()){
            this.varAct = contenu.identifiantSuivant();
            negation = true;
        }

        if(!estFaitBooleen()){
            return false;
        }

        this.conclusion = new ConclusionBooleenne(this.fait,!negation);

        return true;
    }

     /** 
     * Methode qui verifie si une expression est une conclusion entiere
     
      @return true si l'expression est de la forme "<fait entier> = <expression arithmetique>"
      et false sinon
     */
    public boolean estConclusionEntiere() throws IOException{
        if(!estFaitEntier()){
            return false;
        }

        if(!varAct.estEgale()){
            return false;
        }

        this.varAct = contenu.identifiantSuivant();

        if(!estExpressionArithmetique()){
            return false;
        }

        this.conclusion = new ConclusionEntiere(this.fait, this.expArithmetique);

        return true;
    }

    /** 
     * Methode qui verifie si une expression est une conclusion symbolique
     
      @return true si l'expression est de la forme "<fait symbolique> = <chaine de caracteres>"
      et false sinon
     */
    public boolean estConclusionSymbolique() throws IOException{
        if(!estFaitSymbolique()){
            return false;
        }

        if(!varAct.estEgale()){
            return false;
        }

        this.varAct = contenu.identifiantSuivant();

        if(!estChaineDeCaracteres()){
            return false;
        }

        this.conclusion = new ConclusionSymbolique(this.fait, this.valeurFaitSymbolique);

        return true;
    }

    /** 
     * Methode qui renvoie l'ensemble de regles extraites depuis la base de connaissance donnee 
     
      @return this.regles qui est une ArrayList contenant toute les regles de la base de connaissance
     */
    public ArrayList<Regle> getEnsembleDesRegles(){
        return this.regles;
    }
}
