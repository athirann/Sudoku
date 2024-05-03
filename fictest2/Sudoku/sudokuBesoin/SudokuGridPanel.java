package sudokuBesoin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SudokuGridPanel extends JPanel {
    private JLabel[][] cases;
    

    public SudokuGridPanel(int[][] grid) {
        super(new GridLayout(9, 9));
        setPreferredSize(new Dimension(400, 400));
        
        cases = new JLabel[9][9];
        Font font = new Font("Arial", Font.PLAIN, 20); 
        Color lightBlue = new Color(170, 191, 205);
        Color darkerBlue = new Color(140, 161, 175); 
        boolean isLight = true;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cases[i][j] = new JLabel();
                cases[i][j].setFont(font);
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

                // Ajout d'un MouseListener à chaque JLabel
                cases[i][j].addMouseListener(new CustomMouseListener());

                add(cases[i][j]);
                isLight = !isLight; 
            }

            if (9 % 2 == 0) {
                isLight = !isLight;
            }
        }

        
    }

    public void displayGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != 0) {
                    cases[i][j].setText(Integer.toString(grid[i][j]));
                }
            }
        }
    }

    class CustomMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel)e.getSource();
            System.out.println("Clicked: " + label.getText());
        }
    }
}
