package sudokuBesoin;
import java.awt.Color;
import javax.swing.JLabel;



public class SudokuValidator {
    private JLabel[][] cases;
    private int[][] gridLoaded;

    public SudokuValidator(JLabel[][] cases, int[][] grillePass) {
        this.cases = cases;
        this.gridLoaded = grillePass;
        
    }

    public boolean isValidToAdd(int row, int col, int value, boolean loadedFromFile) {
        if (loadedFromFile) {
            return isValidToAddWithSolution(row, col, value);
            
        } else {
            return isValidToAddWithoutSolution(row, col, value);
        }
    }

    private boolean isValidToAddWithSolution(int row, int col, int value) {
        SudokuGrid tempGrid = new SudokuGrid(gridLoaded);



        try {
            tempGrid.fixerValeurCellule(row, col, value);
        } catch (IllegalArgumentException e) {
            return false;
        }

        if (gridLoaded[row][col] != value) {
            return false;
        }

        cases[row][col].setForeground(Color.BLUE);
        return true;
    }

    private boolean isValidToAddWithoutSolution(int row, int col, int value) {
        SudokuGrid tempGrid = new SudokuGrid(getCurrentGrid());

        try {
            tempGrid.fixerValeurCellule(row, col, value);
        } catch (IllegalArgumentException e) {
            return false;
        }
        cases[row][col].setForeground(new Color(0, 0, 128));
        return true;
    }

    public boolean isGrilleComplete() {
        int[][] grid = getCurrentGrid();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return false; 
                }
            }
        }
        
        return true; 
    }

    public void printCurrentGrid() {
        int[][] grid = getCurrentGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getCurrentGrid() {
        int[][] grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = cases[i][j].getText();

                if (!text.isEmpty() && text.length() == 1) {
                    grid[i][j] = Integer.parseInt(text);
                } else {
                    grid[i][j] = 0;
                }
            }
        }
        return grid;
    }
}
