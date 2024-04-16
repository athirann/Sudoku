import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Creationgrille extends JFrame {

    public static int[][] grille;
    public static int TAILLE = 9;
    public static int region = 3;
    public static JFrame fenetre;

    public static void main(String[] args) {
         fenetre = new JFrame("Creation de grille");
        fenetre.setSize(500, 500);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        affichage();
        fenetre.setVisible(true);

    }

    public static void affichage() {
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(TAILLE,TAILLE));

        grille = new int[][] {
                { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
        };


        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                JTextField oninsere = new JTextField();
                oninsere.setHorizontalAlignment(JTextField.CENTER);
                if (grille[i][j] != 0) {
                    oninsere.setText(Integer.toString(grille[i][j]));
                }
                panneau.add(oninsere);
            }
        }

        JButton chargerfichier = new JButton("Charger une grille");
       chargerfichier.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
         
            JFileChooser grillechargé = new JFileChooser();
            int confirmation = grillechargé.showOpenDialog(null);
           if(confirmation == JFileChooser.APPROVE_OPTION){
            File selection = grillechargé.getSelectedFile();

try {
                Scanner scanner = new Scanner(selection);
                for (int i = 0; i < TAILLE; i++) {
                    for (int j = 0; j < TAILLE; j++) {
                        if (scanner.hasNextInt()) {
                            grille[i][j] = scanner.nextInt();
                        } 
                    }
                }
                // Rafraîchir l'affichage de la grille
                refreshGrid();
                scanner.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Aucun fichier sélectionné");
        }
    }

       });
    


       fenetre.add(chargerfichier, BorderLayout.NORTH);
        fenetre.add(panneau, BorderLayout.CENTER);
    }


    public static void refreshGrid() {
        fenetre.getContentPane().removeAll(); // Supprimer tous les composants de la fenêtre
        affichage(); // Réafficher la grille mise à jour
        fenetre.revalidate(); // Valider les modifications apportées à la fenêtre
        fenetre.repaint(); // Rafraîchir l'affichage de la fenêtre
    }

}