package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Server {
    private final static int PORT = 1822;
    static InetAddress LOCAL_HOST;
    static {
        try {
            LOCAL_HOST = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    protected static ServerSocket createServerSocket() throws IOException {
        return new ServerSocket(PORT);
    }
}
