import compo.Fichier;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.io.IOException;
import java.io.File.*;

public class Creationgrille extends JFrame {

    public static int[][] grille;
    public static int TAILLE = 9;
    public static JFrame fenetre;

    public static void main(String[] args) {
        fenetre = new JFrame("Creation de grille");
        fenetre.setSize(500, 500);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grilleazero();
        affichage();
        fenetre.setVisible(true);

    }

    public static void grilleazero() {
        grille = new int[TAILLE][TAILLE]; // Create a new grid
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                grille[i][j] = 0; // Initialize all cells to 0
            }
        }
    }

    public static void affichage() {
        fenetre.setLayout(new BorderLayout());
        JPanel barre = new JPanel();
        JButton charger = new JButton("Choisir un fichier");

        charger.addActionListener(e -> {
            try {
                Fichier.ouvrir(fenetre, grille);
            } catch (IOException ex) {
                System.out.println("Erreur lors de l'ouverture du Fichier");
            }
        });

        JButton sauvegarder = new JButton("Sauvegarder cette grille");
        barre.add(charger);
        barre.add(sauvegarder);

        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(TAILLE, TAILLE));

        JTextField[][] textFields = new JTextField[TAILLE][TAILLE];

        grille = new int[TAILLE][TAILLE];

        for (int i = 0; i < TAILLE; i++) {

            for (int j = 0; j < TAILLE; j++) {

                JTextField on_insere = new JTextField();
                on_insere.setHorizontalAlignment(JTextField.CENTER);

                if (grille[i][j] != 0) {
                    on_insere.setText(Integer.toString(grille[i][j]));
                }

                textFields[i][j] = on_insere;

                panneau.add(on_insere);
            }
        }

        DocumentListener coco = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                lecture(textFields);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lecture(textFields);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lecture(textFields);
            }

            public void lecture(JTextField[][] textFields) {
                for (int i = 0; i < TAILLE; i++) {
                    for (int j = 0; j < TAILLE; j++) {
                        String hehe = textFields[i][j].getText();

                        try {

                            int valeur = Integer.parseInt(hehe);
                            grille[i][j] = valeur;

                            if (!validation(grille, i, j, valeur)) {
                                textFields[i][j].setBackground(Color.RED);
                                System.out.println("Ya un doublon heheha");
                            } else {
                                // Sinon, remettre la couleur de fond par défaut (blanc)
                                textFields[i][j].setBackground(Color.WHITE);
                            }

                        } catch (NumberFormatException ex) {
                            // System.out.println("coco");
                            textFields[i][j].setBackground(Color.WHITE);
                        }

                    }
                }

            }

        };

        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                textFields[i][j].getDocument().addDocumentListener(coco);
            }
        }
        fenetre.add(panneau, BorderLayout.CENTER);
        fenetre.add(barre, BorderLayout.NORTH);
    }

    // Faire de cette fonction en classe plus tard ..

    public static boolean validation(int[][] grille, int ligne, int colonne, int valeur) {
        for (int i = 0; i < grille[ligne].length; i++) {
            if (i != colonne && grille[ligne][i] == valeur) {
                return false;
            }
        }

        for (int i = 0; i < grille.length; i++) {
            if (i != ligne && grille[i][colonne] == valeur) {
                return false;
            }

        }

        int hehehaligne = ligne - ligne % 3;
        int hehehacolonne = colonne - colonne % 3;

        for (int i = hehehaligne; i < hehehaligne + 3; i++) {
            for (int j = hehehacolonne; j < hehehacolonne + 3; j++) {

                if (i == ligne && j == colonne) {
                    continue;
                }

                if (grille[i][j] == valeur) {
                    return false;
                }
            }
        }

        return true;
    }

}
