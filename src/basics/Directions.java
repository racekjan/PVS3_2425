package basics;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Directions extends JFrame implements KeyListener {
    public Directions(){
        this.setSize(700,700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(null);
        JLabel block = new JLabel();
        block.setBounds(0,0, 50, 50);
        this.add(block);
        this.addKeyListener(this);
    }
    public static void main(String[] args) {
        new Directions().setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a':
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Zmáčknuto: " + e.getKeyChar());

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
