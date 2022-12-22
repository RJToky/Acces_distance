package server;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

public class ServerFrame extends JFrame {
    private final BufferStrategy bs;
    private final Graphics g;
    private BufferedImage image;

    public ServerFrame(Socket socket) throws IOException {
        Canvas canvas = new Canvas();
        canvas.addMouseMotionListener(new SendEvent(socket));
        canvas.addMouseListener(new SendEvent(socket));
        canvas.addKeyListener(new SendEvent(socket));

        Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(SCREEN_SIZE.width, SCREEN_SIZE.height);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Server");
        setVisible(true);
        add(canvas);

        canvas.createBufferStrategy(3);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    protected void update() {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(image, 0, 0, null);
        bs.show();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
