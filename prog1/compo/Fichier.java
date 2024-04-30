package compo;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.security.auth.login.CredentialException;
import javax.swing.JFileChooser;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.ByteOrder;

public class Fichier {

    public static void ouvrir(JFrame fenetre, int[][] grille, JTextField[][] textFields) throws IOException {
        JFileChooser ouvrir = new JFileChooser();
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichier GRI", "gri");
        ouvrir.addChoosableFileFilter(filtre);
        ouvrir.setAcceptAllFileFilterUsed(false);
        int verif = ouvrir.showOpenDialog(null);
        // String nom = "Choisisez une grille";
        // ouvrir.setDialogTitle(nom); a voir pour plustard
        if (verif == JFileChooser.APPROVE_OPTION) {
            File fichierchoisi = ouvrir.getSelectedFile();
            FileInputStream entre = new FileInputStream(fichierchoisi);
            FileChannel channel = entre.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.order(ByteOrder.BIG_ENDIAN);

            for (int i = 0; i <= 9; i++) {
                channel.read(buffer);
                buffer.flip();
                int valeur = buffer.getInt();
                String nbr = Integer.toString(valeur);
                int k = 0;
                if (nbr.length() < 9) {

                    textFields[i][k].setText("0");
                    k++;

                }

                for (int j = 0; j <= 9; j++) {
                    // buffer.flip();
                    if (textFields[i][j].equals("0")) {
                        j++;
                    } else {

                        textFields[i][j].setText(String.valueOf(nbr.charAt(j)));
                    }
                }
                buffer.clear();
            }

            channel.close();
            entre.close();

        }

    }
    // String nbr = Integer.toString(valeur);
    // char chiffre = nbr.charAt(1);

    public static void sauvegarder(JFrame fenetre) throws IOException {
        JFileChooser sauvegarder = new JFileChooser();
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichier GRI", "gri");
        sauvegarder.addChoosableFileFilter(filtre);
        sauvegarder.setAcceptAllFileFilterUsed(false);
        int verif = sauvegarder.showSaveDialog(null);
        File fichier = sauvegarder.getSelectedFile();
        String nomdufichier = fichier.getName();
        System.out.println(nomdufichier);

    }

}