import sudokuBesoin.*;

public class Sudoku {
    private SudokuFichierLis fileHandler;
    private int[][] grid;
    private SudokuGridPanel sudokuGridPanel;

    public Sudoku(){
        fileHandler = new SudokuFichierLis();
        
        
        try {
            grid = fileHandler.rameneLeSudoku("exemple.gri");
            sudokuGridPanel = new SudokuGridPanel(grid);
        } catch (Exception ex) {
            System.err.println("Erreur lors de la lecture du fichier : " + ex.getMessage());
            return;
        }
    }
    public static void main(String[] args) {
        new Sudoku();
        
    }
}
