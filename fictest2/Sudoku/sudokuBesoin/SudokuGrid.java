package sudokuBesoin;

public class SudokuGrid {
    public int[][] grille;

    public SudokuGrid(int[][] grille) {
        this.grille = grille;
    }

    public void fixerValeurCellule(int ligne, int colonne, int valeur) {
        if (estValeurValide(valeur) && estValeurUniqueDansLigne(ligne, valeur) && estValeurUniqueDansColonne(colonne, valeur) && estValeurUniqueDansSousGrille(ligne, colonne, valeur)) {
            grille[ligne][colonne] = valeur;
        } else {
            throw new IllegalArgumentException("Valeur invalide pour la cellule Sudoku : " + valeur);
        }
    }

    public int[][] solveSudoku() {
        if (resoudre()) {
            return grille;
        } else {
            System.err.println("Aucune solution trouvée pour la grille Sudoku.");
            return null;
        }
    }

    public boolean resoudre() {
        return resoudre(0, 0);
    }

    private boolean resoudre(int ligne, int colonne) {
        if (ligne == 9) {
            return true;
        }

        if (grille[ligne][colonne] != 0) {
            if (colonne == 8) {
                return resoudre(ligne + 1, 0); 
            } else {
                return resoudre(ligne, colonne + 1);
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (estValeurValide(num) && estValeurUniqueDansLigne(ligne, num) && estValeurUniqueDansColonne(colonne, num) && estValeurUniqueDansSousGrille(ligne, colonne, num)) {
                grille[ligne][colonne] = num;
                if (resoudre(colonne == 8 ? ligne + 1 : ligne, (colonne + 1) % 9)) {
                    return true;
                }
                grille[ligne][colonne] = 0;
            }
        }
        return false; 
    }

    private boolean estValeurValide(int valeur) {
        return valeur >= 1 && valeur <= 9;
    }

    public int valeurCellule(int ligne, int colonne) {
        return grille[ligne][colonne];
    }

    private boolean estValeurUniqueDansLigne(int ligne, int valeur) {
        for (int colonne = 0; colonne < 9; colonne++) {
            if (grille[ligne][colonne] == valeur) {
                return false;
            }
        }
        return true;
    }

    private boolean estValeurUniqueDansColonne(int colonne, int valeur) {
        for (int ligne = 0; ligne < 9; ligne++) {
            if (grille[ligne][colonne] == valeur) {
                return false;
            }
        }
        return true;
    }

    private boolean estValeurUniqueDansSousGrille(int ligne, int colonne, int valeur) {
        int debutLigne = ligne - ligne % 3;
        int debutColonne = colonne - colonne % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grille[i + debutLigne][j + debutColonne] == valeur) {
                    return false;
                }
            }
        }
        return true;
    }
}
