import sudokuBesoin.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


/**
 * Cette classe configure l'environnement pour la résolution libre ou automatique d'un puzzle Sudoku.
 * Elle initialise le jeu avec une grille prédéfinie ou vide et prépare l'interface
 * pour la résolution automatique du puzzle.
 * @author Amir KABBOURI  &amp;  Athiran NAGATHURAI
 * @package Fichier Principale Main
 */
public class SudokuResolution {

    /**
     * Grille utilisée pour le puzzle Sudoku, initialement remplie de zéros indiquant un état vide.
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
     * Panneau de l'interface utilisateur pour le jeu Sudoku, gérant l'affichage
     * et les interactions avec la grille du jeu.
     */
    private SudokuGridPanel sudokuGridPanel;


    /**
     * Constructeur qui initialise l'environnement de résolution avec une grille.
     * La grille peut être soit prédéfinie, soit laissée vide selon les paramètres de lancement.
     */
    public SudokuResolution() {
        


        sudokuGridPanel = new SudokuGridPanel(grid, true, true);


    }

    /**
     * Point d'entrée principal du programme. Initialise une nouvelle instance de résolution Sudoku.
     *
     * @param args Arguments de ligne de commande (non utilisés dans cette implémentation).
     */
    public static void main(String[] args) {
        
        
        
        new SudokuResolution();
    
    }
}

