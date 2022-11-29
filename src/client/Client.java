package client;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private final static int PORT = 1822;

    protected static Socket createSocket(String host) throws IOException {
        return new Socket(host, PORT);
    }
}
