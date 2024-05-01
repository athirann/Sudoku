package sudokuBesoin;

public class SudokuGrid {
    public int[][] grid;

    public SudokuGrid(int[][] grid) {
        this.grid = grid;
    }


    public void setCellValue(int row, int col, int value) {
        if (isValidValue(value) && isValueUniqueInRow(row, value) && isValueUniqueInCol(col, value) && isValueUniqueInSubgrid(row, col, value)) {
            grid[row][col] = value;
        } else {
            throw new IllegalArgumentException("Invalid value for Sudoku cell: " + value);
        }
    }

    public boolean solve() {
        return solve(0, 0);
    }

    private boolean solve(int row, int col) {
        if (row == 9) {
            return true;
        }
        
        if (grid[row][col] != 0) {

            if (col == 8) {
                return solve(row + 1, 0); 
            } else {
                return solve(row, col + 1);
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (isValidValue(num) && isValueUniqueInRow(row, num) && isValueUniqueInCol(col, num) && isValueUniqueInSubgrid(row, col, num)) {
                grid[row][col] = num;
                if (solve(col == 8 ? row + 1 : row, (col + 1) % 9)) {
                    return true;
                }
                grid[row][col] = 0;
            }
        }
        return false; 
    }

    private boolean isValidValue(int value) {
        return value >= 1 && value <= 9;
    }

    public int getCellValue(int row, int col) {
        return grid[row][col];
    }
    

    private boolean isValueUniqueInRow(int row, int value) {
        for (int col = 0; col < 9; col++) {
            if (grid[row][col] == value) {
                return false;
            }
        }
        return true;
    }

    private boolean isValueUniqueInCol(int col, int value) {
        for (int row = 0; row < 9; row++) {
            if (grid[row][col] == value) {
                return false;
            }
        }
        return true;
    }

    private boolean isValueUniqueInSubgrid(int row, int col, int value) {
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == value) {
                    return false;
                }
            }
        }
        return true;
    }
}
