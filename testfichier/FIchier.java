import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.ByteOrder;

public class FIchier {
    public static void main(String[] args) throws IOException {
        File file = new File("exemple.gri");
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4);  
        buffer.order(ByteOrder.BIG_ENDIAN);  

        while (channel.read(buffer) != -1) {
            buffer.flip();
            int line = buffer.getInt();
            System.out.println("Ligne de Sudoku : " + line);
            buffer.clear();
        }

        channel.close();
        fis.close();
    }
}
