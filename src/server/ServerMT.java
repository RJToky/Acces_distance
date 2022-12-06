package server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT extends Thread {
    private final static int PORT = 1822;
    boolean infiniteLoop = true;

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            System.out.println("En attente client...");
            while (infiniteLoop) {
                Socket client = ss.accept();
                new ReceiveScreen(client).start();
                System.out.println("Nouveau client connecte");
            }
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
