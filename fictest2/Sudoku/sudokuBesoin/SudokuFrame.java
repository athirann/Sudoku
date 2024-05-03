package sudokuBesoin;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import sudokuBesoin.*;

public class SudokuFrame extends JFrame {
    private SudokuGridPanel sudokuPanel;
    private SudokuFichierLis fileHandler;
    private JButton[] buttons;
    private JButton[] buttonUtils;
    private JPanel buttonPanel = new JPanel();
    private JPanel buttonUtilsPanel = new JPanel();
    private JPanel SouthButtonUtilsPanel = new JPanel();

    public SudokuFrame() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setSize(900, 800);
        setLocation(500, 200);
        setVisible(true);
    }

    private void initComponents() {
        fileHandler = new SudokuFichierLis();
        int[][] grid;
        try {
            grid = fileHandler.rameneLeSudoku("exemple.gri");
        } catch (Exception ex) {
            System.err.println("Erreur lors de la lecture du fichier : " + ex.getMessage());
            return;
        }

        sudokuPanel = new SudokuGridPanel(grid);
        sudokuPanel.displayGrid(grid);
        sudokuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        buttonPanel.setLayout(new GridLayout(3, 3, 5, 5));
        buttonPanel.setBorder(new EmptyBorder(90, 50, 90, 150));

        buttonUtilsPanel.setLayout(new GridLayout(1, 3, 5, 5));
        buttonUtilsPanel.setBorder(new EmptyBorder(75, 50, 75, 50));

        SouthButtonUtilsPanel.setBorder(new EmptyBorder(75, 50, 75, 50));
        SouthButtonUtilsPanel.setLayout(new GridLayout(1, 3, 5, 5));

        buttonUtils = new JButton[3];
        buttonUtils[0] = new JButton("Ajouts");
        buttonUtils[1] = new JButton("Supprimer");
        buttonUtils[2] = new JButton("Sauvegarder");
        buttonUtils[0].addMouseListener(new CustomMouseListener());
        buttonUtils[1].addMouseListener(new CustomMouseListener());
        buttonUtils[2].addMouseListener(new CustomMouseListener());

        buttonUtilsPanel.add(buttonUtils[0], BorderLayout.WEST);
        buttonUtilsPanel.add(buttonUtils[1], BorderLayout.NORTH);
        SouthButtonUtilsPanel.add(buttonUtils[2], BorderLayout.CENTER);

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(Integer.toString(i + 1));
            buttons[i].addMouseListener(new CustomMouseListener());
            buttonPanel.add(buttons[i]);
            buttons[i].setPreferredSize(new Dimension(50, 20));
        }

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(sudokuPanel, BorderLayout.WEST);

        add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(buttonUtilsPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.EAST);
        centerPanel.add(SouthButtonUtilsPanel, BorderLayout.SOUTH);
    }

    class CustomMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton)e.getSource();
            System.out.println("Clicked: " + button.getText());
            
        }
    }
}
