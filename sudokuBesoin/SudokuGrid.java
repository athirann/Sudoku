package sudokuBesoin;


/**
 * La classe SudokuGrid gère une grille de Sudoku, offrant des fonctionnalités pour configurer,
 * manipuler et résoudre le puzzle. Elle intègre des méthodes pour valider et placer des valeurs
 * selon les règles du Sudoku, telles que l'unicité des chiffres par ligne, colonne et sous-grille 3x3.
 * @author Amir KABBOURI  &amp;  Athiran NAGATHURAI
 */

public class SudokuGrid {
    /**
     * Représentation de la grille de Sudoku sous forme d'un tableau bidimensionnel d'entiers,
     * où chaque élément représente une valeur dans une cellule de la grille.
     */
    public int[][] grille;


    /**
     * Constructeur de la classe SudokuGrid qui initialise la grille avec les valeurs fournies.
     *
     * @param grille Tableau bidimensionnel d'entiers initialisant la grille de Sudoku.
     */
    public SudokuGrid(int[][] grille) {
        this.grille = grille;
    }


    /**
     * Cette méthode tente de fixer une valeur à une position donnée dans la grille de Sudoku.
     * La valeur est acceptée si elle respecte toutes les contraintes de règles du Sudoku, notamment
     * l'unicité dans la ligne, la colonne et la sous-grille 3x3 correspondante.
     *
     * @param ligne Indice de la ligne où la valeur doit être placée.
     * @param colonne Indice de la colonne où la valeur doit être placée.
     * @param valeur La valeur à insérer dans la grille à la position spécifiée.
     * @throws IllegalArgumentException Si la valeur proposée ne respecte pas les contraintes du Sudoku.
     */
    public void fixerValeurCellule(int ligne, int colonne, int valeur) {
        if(valeur == 0){
            grille[ligne][colonne] = 0;
        }
        else if (estValeurValide(valeur) && estValeurUniqueDansLigne(ligne, valeur) && estValeurUniqueDansColonne(colonne, valeur) && estValeurUniqueDansSousGrille(ligne, colonne, valeur)) {
            grille[ligne][colonne] = valeur;
        } else {
            throw new IllegalArgumentException("Valeur invalide pour la cellule Sudoku : " + valeur);
        }
    }

    /**
     * Méthode pour résoudre le puzzle Sudoku. Elle utilise un algorithme de backtracking pour trouver
     * une solution valide.
     *
     * @return Un tableau bidimensionnel d'entiers représentant la grille résolue, ou null si aucune
     *         solution n'est trouvée.
     */
    public int[][] solveSudoku() {
        if (resoudre()) {
            return grille;
        } else {
            System.err.println("Aucune solution trouvée pour la grille Sudoku.");
            return null;
        }
    }


    /**
     * Méthode qui tente de résoudre la grille de Sudoku en utilisant un algorithme de backtracking,
     * qui explore récursivement toutes les possibilités jusqu'à trouver une solution valide ou épuiser toutes les options.
     *
     * @return true si la grille est résolue avec succès, false si aucune solution n'est trouvée.
     */
    public boolean resoudre() {
        return resoudre(0, 0);
    }

    
    /**
     * Lance le processus de résolution du Sudoku et renvoie la grille résolue.
     *
     * @return Un tableau bidimensionnel d'entiers représentant la grille résolue.
     */
    public int[][] solve() {
        /*
     * Methode pour resoudre la grille afin de n'avoir qu'un resultat possible et de
     * la comparer aux entrée de l'utilisateur
     */
        solveSudoku();
        return grille;
    }

    /**
     * Méthode récursive privée qui implémente l'algorithme de backtracking pour résoudre le Sudoku.
     * Elle parcourt la grille cellule par cellule et tente de placer des valeurs valides en suivant
     * les règles du Sudoku.
     *
     * @param ligne Indice de la ligne courante pour la résolution.
     * @param colonne Indice de la colonne courante pour la résolution.
     * @return true si une solution complète est trouvée, false si un blocage est rencontré.
     */
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


    /**
     * Valide si une valeur est appropriée pour être placée dans une cellule donnée en respectant les règles de Sudoku.
     *
     * @param valeur La valeur à tester pour la cellule.
     * @return true si la valeur est entre 1 et 9 inclusivement, false autrement.
     */
    private boolean estValeurValide(int valeur) {
        return valeur >= 1 && valeur <= 9;
    }

    /**
     * Renvoie la valeur actuelle d'une cellule spécifique dans la grille.
     *
     * @param ligne L'indice de la ligne de la cellule.
     * @param colonne L'indice de la colonne de la cellule.
     * @return La valeur de la cellule à l'indice spécifié.
     */
    public int valeurCellule(int ligne, int colonne) {
        return grille[ligne][colonne];
    }

    /**
     * Vérifie si une valeur donnée est unique dans la ligne spécifiée de la grille.
     *
     * @param ligne L'indice de la ligne à vérifier.
     * @param valeur La valeur à tester pour l'unicité.
     * @return true si la valeur n'apparaît pas ailleurs dans la ligne, false si elle est déjà présente.
     */
    private boolean estValeurUniqueDansLigne(int ligne, int valeur) {
        for (int colonne = 0; colonne < 9; colonne++) {
            if (grille[ligne][colonne] == valeur) {
                return false;
            }
        }
        return true;
    }


    /**
     * Vérifie si une valeur donnée est unique dans la colonne spécifiée de la grille.
     *
     * @param colonne L'indice de la colonne à vérifier.
     * @param valeur La valeur à tester pour l'unicité.
     * @return true si la valeur n'apparaît pas ailleurs dans la colonne, false si elle est déjà présente.
     */
    private boolean estValeurUniqueDansColonne(int colonne, int valeur) {
        for (int ligne = 0; ligne < 9; ligne++) {
            if (grille[ligne][colonne] == valeur) {
                return false;
            }
        }
        return true;
    }


    /**
     * Vérifie si une valeur donnée est unique dans la sous-grille 3x3 qui contient la cellule spécifiée.
     *
     * @param ligne L'indice de la ligne de la cellule.
     * @param colonne L'indice de la colonne de la cellule.
     * @param valeur La valeur à tester pour l'unicité dans la sous-grille.
     * @return true si la valeur n'apparaît pas ailleurs dans la sous-grille 3x3, false si elle est déjà présente.
     */
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
