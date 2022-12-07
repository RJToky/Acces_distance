package server;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendEvent implements MouseMotionListener {
    Socket socket;
    DataOutputStream daos;

    public SendEvent(Socket socket) throws IOException {
        this.socket = socket;
        this.daos = new DataOutputStream(this.getSocket().getOutputStream());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        try {
            daos.writeInt(0);
            daos.writeInt(mouseEvent.getX());
            daos.writeInt(mouseEvent.getY());
            System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
