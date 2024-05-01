import javax.swing.*;
import java.awt.*;
import sudokuBesoin.*;

public class Sudoku {
    private JFrame sudokuFrame;
    private int[][] grid;
    private JPanel sudokuPanel;
    private JTextField[][] cases;
    private SudokuFichierLis fileHandler;
    private SudokuGenerator sudokuGenerator; 

    public Sudoku() {
        sudokuFrame = new JFrame();
        sudokuFrame.setTitle("Sudoku");
        sudokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sudokuPanel = new JPanel(new GridLayout(9, 9));
        sudokuPanel.setBackground(Color.WHITE); 

        cases = new JTextField[9][9];
        fileHandler = new SudokuFichierLis();
        sudokuGenerator = new SudokuGenerator(); 

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cases[i][j] = new JTextField();
                cases[i][j].setHorizontalAlignment(JTextField.CENTER);
                sudokuPanel.add(cases[i][j]);
                cases[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK)); 

                if ((i + 1) % 3 == 0 && i < 8) {
                    cases[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK)); 
                }
                if ((j + 1) % 3 == 0 && j < 8) {
                    cases[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
                }
                if ((i + 1) % 3 == 0 && (j + 1) % 3 == 0 && i < 8 && j < 8) {
                    cases[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK)); 
                }
            }
        }

        try {
            grid = sudokuGenerator.generateSudoku();
            displayGrid(grid);
        } catch (Exception ex) {
            System.err.println("Erreur lors de la lecture du fichier : " + ex.getMessage());
        }

        sudokuFrame.add(sudokuPanel);
        sudokuFrame.setSize(400, 400);
        sudokuFrame.setVisible(true);
    }

    private void displayGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != 0) {
                    cases[i][j].setText(Integer.toString(grid[i][j]));
                }
            }
        }
    }

    public static void main(String[] args) {
        new Sudoku();

        
    }
}
