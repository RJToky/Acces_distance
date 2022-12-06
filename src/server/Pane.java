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
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(buffImg, 0, 0, buffImg.getWidth(null), buffImg.getHeight(null),null);

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
