package server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ReceiveScreen extends Thread {
    Socket socket;
    ServerFrame serverFrame;

    public ReceiveScreen(Socket socket) {
        this.socket = socket;
        start();
    }

    public void run() {
        DataInputStream dis;
        BufferedImage buffImg;

        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        serverFrame = new ServerFrame();
        serverFrame.setPane(new Pane());

        while (true) {
            try {
                byte[] sizeAr = new byte[4];
                dis.readFully(sizeAr);

                int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
                byte[] imageAr = new byte[size];
                int totalRead = 0, currentRead;

                while (totalRead < size && (currentRead = dis.read(imageAr, totalRead, size-totalRead)) > 0) {
                    totalRead += currentRead;
                }

                buffImg = ImageIO.read(new ByteArrayInputStream(imageAr));
                buffImg.getScaledInstance(buffImg.getWidth(null), buffImg.getHeight(null), Image.SCALE_FAST);

                serverFrame.getPane().setBuffImg(buffImg);
                serverFrame.getPane().repaint();

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ServerFrame getServerFrame() {
        return serverFrame;
    }

    public void setServerFrame(ServerFrame serverFrame) {
        this.serverFrame = serverFrame;
    }

}
