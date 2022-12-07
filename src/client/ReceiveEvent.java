package client;

import java.awt.*;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveEvent extends Thread {
    Socket socket;
    Robot robot;
    boolean infiniteLoop = true;

    public ReceiveEvent(Socket socket, Robot robot) {
        this.socket = socket;
        this.robot = robot;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(this.getSocket().getInputStream());
            int command, x, y;
            while (infiniteLoop) {
                command = in.readInt();
                switch (command) {
                    case 0 -> {
                        x = in.readInt();
                        y = in.readInt();
                        robot.mouseMove(x, y);
                        System.out.println(x + " " + y);
                    }
                    case 1 -> {}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
