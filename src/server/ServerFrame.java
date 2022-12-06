package server;

import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame {
    Pane pane;

    public ServerFrame() {
        Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(SCREEN_SIZE.width, SCREEN_SIZE.height);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Server");
        setVisible(true);
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
        add(pane);
    }
}
