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
    boolean infiniteLoop = true;

    public ReceiveScreen(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedImage buffImg;
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            serverFrame = new ServerFrame();
            serverFrame.setPane(new Pane());

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

                serverFrame.getPane().setBuffImg(buffImg);
                serverFrame.getPane().repaint();
            }

        } catch (Exception e) {
            e.printStackTrace();
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
