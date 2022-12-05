package server;

import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame {

    public ServerFrame() {
        Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(SCREEN_SIZE.width, SCREEN_SIZE.height);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Server");
        add(new JPanel());
        setVisible(true);

    }
}
