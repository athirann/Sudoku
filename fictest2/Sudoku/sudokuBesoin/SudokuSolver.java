package sudokuBesoin;

public class SudokuSolver {
    
    public int[][] grid;
    public SudokuGrid sudokuGrid;
    
    public SudokuSolver(int[][] grid) {
        this.grid = grid;
        this.sudokuGrid = new SudokuGrid(grid);
    }

    
    public int[][] solveSudoku() {
        if (sudokuGrid.solve()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = sudokuGrid.getCellValue(i, j);
                }
            }
        } else {
            System.err.println("Aucune solution trouvée pour la grille Sudoku.");
        }
        return grid;
    }
}
