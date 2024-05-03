package sudokuBesoin;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuGridPanel extends JFrame {
    private JLabel[][] cases;
    private Boolean[] buttonsClicked;
    private JButton[] buttons;
    private int[][] solvedGrid;
    private JPanel sudokuPanel;
    private JPanel buttonPanel;
    private JPanel buttonUtilsPanel;
    private JPanel SouthButtonUtilsPanel;

    public SudokuGridPanel(int[][] grid) {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(grid);

        setSize(900, 800);
        setLocation(500, 200);
        setVisible(true);
    }

    private void initComponents(int[][] grid) {
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


        

        JButton ajoutButton = new JButton("Ajouter");
        JButton supprimerButton = new JButton("Supprimer");
        JButton sauvegarderButton = new JButton("Sauvegarder");
        JButton notes = new JButton("Notes");
        JButton OuvrireFic = new JButton("Ouvrire Fichier :");

        ajoutButton.addMouseListener(new CustomMouseListener());
        supprimerButton.addMouseListener(new CustomMouseListener());
        sauvegarderButton.addMouseListener(new CustomMouseListener());

        buttonUtilsPanel.add(ajoutButton);
        buttonUtilsPanel.add(supprimerButton);
        buttonUtilsPanel.add(notes);
        SouthButtonUtilsPanel.add(OuvrireFic);
        SouthButtonUtilsPanel.add(sauvegarderButton);




        for (int i = 0; i < 9; i++) {
            buttonsClicked[i] = false;
            buttons[i] = new JButton(Integer.toString(i + 1));
            buttons[i].addMouseListener(new CustomMouseListener());
            buttonPanel.add(buttons[i]);
            buttons[i].setPreferredSize(new Dimension(50, 20));
        }

        Font font = new Font("Arial", Font.PLAIN, 20);
        Color lightBlue = new Color(170, 191, 205);
        Color darkerBlue = new Color(140, 161, 175);
        boolean isLight = true;

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
                    cases[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
                }
                if ((j + 1) % 3 == 0 && (j != 8)) {
                    cases[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK));
                }
                if (((i + 1) % 3 == 0 && (j + 1) % 3 == 0) && (i != 8 && j != 8)) {
                    cases[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, Color.BLACK));
                }

                sudokuPanel.add(cases[i][j]);
                isLight = !isLight;
            }

            if (9 % 2 == 0) {
                isLight = !isLight;
            }
        }
        displayGrid(grid);

        solvedGrid = solve(grid);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                    System.out.println(solvedGrid[i][j]);
            }
        }

        add(sudokuPanel, BorderLayout.CENTER);
        add(buttonUtilsPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.EAST);
        add(SouthButtonUtilsPanel, BorderLayout.SOUTH);
    }

    private void displayGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(grid[i][j]!=0){
                    cases[i][j].setText(Integer.toString(grid[i][j]));
                }
                
            }
        }
    }

    class CustomMouseListener extends MouseAdapter {


        public void mouseClicked(MouseEvent e) {

            Object source = e.getSource();

            if (source instanceof JLabel) {
                JLabel clickedLabel = (JLabel) source;

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (clickedLabel.equals(cases[i][j])) {
                            for (int x = 0; x < 9; x++) {
                                if (buttonsClicked[x]) {
                                    if (isValidToAdd(i, j, x + 1)) {
                                        cases[i][j].setText(Integer.toString(x + 1));
                                        
                                    }
                                    buttonsClicked[x] = !buttonsClicked[x];
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
                        buttonsClicked[x] = !buttonsClicked[x];
                    }
                }
            }
        }

        private boolean isValidToAdd(int row, int col, int value) {
            SudokuGrid tempGrid = new SudokuGrid(getCurrentGrid());
            try {
                tempGrid.fixerValeurCellule(row, col, value);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    
        private int[][] getCurrentGrid() {
            int[][] grid = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    String text = cases[i][j].getText();
                    grid[i][j] = text.isEmpty() ? 0 : Integer.parseInt(text);
                }
            }
            return grid;
        }

        
    }

    public static int[][] solve(int[][] grid) {
        SudokuGrid sudoku = new SudokuGrid(grid);
        sudoku.solveSudoku();
        return sudoku.grille;
    }
}


    /* 

    class CustomMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            Object source = e.getSource();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (source.equals(cases[i][j])) {
                        for (int x = 0; x < 9; x++) {
                            if (buttonsClicked[x]) {
                                cases[i][j].setText(Integer.toString(x + 1));
                                System.out.println("test de ");
                            }
                        }
                    }
                }
            }
        }
    }
    
}*/
