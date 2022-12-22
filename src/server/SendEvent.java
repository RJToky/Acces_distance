package server;

import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendEvent implements MouseMotionListener, MouseListener, KeyListener {
    private final DataOutputStream daos;

    public SendEvent(Socket socket) throws IOException {
        this.daos = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        try {
            daos.writeInt(0);
            daos.writeInt(mouseEvent.getX());
            daos.writeInt(mouseEvent.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        try {
            daos.writeInt(1);
            int xBtn = 16;
            if(mouseEvent.getButton() == MouseEvent.BUTTON3) {
                xBtn = 4;
            }
            daos.writeInt(xBtn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        try {
            daos.writeInt(2);
            int xBtn = 16;
            if(mouseEvent.getButton() == MouseEvent.BUTTON3) {
                xBtn = 4;
            }
            daos.writeInt(xBtn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        try {
            daos.writeInt(3);
            daos.writeInt(keyEvent.getKeyCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

}
