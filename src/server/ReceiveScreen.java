package server;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

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
            byte[] bytes = new byte[1024 * 1024];
            try {
                int count = 0;
                do {
                    count += dis.read(bytes, count, bytes.length - count);
                    System.out.println(count);
                } while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));

//                File file = new File("./temp.jpg");
//                FileOutputStream out = new FileOutputStream(file);
//                out.write(bytes);
//
//                buffImg = ImageIO.read(new File("./temp.jpg"));

                buffImg = ImageIO.read(new ByteArrayInputStream(bytes));
                serverFrame.getPane().setBuffImg(buffImg);
                serverFrame.getPane().repaint();

                Thread.sleep(1);

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
