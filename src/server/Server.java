package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int PORT = 1822;

    public Server() throws IOException {
        ServerSocket serverSocket = createServerSocket();

        System.out.println("Miandry...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connecte");

        new ReceiveScreen(socket);
        serverSocket.close();
    }

    protected static ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(PORT);
    }
}
