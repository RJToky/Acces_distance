package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    private final static int PORT = 1822;
    private final static InetAddress LOCAL_HOST;
    static {
        try {
            LOCAL_HOST = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public Server() throws IOException {
        ServerSocket serverSocket = createServerSocket();

        System.out.println("Miandry...");
        Socket socket = serverSocket.accept();
        System.out.println("Client connecte");

        new ReceiveScreen(socket);
    }

    protected static ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(PORT);
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
