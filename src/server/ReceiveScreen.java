package server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ReceiveScreen extends Thread {
    private final Socket socket;
    boolean infiniteLoop = true;

    public ReceiveScreen(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedImage buffImg;
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            ServerFrame serverFrame = new ServerFrame(socket);

            while (infiniteLoop) {
                byte[] bytes = new byte[4];
                dis.readFully(bytes);

                int size = ByteBuffer.wrap(bytes).asIntBuffer().get();
                byte[] data = new byte[size];
                int totalRead = 0, currentRead;

                while (totalRead < size && (currentRead = dis.read(data, totalRead, size-totalRead)) > 0) {
                    totalRead += currentRead;
                }

                buffImg = ImageIO.read(new ByteArrayInputStream(data));
                buffImg.getScaledInstance(buffImg.getWidth(null), buffImg.getHeight(null), Image.SCALE_FAST);

                serverFrame.setImage(buffImg);
                serverFrame.update();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
