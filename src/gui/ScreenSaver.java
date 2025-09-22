package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ScreenSaver extends JFrame {
    public ScreenSaver(){
        Canvas panel = new Canvas();
        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        pack();
    }
    public static void main(String[] args) {
        new ScreenSaver().setVisible(true);
    }
}

class Canvas extends JPanel implements ActionListener {
    final int WIDTH = 1200;
    final int HEIGHT = 1200;
    int x = 0;
    int y = 0;
    int xVelocity = 5;
    int yVelocity = 2;
    Image bouncing;
    Random random = new Random(5);
    Canvas(){
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.BLACK);
        bouncing = new ImageIcon("src\\dvd.png").getImage();
        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawImage(bouncing, x, y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (x >= WIDTH - bouncing.getWidth(null) || x<0) {
            xVelocity*=-1;
           // xVelocity+=random;
        }
        x+=xVelocity;
        if (y >= HEIGHT - bouncing.getWidth(null) || y<0) {
            yVelocity*=-1;
        }
        y+=yVelocity;
        repaint();
    }
}
