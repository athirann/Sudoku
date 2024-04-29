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
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichier GRI","gri");
        ouvrir.addChoosableFileFilter(filtre);
        ouvrir.setAcceptAllFileFilterUsed(false);
        int verif = ouvrir.showOpenDialog(null);
        //String nom = "Choisisez une grille"; 
        //ouvrir.setDialogTitle(nom); a voir pour plustard
        if (verif == JFileChooser.APPROVE_OPTION) {
            File fichierchoisi = ouvrir.getSelectedFile();
            FileInputStream entre = new FileInputStream(fichierchoisi);
            FileChannel channel = entre.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.order(ByteOrder.BIG_ENDIAN);
            int i = 0, j = 0;
            while (i < 9 && channel.read(buffer) != -1) {
                buffer.flip();
                byte b = buffer.get();
                int digit = b;
                grille[i][j] = digit;
                textFields[i][j].setText(Integer.toString(digit)); 
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

    public static void sauvegarder  (JFrame fenetre) throws IOException {
        JFileChooser sauvegarder = new JFileChooser();
        FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichier GRI","gri");
        sauvegarder.addChoosableFileFilter(filtre);
        sauvegarder.setAcceptAllFileFilterUsed(false);
        int verif =  sauvegarder.showSaveDialog(null);
        File fichier = sauvegarder.getSelectedFile();
        String nomdufichier = fichier.getName();
        System.out.println(nomdufichier);
        

    }       

}