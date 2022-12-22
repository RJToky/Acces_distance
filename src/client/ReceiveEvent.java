package client;

import java.awt.*;
import java.io.DataInputStream;
import java.net.Socket;

public class ReceiveEvent extends Thread {
    private final Socket socket;
    private final Robot robot;
    boolean infiniteLoop = true;

    public ReceiveEvent(Socket socket, Robot robot) {
        this.socket = socket;
        this.robot = robot;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            int command, x, y;
            while (infiniteLoop) {
                command = in.readInt();
                switch (command) {
                    case 0 -> {
                        x = in.readInt();
                        y = in.readInt();
                        robot.mouseMove(x, y);
                    }
                    case 1 -> robot.mousePress(in.readInt());
                    case 2 -> robot.mouseRelease(in.readInt());
                    case 3 -> robot.keyPress(in.readInt());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
