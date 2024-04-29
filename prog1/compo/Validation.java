package compo;

// Cette classe permet de verifier cette l'unicité de la grille
public class Validation{
    
    public static boolean Validation(int[][] grille, int ligne, int colonne, int valeur) {
        for (int i = 0; i < grille[ligne].length; i++) {
            if (i != colonne && grille[ligne][i] == valeur) {
                return false;
            }
        }

        for (int i = 0; i < grille.length; i++) {
            if (i != ligne && grille[i][colonne] == valeur) {
                return false;
            }

        }

        int hehehaligne = ligne - ligne % 3;
        int hehehacolonne = colonne - colonne % 3;

        for (int i = hehehaligne; i < hehehaligne + 3; i++) {
            for (int j = hehehacolonne; j < hehehacolonne + 3; j++) {

                if (i == ligne && j == colonne) {
                    continue;
                }

                if (grille[i][j] == valeur) {
                    return false;
                }
            }
        }

        return true;
    }

    
}