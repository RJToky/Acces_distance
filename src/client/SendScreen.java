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
        DataOutputStream dos = null;
        BufferedImage buffImg;
        File file;
        FileInputStream fin = null;
        String fileName = "./temp.jpeg";

        int i;

        while (true) {
            try {
                dos = new DataOutputStream(socket.getOutputStream());
                file = new File(fileName);
                file.createNewFile();
                buffImg = robot.createScreenCapture(rect);
                ImageIO.write(buffImg, "jpeg", file);
                dos.writeUTF(fileName);

                fin = new FileInputStream(file);
                byte[] readData = new byte[1024];
                while ((i = fin.read(readData)) != -1) {
                    dos.write(readData, 0, i);
                }
                file.delete();

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            assert fin != null;
            fin.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
