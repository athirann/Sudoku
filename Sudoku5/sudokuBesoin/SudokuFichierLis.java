package sudokuBesoin;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.ByteOrder;


import java.io.*;


public class SudokuFichierLis {
    public int[][] rameneLeSudoku(String filePath) throws IOException {
        int[][] grid = new int[9][9];
        FileInputStream fileInputStream = null;
        FileChannel channel = null;
        
        try {
            fileInputStream = new FileInputStream(filePath);
            channel = fileInputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4); // Pour lire des entiers de 4 octets
            buffer.order(ByteOrder.BIG_ENDIAN);

            for (int i = 0; i < 9; i++) {
                channel.read(buffer);
                buffer.flip();
                int valeur = buffer.getInt();
                String nbr = Integer.toString(valeur);
                // Ajoute des zéros au début si la ligne contient moins de 9 chiffres
                while (nbr.length() < 9) {
                    nbr = "0" + nbr;
                }
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = nbr.charAt(j) - '0'; // Convertit le caractère en chiffre et le stocke dans la grille
                }
                buffer.clear();
            }
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        
        return grid;
    }
}
