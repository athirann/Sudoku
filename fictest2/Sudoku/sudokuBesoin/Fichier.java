package sudokuBesoin;

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

    public static void ouvrir(JFrame fenetre, JTextField[][] textFields) throws IOException {
        JFileChooser ouvrir = new JFileChooser();
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichier GRI", "gri");
        ouvrir.addChoosableFileFilter(filtre);
        ouvrir.setAcceptAllFileFilterUsed(false);
        int verif = ouvrir.showOpenDialog(null);

        if (verif == JFileChooser.APPROVE_OPTION) {
            File fichierchoisi = ouvrir.getSelectedFile();
            FileInputStream entre = new FileInputStream(fichierchoisi);
            FileChannel channel = entre.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.order(ByteOrder.BIG_ENDIAN);

            for (int i = 0; i <= 8; i++) {
                channel.read(buffer);
                buffer.flip();
                int valeur = buffer.getInt();
                String nbr = Integer.toString(valeur);
                // permet d'ajouter des 0 au debut si la ligne contient moin de 9 chiffre
                while (nbr.length() < 9) {
                    nbr = "0" + nbr;
                }

                for (int j = 0; j <= 8; j++) {
                    // permet de retirer les 0 de la grille
                    char charAtPosition = nbr.charAt(j);
                    if (charAtPosition == '0') {
                        textFields[i][j].setText("");
                    } else {
                        textFields[i][j].setText(String.valueOf(charAtPosition));
                    }
                }
                buffer.clear();
            }

            channel.close();
            entre.close();

        }

    }

    public static void sauvegarder(JFrame fenetre, JTextField[][] textFields) throws IOException {
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

            FileOutputStream sortie = new FileOutputStream(fichier);
            FileChannel channel = sortie.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.order(ByteOrder.BIG_ENDIAN);

            for (int i = 0; i <= 8; i++) {
                for (int j = 0; j <= 8; j++) {
                    int valeur = Integer.parseInt(textFields[i][j].getText());
                    buffer.putInt(valeur);
                    buffer.flip();
                    channel.write(buffer);
                    buffer.clear();
                    channel.write(ByteBuffer.wrap(System.getProperty("line.separator").getBytes()));
                }


            }

        }

    }

}
