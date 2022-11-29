package client;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
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
        try {
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage buffImg;
        while (true) {
            buffImg = robot.createScreenCapture(rect);
            try {
                ImageIO.write(buffImg, "jpeg", dos);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
