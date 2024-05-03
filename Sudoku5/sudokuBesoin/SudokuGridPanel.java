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
    private Color bleu_marine = new Color(0, 0, 100);
    private boolean isLight = true;
    private JButton[] buttonsUtils = new JButton[5];
    private Boolean[] buttonsUtilsClicked = new Boolean[5];

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


        

        buttonsUtils[0] = new JButton("Ajouter");
        buttonsUtils[1] = new JButton("Supprimer");
        buttonsUtils[2] = new JButton("Sauvegarder");
        buttonsUtils[3] = new JButton("Notes");
        buttonsUtils[4] = new JButton("Ouvrire Fichier :");

        for(int i =0;i<5;i++){
            buttonsUtils[i].addMouseListener(new CustomMouseListener());
            buttonsUtilsClicked[i] = false;
        }

        buttonUtilsPanel.add(buttonsUtils[0]);
        buttonUtilsPanel.add(buttonsUtils[1]);
        buttonUtilsPanel.add(buttonsUtils[3]);
        SouthButtonUtilsPanel.add(buttonsUtils[4]);
        SouthButtonUtilsPanel.add(buttonsUtils[2]);




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
        displayGrid(grid);

        solvedGrid = solve(grid);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                    System.out.print(solvedGrid[i][j]+" ");
            }
            System.out.println("");
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
                    cases[i][j].setForeground(new Color(0, 0, 100)); 
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

                                    if(buttonsUtilsClicked[1]){
                                        cases[i][j].setText("");
                                    }


                                    else if (buttonsClicked[x]) {

                                        if(buttonsUtilsClicked[0]){
                                            if (isValidToAdd(i, j, x + 1)) {
                                                cases[i][j].setText(Integer.toString(x + 1));
                                            }
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

                            if (clickedButton.equals(buttonsUtils[0])) {
                                buttonsUtilsClicked[0] = !buttonsUtilsClicked[0]; 
                                buttonsUtilsClicked[1] = false; 
                            }

                            if (clickedButton.equals(buttonsUtils[1])) {
                                buttonsUtilsClicked[1] = !buttonsUtilsClicked[1]; 
                                buttonsUtilsClicked[0] = false; 
                                
                            }

                            if (clickedButton.equals(buttonsUtils[3])) {
                                buttonsUtilsClicked[3] = !buttonsUtilsClicked[3]; 
                                
                            }
            }
        }

        private boolean isValidToAdd(int row, int col, int value) {
            SudokuGrid tempGrid = new SudokuGrid(getCurrentGrid());
            try {
                tempGrid.fixerValeurCellule(row, col, value);
            } catch (IllegalArgumentException e) {
                return false;
            }

            if (solvedGrid[row][col] != value) {
                return false;
            }

            cases[row][col].setForeground(Color.BLUE);
            return true;
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


