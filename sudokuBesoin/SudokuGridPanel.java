package sudokuBesoin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import sudokuBesoin.SudokuGridPanel.CustomMouseListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;


/**
 * Classe principale de l'interface utilisateur pour un jeu de Sudoku.
 * Cette classe étend {@link JFrame} et configure l'interface graphique du Sudoku, 
 * incluant le panneau de grille de Sudoku, les boutons de contrôle, et la logique de validation.
 * Elle gère également les événements de l'interface utilisateur pour interagir avec la grille de Sudoku.
 *
 * @author Amir KABBOURI  &amp;  Athiran NAGATHURAI
 */

public class SudokuGridPanel extends JFrame {


    /**
 * Color for navy blue used in UI elements.
 */
private Color bleu_marine = new Color(0, 0, 100);

/**
 * Panel for holding utility buttons.
 */
private JPanel buttonPanel;

/**
 * Array of game buttons.
 */
private JButton[] buttons;

/**
 * Tracks whether buttons have been clicked.
 */
private Boolean[] buttonsClicked;

/**
 * Array of utility buttons for additional functions.
 */
private JButton[] buttonsUtils = new JButton[7];

/**
 * Tracks clicks on utility buttons.
 */
private Boolean[] buttonsUtilsClicked = new Boolean[5];

/**
 * Panel for additional utility buttons.
 */
private JPanel buttonUtilsPanel;

/**
 * Labels representing the squares in the Sudoku grid.
 */
private JLabel[][] cases;

/**
 * Start time for the game in nanoseconds.
 */
private long debutJeu;

/**
 * Indicates whether a file has been opened.
 */
private boolean ficOpen = false;

/**
 * Default font used across UI elements.
 */
private Font font = new Font("Arial", Font.PLAIN, 20);

/**
 * Current state of the grid.
 */
private int[][] gridCurr;

/**
 * Indicates light mode status.
 */
private boolean isLight = true;

/**
 * Status of the grid loaded from a file.
 */
private boolean loadedFromFile;

/**
 * Represents a newly created grid; `null` if not used.
 */
private int[][] newGrid = null;

/**
 * Indicates if the auto-solve feature is active.
 */
private boolean resolver;

/**
 * Panel located at the south containing button utilities.
 */
private JPanel SouthButtonUtilsPanel;

/**
 * Main panel containing the Sudoku grid.
 */
private JPanel sudokuPanel;

/**
 * SudokuValidator used for checking the validity of moves.
 */
private SudokuValidator validator;



