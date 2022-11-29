package server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = Server.createServerSocket();

        System.out.println("Miandry...");
        Socket client = serverSocket.accept();
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setVisible(true);
        System.out.println("Client connecte");

        client.close();
        serverSocket.close();
    }
}
