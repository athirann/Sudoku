package compo;

import java.io.IOException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.*;
import java.awt.*;

import compo.Validation;
import compo.Fichier;

public class Affichage {

    public static void houhou(JFrame fenetre, int bouton, int grille[][], int TAILLE) {
        fenetre.setLayout(new BorderLayout());
        JTextField[][] textFields = new JTextField[TAILLE][TAILLE];
        JPanel barre = new JPanel();

        // Les bouton pour la creation du sudoku
        if (bouton == 1) {
            JButton charger = new JButton("Choisir un fichier");
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
                    Fichier.sauvegarder(fenetre, textFields);
                } catch (IOException ex) {
                    System.out.println("Impossible de sauvegarder");
                }
            });
            barre.add(sauvegarder);
            barre.add(charger);
        }

        // Les Boutons pour resoudre le sudoku

        if (bouton == 2) {

            JButton Ouvrir = new JButton("Ouvrir une grille");
            Ouvrir.addActionListener(e -> {
                try {
                    Fichier.ouvrir(fenetre, textFields);
                } catch (IOException ex) {
                    System.out.println("Erreur lors de l'ouverture de la grille");
                }
            });
            barre.add(Ouvrir);
        }

        // Pour crée la grille
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(TAILLE, TAILLE));

        for (int i = 0; i < TAILLE; i++) {

            for (int j = 0; j < TAILLE; j++) {

                JTextField on_insere = new JTextField();
                on_insere.setHorizontalAlignment(JTextField.CENTER);

                if (grille[i][j] != 0) {
                    on_insere.setText(Integer.toString(grille[i][j]));
                    on_insere.setEditable(false);
                } else {
                    on_insere.setText("");
                }

                textFields[i][j] = on_insere;

                panneau.add(on_insere);
            }
            panneau.revalidate();
            panneau.repaint();
        }

        // Les Listerner qui detecter a chaque fois que un chiffre est inserer

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

            // Permet d'ajouter le chiffre a la grille

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
        // Pour les Listerner.
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                textFields[i][j].getDocument().addDocumentListener(coco);
            }
        }

        fenetre.add(panneau, BorderLayout.CENTER);
        fenetre.add(barre, BorderLayout.NORTH);
    }

}
