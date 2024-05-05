package sudokuBesoin;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.ByteOrder;

/**
 * Cette classe est destinée à charger une grille de Sudoku à partir d'un fichier.
 * Elle lit les données du fichier et les transforme en une grille de Sudoku sous forme de tableau bidimensionnel.
 * @author Amir KABBOURI &amp; Athiran NAGATHURAI
 */
public class SudokuFichierLis {

    /**
     * Constructeur par défaut de SudokuFichierLis.
     * Crée une instance de la classe capable de lire des grilles de Sudoku à partir de fichiers.
     */
    public SudokuFichierLis() {
        // Constructeur vide, aucune initialisation spéciale requise.
    }

    /**
     * Lit un fichier contenant une grille de Sudoku et retourne cette grille sous forme d'un tableau d'entiers.
     *
     * @param filePath Le chemin d'accès complet au fichier contenant la grille de Sudoku.
     * @return Un tableau bidimensionnel d'entiers représentant la grille de Sudoku chargée du fichier.
     * @throws IOException Si une erreur d'entrée/sortie se produit pendant la lecture du fichier.
     */
    public int[][] rameneLeSudoku(String filePath) throws IOException {
        int[][] grid = new int[9][9];
        FileInputStream fileInputStream = null;
        FileChannel channel = null;
        
        try {
            fileInputStream = new FileInputStream(filePath);
            channel = fileInputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4); 
            buffer.order(ByteOrder.BIG_ENDIAN);

            for (int i = 0; i < 9; i++) {
                channel.read(buffer);
                buffer.flip();
                int valeur = buffer.getInt();
                String nbr = Integer.toString(valeur);

                while (nbr.length() < 9) {
                    nbr = "0" + nbr;
                }
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = nbr.charAt(j) - '0'; 
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
