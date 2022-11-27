package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {
    public static void main(String[] args) {
        try {
            int port = 1822;
            Socket socket = new Socket(InetAddress.getLocalHost(), port);

            ObjectOutputStream output;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (socket.isConnected()) {
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(reader.readLine());
            }
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
