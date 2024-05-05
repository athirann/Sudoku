import sudokuBesoin.*;

import javax.swing.*;

/**
 * Classe principale pour lancer l'application Sudoku. Elle initialise le jeu avec une grille vide
 * ou une grille chargée depuis un fichier, en fonction des choix de l'utilisateur.
 * @author Amir KABBOURI  &amp;  Athiran NAGATHURAI
 * @package Fichier Principale Main
 */
public class Sudoku {
    /**
     * Grille initiale pour le jeu Sudoku, initialisée avec tous les éléments à zéro,
     * ce qui représente une grille vide.
     */
    private int[][] grid = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    
    /**
     * Panneau de l'interface utilisateur pour la grille de Sudoku, qui gère l'affichage
     * et l'interaction avec la grille de jeu.
     */
    private SudokuGridPanel sudokuGridPanel;


    /**
     * Constructeur qui initialise le jeu avec une grille vide ou une grille pré-remplie.
     *
     * @param startWithEmptyGrid Booléen qui détermine si le jeu commence avec une grille vide
     *        (true pour une grille vide, false pour charger une grille pré-existante).
     */
    public Sudoku(boolean startWithEmptyGrid) {

        

        if (!startWithEmptyGrid) {
            sudokuGridPanel = new SudokuGridPanel(grid, true, false);
        }else{  
            sudokuGridPanel = new SudokuGridPanel(grid, false, false);
        }
        
    }


    /**
     * Point d'entrée du programme. Demande à l'utilisateur de choisir entre démarrer
     * avec une grille vide ou charger une grille depuis un fichier.
     *
     * @param args Arguments de ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        Object[] options = {"Grille vide", "Ouvrir depuis un fichier"};
        int choice = JOptionPane.showOptionDialog(null, "Choisissez une option :",
                "Options de départ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);




        boolean startWithEmptyGrid = (choice == JOptionPane.YES_OPTION);
        new Sudoku(startWithEmptyGrid);
    }
}

