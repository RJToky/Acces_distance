package server;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pane extends JPanel {
    BufferedImage buffImg;
    public Pane() {
        Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(SCREEN_SIZE.width, SCREEN_SIZE.height);
    }

    public void paint(Graphics g) {
        try {
            super.paint(g);
            g.drawImage(this.getBuffImg(), 0, 0, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public void setBuffImg(BufferedImage buffImg) {
        this.buffImg = buffImg;
    }

}
