package compo;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.security.auth.login.CredentialException;
import javax.swing.JFileChooser;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.ByteOrder;

public class Fichier {

    public static void ouvrir(JFrame fenetre, int[][] grille, JTextField[][] textFields) throws IOException {
        JFileChooser ouvrir = new JFileChooser();
        int verif = ouvrir.showOpenDialog(null);
        if (verif == JFileChooser.APPROVE_OPTION) {
            File fichierchoisi = ouvrir.getSelectedFile();
            FileInputStream entre = new FileInputStream(fichierchoisi);
            FileChannel channel = entre.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.order(ByteOrder.BIG_ENDIAN);
            int i = 0, j = 0;
            while (i < 9 && channel.read(buffer) != -1) {
                buffer.flip();
                grille[i][j] = buffer.getInt();
                textFields[i][j].setText(Integer.toString(grille[i][j])); 
                j++;
                buffer.clear();
                if (j == 9) {
                    j = 0;
                    i++;
                }
            }

            channel.close();
            entre.close();

        }

    }

}