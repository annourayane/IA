package systeme_expert.gui;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File; // Import pour la classe File
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

import systeme_expert.grammaire.*;
import systeme_expert.moteur.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    JMenuBar menuBar;
    JMenu optionsMenu;
    JMenuItem parcourirMenuItem;
    JPanel panel1;
    JPanel panel2;
    JButton avant;
    JLabel label;
    JLabel label2;
    JTextArea regles;
    JTextArea faits;
    JTextArea result;

    JButton arriere;
    JLabel messageLabel;
    JRadioButton radioButton1;
    JRadioButton radioButton2;
    JRadioButton radioButton3;
    ButtonGroup radioButtonGroup;
    JLabel symboleLabel; 
    JTextField symboleText;
    JLabel valeurLabel; 
    JTextField valeurText; 
    JLabel res; 
    private ArrayList<Regle> baseDeRegles;
    private boolean fichierImporte = false; // Attribut pour contrôler si un fichier a été importé

    public Gui(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BaseDeFaits baseDeFaits = new BaseDeFaits();

        // Menu
        setJMenuBar( createMenuBar());


        // Panel 1
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        avant = new JButton("Chainage Avant");
        label = new JLabel("Base de règles : ");
        regles = new JTextArea(18,35);
        regles.setEditable(false); // Empêche l'utilisateur de modifier le texte
        regles.setLineWrap(true); // Active le retour automatique à la ligne
        regles.setWrapStyleWord(true); // Interprète correctement les retours à la ligne
        regles.setBackground(Color.GRAY); // Couleur de fond blanche

        label2 = new JLabel("Base de faits après le chaînage avant :");
        faits = new JTextArea(18,35);
        faits.setEditable(false); // Empêche l'utilisateur de modifier le texte
        faits.setLineWrap(true); // Active le retour automatique à la ligne
        faits.setWrapStyleWord(true); // Interprète correctement les retours à la ligne
        faits.setBackground(Color.GRAY); // Couleur de fond blanche
        //faits.setPreferredSize(new Dimension(350, 80)); // Augmenter la hauteur du JTextField
        //faits.setBackground(Color.GRAY); // Couleur de fond blanche
        panel1.add(avant);
        panel1.add(label);
        panel1.add(regles);
        panel1.add(label2);
        panel1.add(faits);

        // Panel 2
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        arriere = new JButton("Chainage Arrier");
        messageLabel = new JLabel("Type de fait : ");
        radioButton1 = new JRadioButton("Fait Symbolique");
        radioButton2 = new JRadioButton("Fait Boolean");
        radioButton3 = new JRadioButton("Fait Entier");
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(radioButton1);
        radioButtonGroup.add(radioButton2);
        radioButtonGroup.add(radioButton3);
        symboleLabel = new JLabel("Symbole : ");
        symboleText = new JTextField(35);
        symboleText.setPreferredSize(new Dimension(350, 35)); // Augmenter la hauteur du JTextField
        symboleText.setBackground(Color.GRAY);
        valeurLabel = new JLabel("Valeur : ");
        valeurText = new JTextField(35);
        valeurText.setPreferredSize(new Dimension(350, 35)); // Augmenter la hauteur du JTextField
        valeurText.setBackground(Color.GRAY);
        res = new JLabel("Résultat : ");
        result = new JTextArea(18,50);
        result.setEditable(false); // Empêche l'utilisateur de modifier le texte
        result.setLineWrap(true); // Active le retour automatique à la ligne
        result.setWrapStyleWord(true); // Interprète correctement les retours à la ligne
        result.setBackground(Color.GRAY);

        



        panel2.add(arriere);
        panel2.add(messageLabel);
        panel2.add(radioButton1);
        panel2.add(radioButton2);
        panel2.add(radioButton3);
        panel2.add(symboleLabel);
        panel2.add(symboleText);
        panel2.add(valeurLabel);
        panel2.add(valeurText);
        panel2.add(res);
        panel2.add(result);
        

        // Layout
        setLayout(new FlowLayout());
        add(panel1);
        add(panel2);

        pack();
                 avant.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                if (fichierImporte){
                if (e.getSource()==avant){
                
                MoteurInference.chainageAvant(baseDeFaits, baseDeRegles);
                faits.setText(baseDeFaits.toString());
              // System.out.println(baseDeFaits);
                
                }
            }
         }

         });

         arriere.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (fichierImporte){
                            // Vérifiez quel type de fait est sélectionné
                    String typeDeFait = "";
                    if (radioButton1.isSelected()){
                        typeDeFait = "Fait Symbolique";
                    }else if (radioButton2.isSelected()){
                        typeDeFait = "Fait Boolean";
                    } else if (radioButton3.isSelected()) {
                        typeDeFait = "Fait Entier";
                    }
                    String symbole = "";
                    String valeur = "";
                    String valeurSymbole = "";
                    boolean valeurBoolean = false;
                     //boolean valeurBoolean;
                    double valeurNumerique = 0.0;
                    symbole   = symboleText.getText();
                    valeur = valeurText.getText();
                    if (!symbole.equals("")){
                        if ( !valeur.equals("")){
                        
                        

                    if (typeDeFait.equals("Fait Symbolique")){
                        
                        valeurSymbole = valeur;


                    
                        
                             
                    } else if (typeDeFait.equals("Fait Boolean")){
                        try{
                            valeurBoolean = Boolean.parseBoolean(valeur);
                        }catch (NumberFormatException ex){
                                    // Gérer le cas où aucun texte n'a été saisi
                                JOptionPane.showMessageDialog(Gui.this, "Veuillez saisir une valeur pour le type 'Fait Boolean'", "Erreur", JOptionPane.ERROR_MESSAGE);
                                 return; // Sortir de la méthode car la saisie est obligatoire
                        }
                        
                    } else if (typeDeFait.equals("Fait Entier")){
                        try{
                            valeurNumerique = Double.parseDouble(valeurText.getText());
                        } catch (NumberFormatException ex) {
                            // Gérer les cas où la valeur texte n'est pas convertible en nombre
                             JOptionPane.showMessageDialog(Gui.this, "La valeur doit être un nombre pour le type 'Fait Entier'", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return; // Sortir de la méthode si la conversion échoue
                        }
                    }

                    if (typeDeFait.equals("Fait Symbolique")){
                            FaitSymbolique fait = new FaitSymbolique(symbole,valeurSymbole);
                            MoteurInference.chainageArriere(baseDeFaits, baseDeRegles, fait);
                            boolean resultat = MoteurInference.chainageArriere(baseDeFaits, baseDeRegles, fait);
                        if (resultat){
                            result.setText("le fait"+fait+"est prouver par chainage arrier");

                        } else {
                            result.setText("le fait"+fait+"n'est pas prouver");

                        }

                        
                    } else if (typeDeFait.equals("Fait Boolean")){
                        FaitBooleen fait2 = new FaitBooleen (symbole,valeurBoolean);
                        MoteurInference.chainageArriere(baseDeFaits, baseDeRegles, fait2);
                        boolean resultat = MoteurInference.chainageArriere(baseDeFaits, baseDeRegles, fait2);
                        if (resultat){
                            result.setText("le fait"+fait2+"est prouver par chainage arrier");

                        } else {
                            result.setText("le fait"+fait2+"n'est pas prouver");

                        }

                    } else if (typeDeFait.equals("Fait Entier")){
                        ExpressionArithmetique valeurNum = new Valeur(valeurNumerique);

                        FaitEntier fait3 = new FaitEntier(symbole,valeurNum);
                         
                        boolean resultat = MoteurInference.chainageArriere(baseDeFaits, baseDeRegles, fait3);
                        if (resultat){
                            result.setText("le fait"+fait3+"est prouver par chainage arrier");

                        } else {
                            result.setText("le fait"+fait3+"n'est pas prouver");

                        }
                      






                    }

                    }
                }
                    
                }

            }





         });


    }



        private JMenuBar createMenuBar(){
        //Création de la barre de navigation
		JMenuBar menuBar = new JMenuBar();
        JMenu changerFichier = new JMenu("charger un Fichier");
        //Création de la sous-catégorie "parcourir" de "changerfichier"
		JMenuItem parcourir = new JMenuItem("parcourir");
        changerFichier.add(parcourir);
        menuBar.add(changerFichier);
        
        
        parcourir.addActionListener((ActionEvent e) ->{
            FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Fichiers txt", "txt");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false); // Désactive l'option pour sélectionner tous les fichiers
            fileChooser.setFileFilter(txtFilter); // Applique le filtre pour les fichiers texte
            int returnVal = fileChooser.showOpenDialog(Gui.this); // Affiche la boîte de dialogue pour choisir un fichier
            if (returnVal == JFileChooser.APPROVE_OPTION) { // Si l'utilisateur a sélectionné un fichier et a appuyé sur "Ouvrir"
            // On essaye de lire le fichier choisi par l'utilisateur
            try {
                File selectedFile = fileChooser.getSelectedFile(); // Récupère le fichier sélectionné
                // Ici, vous pouvez insérer votre logique pour traiter le fichier texte sélectionné
                // Par exemple, lire son contenu ou effectuer d'autres opérations selon vos besoins
                // selectedFile contient le chemin absolu vers le fichier sélectionné
                traiterFichier(selectedFile);
                fichierImporte = true; // Met à true une fois que le fichier est importé
            // Afficher la base de règles dans le JTextArea
            String baseDeReglesString = afficherBaseDeRegles(this.baseDeRegles);
            regles.setText(baseDeReglesString);
            } catch (Exception ex) { // S'il y a une exception lors de la lecture du fichier
                // Renvoie un message d'erreur si le fichier n'est pas valide
                JOptionPane.showMessageDialog(Gui.this, "Impossible d'ouvrir le fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }


        });
        return menuBar;

    }

      private void traiterFichier(File fichier)throws FileNotFoundException{
    FileReader fileReader = new FileReader(fichier);
    LineNumberReader reader = new LineNumberReader(fileReader);
     ConstructeurGrammaire builder = new ConstructeurGrammaire(reader);
     Manager director = new Manager(builder);
     director.demarerLaConstruction();
      
    this.baseDeRegles = director.getConstructeur().getBaseDesRegles();
    

        
  }

  public String afficherBaseDeRegles(ArrayList<Regle> listeDeRegles) {
    String affichage = "";
    for (Regle regle : listeDeRegles) {
        affichage += regle.toString() + "\n";
    }
    return affichage;
}























    public static void main(String[] args) {
        Gui gui = new Gui("Système Expert GUI");
        gui.setVisible(true);

    }
}