    /**
     * Construit un panneau de grille Sudoku avec la configuration initiale spécifiée.
     *
     * @param grid Le tableau d'entiers représentant les valeurs initiales de la grille Sudoku.
     * Utilise {@link SudokuValidator}.
     * @param loaded Indique si la grille a été chargée à partir d'un fichier.
     * @param resolv Indique si le mode de résolution automatique est activé.
     */
    public SudokuGridPanel(int[][] grid, boolean loaded, boolean resolv) {
        
        loadedFromFile = loaded;
        resolver = resolv;
        
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(grid);
        
        validator = new SudokuValidator(cases, grid);

        setSize(900, 800);
        setLocation(500, 200);
        setVisible(true);
    }
     /**
     * Initialise les composants de l'interface utilisateur, incluant la grille de Sudoku, les boutons et les panels.
     *
     * @param grid Le tableau d'entiers pour initialiser la grille.
     */
    private void initComponents(int[][] grid) {
        // Initialisation des composants de l'interface et configuration des événements
        // Cette méthode configure la grille, les boutons numériques et utilitaires,
        // et les panels pour organiser ces éléments.

        cases = new JLabel[9][9];
        buttonsClicked = new Boolean[9];
        buttons = new JButton[9];

        sudokuPanel = new JPanel(new GridLayout(9, 9));
        sudokuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        buttonPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        buttonPanel.setBorder(new EmptyBorder(90, 150, 90, 100));

        buttonUtilsPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonUtilsPanel.setBorder(new EmptyBorder(75, 50, 75, 50));

        SouthButtonUtilsPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        SouthButtonUtilsPanel.setBorder(new EmptyBorder(75, 50, 75, 50));

        buttonsUtils[0] = new JButton("Ajouter");
        buttonsUtils[1] = new JButton("Supprimer");
        buttonsUtils[2] = new JButton("Sauvegarder");
        buttonsUtils[3] = new JButton("Notes");
        buttonsUtils[4] = new JButton("Ouvrire Fichier :");
        buttonsUtils[5] = new JButton("Resolution Auto");
        buttonsUtils[6] = new JButton("Fini");

        buttonsUtils[6].addActionListener(e -> {
            
                if (validator.isGrilleComplete()) {

                    
                    JOptionPane.showMessageDialog(null, "Félicitations! La grille est complète.");
                    System.exit(0);
                }else{
                    JOptionPane.showMessageDialog(null,"Tu n'as pas fini !");
                }
                
            

        });
    
        buttonsUtils[2].addActionListener(e -> {
            JFileChooser sauvegarder = new JFileChooser();
            FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichier GRI", "gri");
            sauvegarder.addChoosableFileFilter(filtre);
            sauvegarder.setAcceptAllFileFilterUsed(false);
            int verif = sauvegarder.showSaveDialog(null);
            if (verif == JFileChooser.APPROVE_OPTION) {
                File fichier = sauvegarder.getSelectedFile();
                if (!fichier.getPath().endsWith(".gri")) {
                    fichier = new File(fichier.getPath() + ".gri");
                }

                try (FileOutputStream sortie = new FileOutputStream(fichier);
                        FileChannel channel = sortie.getChannel()) {
                    ByteBuffer buffer = ByteBuffer.allocate(4);
                    buffer.order(ByteOrder.BIG_ENDIAN);

                    for (int i = 0; i <= 8; i++) {
                        int valeur = 0;
                        int digit;
                        for (int j = 0; j <= 8; j++) {
                            String labelContent = cases[i][j].getText();
                            if(labelContent.length() > 1){
                                digit = 0;
                            }else{
                                digit = labelContent.isEmpty() ? 0 : Integer.parseInt(labelContent);
                            }
                            
                            valeur = valeur * 10 + digit;
                        }
                        buffer.putInt(valeur);
                        buffer.flip();
                        channel.write(buffer);
                        buffer.clear();
                    }
                } catch (IOException ex) {
                   System.out.println("erreur");
                }
            }
        });
    

        // Bouton qui qui permet d'ouvrir une fenetre de Jfilechooser pour ouvrir une
        // grille.
        buttonsUtils[4].addActionListener(e ->

        {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier GRI", "gri");
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    SudokuFichierLis fileHandler = new SudokuFichierLis();
                    newGrid = fileHandler.rameneLeSudoku(selectedFile.getAbsolutePath());

                    validator = new SudokuValidator(cases, newGrid);
                    ficOpen = true;
                    displayGrid(newGrid, false);
                } catch (IOException ex) {
                    System.out.println("Erreur lors de l'ouverture du fichier");
                }
            }
        });

        for (int i = 0; i < 5; i++) {
            buttonsUtils[i].addMouseListener(new CustomMouseListener());
            buttonsUtilsClicked[i] = false;
        }

        buttonUtilsPanel.add(buttonsUtils[0]);
        buttonUtilsPanel.add(buttonsUtils[1]);
        buttonUtilsPanel.add(buttonsUtils[3]);

        if(loadedFromFile && !resolver){
            SouthButtonUtilsPanel.add(buttonsUtils[4]);
            SouthButtonUtilsPanel.add(buttonsUtils[2]);
        }else if (!loadedFromFile && !resolver){
            SouthButtonUtilsPanel.add(buttonsUtils[2]);
            
        }

        if(resolver){
            buttonUtilsPanel.add(buttonsUtils[5]);
            SouthButtonUtilsPanel.add(buttonsUtils[4]);
            SouthButtonUtilsPanel.add(buttonsUtils[6]);

        }
        

        for (int i = 0; i < 9; i++) {
            buttonsClicked[i] = false;
            buttons[i] = new JButton(Integer.toString(i + 1));
            buttons[i].addMouseListener(new CustomMouseListener());
            buttonPanel.add(buttons[i]);
            buttons[i].setPreferredSize(new Dimension(50, 20));
        }

        Color lightBlue = new Color(170, 191, 205);
        Color darkerBlue = new Color(140, 161, 175);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cases[i][j] = new JLabel();
                cases[i][j].setFont(font);
                cases[i][j].addMouseListener(new CustomMouseListener());
                cases[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                cases[i][j].setOpaque(true);

                if (isLight) {
                    cases[i][j].setBackground(lightBlue);
                } else {
                    cases[i][j].setBackground(darkerBlue);
                }

                if ((i + 1) % 3 == 0 && (i != 8)) {
                    cases[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, bleu_marine));
                }
                if ((j + 1) % 3 == 0 && (j != 8)) {
                    cases[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, bleu_marine));
                }
                if (((i + 1) % 3 == 0 && (j + 1) % 3 == 0) && (i != 8 && j != 8)) {
                    cases[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, bleu_marine));
                }

                sudokuPanel.add(cases[i][j]);
                isLight = !isLight;
            }

            if (9 % 2 == 0) {
                isLight = !isLight;
            }
        }

        buttonsUtils[5].addActionListener(e -> {
            debutJeu = System.nanoTime();
            resolveGrid();
            if (validator.isGrilleComplete()) {
                long finJeu = System.nanoTime();
                double dureeJeu = (finJeu - debutJeu) / 1_000_000_000.00000; 
                JOptionPane.showMessageDialog(null, "Félicitations! La grille est complète. La durée du jeu est de " + String.format("%.5f", dureeJeu) + " secondes");
                System.exit(0);
            }
        });

        
        add(sudokuPanel, BorderLayout.CENTER);
        add(buttonUtilsPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.EAST);
        add(SouthButtonUtilsPanel, BorderLayout.SOUTH);
    }

    /**
     * Affiche la grille de Sudoku avec les valeurs spécifiées.
     *
     * @param grid Le tableau d'entiers représentant la grille Sudoku.
     * @param resolved Indique si la grille a été résolue.
     */
    private void displayGrid(int[][] grid, boolean resolved) {
        // Met à jour les labels de la grille avec les valeurs de la grille spécifiée.
        // Applique une couleur différente si la grille est résolue.
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != 0) {
                    
                    cases[i][j].setText(Integer.toString(grid[i][j]));
                    cases[i][j].setForeground(new Color(0, 0, 100));
                }

            }
        }
    }

    /**
     * Déclenche le processus de résolution de la grille de Sudoku.
     * Utilise {@link SudokuValidator} pour vérifier les solutions et mettre à jour la grille de l'interface utilisateur.
     * Affiche la grille résolue sur l'interface graphique.
     */
    private void resolveGrid() {
        SudokuValidator sudokuValidator = new SudokuValidator(cases, gridCurr);
        int[][] currentGrid = sudokuValidator.getCurrentGrid();
        SudokuGrid solver = new SudokuGrid(currentGrid);
        int[][] solvedGrid = solver.solve();
        boolean resolved = true;
        
        displayGrid(solvedGrid, resolved);
    }



    class CustomMouseListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {

            Object source = e.getSource();

            if (source instanceof JLabel) {
                JLabel clickedLabel = (JLabel) source;

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (clickedLabel.equals(cases[i][j])) {

                            if (buttonsUtilsClicked[1]) {
                                validator.isValidToAdd(i, j, 0, loadedFromFile);
                                cases[i][j].setText("");
                                
                                buttonsUtilsClicked[1] = false;
                            }
                            for (int x = 0; x < 9; x++) {

                                

                                if (buttonsClicked[x]) {

                                    if (buttonsUtilsClicked[0]) {

                                        if (validator.isValidToAdd(i, j, x + 1, loadedFromFile)) {
                                            
                                            cases[i][j].setFont(font);
                                            cases[i][j].setText(Integer.toString(x + 1));
                                            buttonsClicked[x] = false;
                                        }

                                    }

                                    else if (buttonsUtilsClicked[3]) {
                                        if (cases[i][j].getText().length() < 3) {
                                            cases[i][j].setFont(new Font("Arial", Font.PLAIN, 10));
                                            cases[i][j].setText("<html>[" + (x + 1) + "]");
                                            System.out.println(cases[i][j].getText().length());
                                        } else {
                                            String currentText = cases[i][j].getText();

                                            if (currentText.length() < 21) {
                                                if (currentText.length() < 10) {

                                                    cases[i][j].setText(currentText + " [" + (x + 1) + "]");

                                                } else if (currentText.length() == 13) {

                                                    cases[i][j].setText(currentText + "<br>[" + (x + 1) + "]");
                                                } else {

                                                    cases[i][j].setText(currentText + " [" + (x + 1) + "]</html>");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            else if (source instanceof JButton) {
                JButton clickedButton = (JButton) source;

                for (int x = 0; x < 9; x++) {
                    if (clickedButton.equals(buttons[x])) {

                        if((ficOpen == false && resolver) || (loadedFromFile && ficOpen == false)){
                            JOptionPane.showMessageDialog(null,"Il faut dabord ouvrire une grille !");
                        }


                        for (int i = 0; i < 9; i++) {
                            buttonsClicked[i] = false;
                        }

                        buttonsClicked[x] = true;
                    }
                }

                if (clickedButton.equals(buttonsUtils[0])) {
                    buttonsUtilsClicked[0] = true;
                    buttonsUtilsClicked[1] = false;
                    buttonsUtilsClicked[3] = false;
                }

                if (clickedButton.equals(buttonsUtils[1])) {
                    buttonsUtilsClicked[1] = true;
                    buttonsUtilsClicked[0] = false;
                    buttonsUtilsClicked[3] = false;

                }

                if (clickedButton.equals(buttonsUtils[3])) {
                    buttonsUtilsClicked[3] = true;
                    buttonsUtilsClicked[1] = false;
                    buttonsUtilsClicked[0] = false;

                }
            }
        }

    }

}
