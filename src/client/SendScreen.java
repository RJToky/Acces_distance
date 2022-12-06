package client;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class SendScreen extends Thread {
    Socket socket;
    Rectangle rect;
    Robot robot;

    public SendScreen(Socket socket, Rectangle rect, Robot robot) {
        this.socket = socket;
        this.rect = rect;
        this.robot = robot;
        start();
    }

    public void run() {
        DataOutputStream dos;
        BufferedImage buffImg;
        ByteArrayOutputStream baos;

        while (true) {
            try {
                dos = new DataOutputStream(socket.getOutputStream());
                buffImg = robot.createScreenCapture(rect);

                baos = new ByteArrayOutputStream();

                ImageIO.write(buffImg, "png", baos);
                byte[] bytes = ByteBuffer.allocate(4).putInt(baos.size()).array();
                dos.write(bytes);
                dos.write(baos.toByteArray());
                dos.flush();

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
