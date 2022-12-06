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
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gdev = genv.getDefaultScreenDevice();
            Rectangle rect = genv.getMaximumWindowBounds();
            Robot robot = new Robot(gdev);

            new SendScreen(socket, rect, robot).start();
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