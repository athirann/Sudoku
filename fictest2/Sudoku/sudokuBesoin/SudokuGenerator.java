package sudokuBesoin;

import java.util.Random;

public class SudokuGenerator {
    public int[][] grid;
    private Random random;

    public SudokuGenerator() {
        this.grid = new int[9][9];
        this.random = new Random();
    }

    public int[][] generateSudoku() {
        do {
            clearGrid();
            fillGrid();
        } while (!isValidGrid());
        return grid;
    }

    private boolean isValidGrid() {
        SudokuSolver solver = new SudokuSolver(grid);
        return solver.solveSudoku() != null;
    }

    private void clearGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = 0;
            }
        }
    }

    private void fillGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int randomNum = 0;
                boolean valid = false;
                while (!valid) {
                    randomNum = random.nextInt(9) + 1;
                    valid = isValid(i, j, randomNum);
                }
                grid[i][j] = randomNum;
            }
        }
    }

    private boolean isValid(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] emptyGrid = new int[9][9];

        // Remplissage de la grille avec des zéros en utilisant la classe SudokuGenerator
        SudokuGenerator generator = new SudokuGenerator();
        int[][] filledGrid = generator.generateSudoku();

        
    }
}
