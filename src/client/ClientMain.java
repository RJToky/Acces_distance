package client;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket socket = Client.createSocket();
    }
}
