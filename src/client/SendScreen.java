package client;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class SendScreen extends Thread {
    private final Socket socket;
    private final Robot robot;
    boolean infiniteLoop = true;

    public SendScreen(Socket socket, Robot robot) {
        this.socket = socket;
        this.robot = robot;
    }

    @Override
    public void run() {
        try {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rect = new Rectangle(dim);

            DataOutputStream dos;
            BufferedImage buffImg;
            ByteArrayOutputStream baos;

            while (infiniteLoop) {
                dos = new DataOutputStream(socket.getOutputStream());
                buffImg = robot.createScreenCapture(rect);

                baos = new ByteArrayOutputStream();

                ImageIO.write(buffImg, "png", baos);
                byte[] bytes = ByteBuffer.allocate(4).putInt(baos.size()).array();
                dos.write(bytes);
                dos.write(baos.toByteArray());
                dos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
