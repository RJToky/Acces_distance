package client;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client extends Thread {
    private final static int PORT = 1822;

    @Override
    public void run() {
        try {
            Socket socket = getSocket();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rect = new Rectangle(dim);
            Robot robot = new Robot();

            new SendScreen(socket, rect, robot).start();
            new ReceiveEvent(socket, robot).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static Socket getSocket() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String host;
        Socket socket;
        while (true) {
            System.out.print("Adresse IP : ");
            host = reader.readLine();
            try {
                socket = new Socket(host, PORT);
                break;
            } catch (Exception e) {
                System.out.println("Connection timed out");
            }
        }
        return socket;
    }
}