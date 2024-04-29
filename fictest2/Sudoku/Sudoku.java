import javax.swing.*;
import java.awt.*;
import sudokuBesoin.*;

public class Sudoku {
    private JFrame sudoku;
    private int[][] grid;
    private JPanel sudokuPanel;
    private JTextField[][] cases;
    private SudokuFichierLis fileHandler;
    private SudokuGrid sudokuGrid;

    public Sudoku() {
        sudoku = new JFrame();
        sudoku.setTitle("Sudoku");

        sudokuPanel = new JPanel(new GridLayout(9, 9));
        cases = new JTextField[9][9];
        fileHandler = new SudokuFichierLis();
       

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cases[i][j] = new JTextField();
                cases[i][j].setHorizontalAlignment(JTextField.CENTER);
                sudokuPanel.add(cases[i][j]);
            }
        }

        // Charger la grille depuis le fichier
        try {
            grid = fileHandler.rameneLeSudoku("exemple.gri");
            sudokuGrid = new SudokuGrid(grid);
            displayGrid(grid);
            loadGridIntoSudoku(grid);
        } catch (Exception ex) {
            System.err.println("Erreur lors de la lecture du fichier : " + ex.getMessage());
        }

        solveSudoku();

        sudoku.getContentPane().add(sudokuPanel, BorderLayout.CENTER);
        sudoku.setSize(400, 400);
        sudoku.setVisible(true);
    }

    private void displayGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cases[i][j].setText(Integer.toString(grid[i][j]));
            }
        }
    }

    private void loadGridIntoSudoku(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuGrid.setCellValue(i, j, grid[i][j]);
            }
        }
    }

    private void solveSudoku() {
        if (sudokuGrid.solve()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = sudokuGrid.getCellValue(i, j);
                }
            }
            displayGrid(grid);
        } else {
            System.err.println("Aucune solution trouvée pour la grille Sudoku.");
        }
    }

    public static void main(String[] args) {
        new Sudoku();
    }
}
