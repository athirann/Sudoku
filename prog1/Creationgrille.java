import compo.Fichier;
import compo.Validation;

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
        grille = new int[TAILLE][TAILLE];
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                grille[i][j] = 0;
            }
        }
    }

    public static void affichage() {
        fenetre.setLayout(new BorderLayout());
        JPanel barre = new JPanel();
        JButton charger = new JButton("Choisir un fichier");
        JTextField[][] textFields = new JTextField[TAILLE][TAILLE];

        charger.addActionListener(e -> {
            try {
                Fichier.ouvrir(fenetre, textFields);
            } catch (IOException ex) {
                System.out.println("Erreur lors de l'ouverture du Fichier");
            }
        });

        JButton sauvegarder = new JButton("Sauvegarder cette grille");

        sauvegarder.addActionListener(e -> {
            try {
                Fichier.sauvegarder(fenetre,textFields);
            } catch (IOException ex) {
                System.out.println("Impossible de sauvegarder");
            }
        });

        barre.add(charger);
        barre.add(sauvegarder);

        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(TAILLE, TAILLE));

        // grille = new int[TAILLE][TAILLE];

        for (int i = 0; i < TAILLE; i++) {

            for (int j = 0; j < TAILLE; j++) {

                JTextField on_insere = new JTextField();
                on_insere.setHorizontalAlignment(JTextField.CENTER);

                if (grille[i][j] != 0) {
                    on_insere.setText(Integer.toString(grille[i][j]));
                } else {
                    on_insere.setText("");
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

                            if (!Validation.Validation(grille, i, j, valeur)) {
                                textFields[i][j].setBackground(Color.RED);
                                System.out.println("Ya un doublon heheha");
                            } else {

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

    // anciennement la fonction validation elle est actuellement en classe
}
