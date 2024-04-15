import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generateur {

    private static final int TAILLE = 9;

    static class Impression {
        Impression() {
            String sudoku = "grille.gri";

            try (FileWriter grille = new FileWriter(sudoku, true)) {

                grille.write("zaza");
                grille.close();
            }

            catch (IOException e) {
                System.out.println("Erreur lors de la creation du fichier");
            }

        }

    }

    public static void Generation(int[][] grille) {

        Random random = new Random();

        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                int aleeeeeaaaatoire;
                do {
                    aleeeeeaaaatoire = random.nextInt(9) + 1;
                    // System.out.println(aleeeeeaaaatoire);
                } while (!validation(grille, i, j, aleeeeeaaaatoire));
                grille[i][j] = aleeeeeaaaatoire;
                System.out.print(grille[i][j] + " ");
            }
            System.out.println();
        }

    }

    
    public static boolean validation(int[][] grille, int ligne, int colonne, int heheha) {

        // System.out.println("coucou");


        for (int i = 0; i < TAILLE; i++) {
            if (grille[ligne][i] == heheha) {
                return false;
            }
        }

        for (int i = 0; i < TAILLE; i++) {
            if (grille[i][colonne] == heheha) {
                return false;
            }
        }

        int hehehaligne = ligne - ligne % 3;
        int hehehacolonne = colonne - colonne % 3;

        for (int i = hehehaligne; i < hehehaligne + 3; i++) {
            for (int j = hehehacolonne; j < hehehacolonne + 3; j++) {
                if (grille[i][j] == heheha) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void effacerlaligneeeee(int [][] grille, int ligne){
        for (int j =0; j< TAILLE; j++){
            grille[ligne][j]=0;

        } 


    }

    public static void main(String[] args) {
        int[][] grille = new int[TAILLE][TAILLE];
        Generation(grille);
        // new Impression();

    }

}
