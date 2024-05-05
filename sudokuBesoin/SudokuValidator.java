package sudokuBesoin;
import java.awt.Color;
import javax.swing.JLabel;
da


/**
 * Cette classe sert à valider les mouvements dans un jeu de Sudoku. Elle gère la validation
 * de l'ajout de valeurs à la grille, en vérifiant les contraintes du jeu et les règles spécifiques
 * lorsque la grille est chargée depuis un fichier.
 * @author Amir KABBOURI  &amp;  Athiran NAGATHURAI
 */
public class SudokuValidator {
    private JLabel[][] cases;
    private int[][] gridLoaded;

    /**
     * Constructeur pour initialiser le validateur de Sudoku avec la grille de jeu et les valeurs initiales.
     *
     * @param cases Grille de JLabels utilisée pour l'affichage du Sudoku.
     * @param grillePass Grille d'entiers représentant les valeurs initiales du Sudoku.
     */
    public SudokuValidator(JLabel[][] cases, int[][] grillePass) {
        this.cases = cases;
        this.gridLoaded = grillePass;
        
    }

    /**
     * Vérifie si une valeur peut être ajoutée à une certaine position de la grille,
     * avec une logique différente selon que la grille est chargée depuis un fichier ou non.
     *
     * @param row La ligne où ajouter la valeur.
     * @param col La colonne où ajouter la valeur.
     * @param value La valeur à ajouter.
     * @param loadedFromFile Booléen indiquant si la grille a été chargée depuis un fichier.
     * @return true si la valeur peut être ajoutée, false autrement.
     */
    public boolean isValidToAdd(int row, int col, int value, boolean loadedFromFile) {
        if (loadedFromFile) {
            return isValidToAddWithSolution(row, col, value);
            
        } else {
            return isValidToAddWithoutSolution(row, col, value);
        }
    }

    
    /**
     * Valide l'ajout d'une valeur à la grille, en utilisant la grille chargée pour comparer les solutions.
     *
     * @param row La ligne de la cellule.
     * @param col La colonne de la cellule.
     * @param value La valeur à fixer.
     * @return true si la valeur est conforme à la solution attendue, false autrement.
     */
    private boolean isValidToAddWithSolution(int row, int col, int value) {
        SudokuGrid tempGrid = new SudokuGrid(gridLoaded);

        SudokuGrid solver = new SudokuGrid(getCurrentGrid());
        int[][] solvedGrid = solver.solve();

        try {
            tempGrid.fixerValeurCellule(row, col, value);
        } catch (IllegalArgumentException e) {
            return false;
        }

        if (gridLoaded[row][col] != value) {
            return false;
        }

        if (solvedGrid[row][col] != value){
            return false;
        }

        cases[row][col].setForeground(Color.BLUE);
        return true;
    }


     /**
     * Valide l'ajout d'une valeur à la grille sans utiliser une solution prédéfinie.
     *
     * @param row La ligne de la cellule.
     * @param col La colonne de la cellule.
     * @param value La valeur à fixer.
     * @return true si la valeur peut être légalement ajoutée selon les règles du Sudoku, false autrement.
     */
    private boolean isValidToAddWithoutSolution(int row, int col, int value) {
        SudokuGrid tempGrid = new SudokuGrid(getCurrentGrid());

        try {
            tempGrid.fixerValeurCellule(row, col, value);
        } catch (IllegalArgumentException e) {
            return false;
        }
        cases[row][col].setForeground(new Color(0, 0, 128));
        return true;
    }


    /**
     * Vérifie si la grille de Sudoku est complètement remplie sans aucun zéro restant.
     *
     * @return true si la grille est complète, false autrement.
     */
    public boolean isGrilleComplete() {
        int[][] grid = getCurrentGrid();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return false; 
                }
            }
        }
        
        return true; 
    }


    /**
     * Affiche la grille actuelle dans la console, utilisée pour le débogage.
     */
    public void printCurrentGrid() {
        int[][] grid = getCurrentGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * Construit et renvoie une grille d'entiers représentant l'état actuel de la grille de Sudoku.
     *
     * @return La grille actuelle sous forme d'un tableau bidimensionnel d'entiers.
     */
    public int[][] getCurrentGrid() {
        int[][] grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = cases[i][j].getText();

                if (!text.isEmpty() && text.length() == 1) {
                    grid[i][j] = Integer.parseInt(text);
                } else {
                    grid[i][j] = 0;
                }
            }
        }
        return grid;
    }
}
