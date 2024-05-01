import compo.Fichier;
import compo.Validation;
import compo.Affichage;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.io.IOException;
import java.io.File.*;

public class Sudoku {
    public static int[][] grille;
    public static int TAILLE = 9;
    public static JFrame fenetre;

    public static void main(String[] args) {
        fenetre = new JFrame("Jeu du Sudoku");
        fenetre.setSize(500, 500);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grilleazero();
        int bouton = 2;
        Affichage.houhou(fenetre, bouton, grille, TAILLE);
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

}
