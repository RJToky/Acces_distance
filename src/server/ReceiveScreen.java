package server;

import java.io.DataInputStream;
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
            try {
                System.out.println(dis.read());
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
