import sudokuBesoin.*;
import javax.swing.*;


public class Sudoku {
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
    private SudokuGridPanel sudokuGridPanel;

    public Sudoku(boolean startWithEmptyGrid) {

        if (!startWithEmptyGrid) {
            sudokuGridPanel = new SudokuGridPanel(grid, true);
        }else{  
            sudokuGridPanel = new SudokuGridPanel(grid, false);
        }
        
    }

    public static void main(String[] args) {
        Object[] options = {"Grille vide", "Ouvrir depuis un fichier"};
        int choice = JOptionPane.showOptionDialog(null, "Choisissez une option :",
                "Options de départ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);




        boolean startWithEmptyGrid = (choice == JOptionPane.YES_OPTION);
        new Sudoku(startWithEmptyGrid);
    }
}

