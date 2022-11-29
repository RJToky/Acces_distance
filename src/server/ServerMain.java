package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = Server.createServerSocket();

        System.out.println("Miandry...");
        Socket client = serverSocket.accept();
        System.out.println("Client connecte");
    }
}
