import javax.swing.*;
import java.awt.*;
import sudokuBesoin.*;

public class Sudoku{
    private JFrame sudoku;
    private JPanel sudokuPanel;
    private JTextField[][] cases;
    private SudokuFichierLis fileHandler;

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

        try {
            int[][] grid = fileHandler.rameneLeSudoku("exemple.gri");
            displayGrid(grid);
        } catch (Exception ex) {
            System.err.println("mince alors le fichier ne s'est pas bien ouvert :/");
        }

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

    public static void main(String[] args) {
        Sudoku grlle = new Sudoku();
        
    }
}
