package client;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

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
                ImageIO.write(buffImg,"jpeg", baos);

                byte[] bytes = baos.toByteArray();
                System.out.println(bytes.length);
                dos.write(bytes);

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
