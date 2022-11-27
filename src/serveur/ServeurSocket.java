package serveur;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurSocket {
    public static void main(String[] args) {

        try {
            int port = 1822;
            ServerSocket serverSocket = new ServerSocket(port);

            ObjectInputStream inputStream;
            Socket client = serverSocket.accept();
            while (true) {
                inputStream = new ObjectInputStream(client.getInputStream());
                System.out.println(inputStream.readObject());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
