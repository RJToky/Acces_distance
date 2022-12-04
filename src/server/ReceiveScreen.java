package server;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class ReceiveScreen extends Thread {
    Socket socket;

    public ReceiveScreen(Socket socket) {
        this.socket = socket;
        start();
    }

    public void run() {
        DataInputStream dis;
        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
//            byte[] bytes = new byte[1024 * 1024];
//            int i = 0;
//            try {
//                int count = 0;
//                do {
//                    count += dis.read(bytes, count, bytes.length - count);
//                    System.out.println(count);
//                }
//                while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39));
//                System.out.println("vita");
//            } catch (IOException e) {
//                e.printStackTrace();
//                break;
//            }
        }
    }
}
