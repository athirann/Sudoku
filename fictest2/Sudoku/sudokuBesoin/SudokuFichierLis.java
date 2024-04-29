package sudokuBesoin;

import java.io.*;

public class SudokuFichierLis {
    public int[][] rameneLeSudoku(String filePath) throws IOException {
        int[][] grid = new int[9][9];
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(filePath));
            int i = 0;
            String donnee;
            while ((donnee = reader.readLine()) != null && i < 9) {
                donnee = padZeros(donnee);//on rajoute des 0 devant les chiffre ou yen a pas
                for (int j = 0; j < 9; j++) {
                    char c = donnee.charAt(j);
                        int num = Character.getNumericValue(c);
                        grid[i][j] = num;
                }
                i++;
            }
        } finally {
            if (reader != null) {
                reader.close(); 
            }
        }
        
        return grid;
    }
    
    private String padZeros(String donnee) {
        while (donnee.length() < 9) {
            donnee = "0" + donnee;
        }
        return donnee;
    }
}
