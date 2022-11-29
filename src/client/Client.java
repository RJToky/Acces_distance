package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private final static int PORT = 1822;

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
