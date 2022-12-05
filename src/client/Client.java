package client;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private final static int PORT = 1822;

    public Client() throws IOException, AWTException {
        Socket socket = createSocket();
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gdev = genv.getDefaultScreenDevice();
        Rectangle rect = genv.getMaximumWindowBounds();
        Robot robot = new Robot(gdev);

        new SendScreen(socket, rect, robot);
    }

    protected static Socket createSocket() throws IOException {
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
