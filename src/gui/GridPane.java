package gui;

import javax.swing.*;
import java.awt.*;

public class GridPane extends JFrame {
    public GridPane() throws HeadlessException{
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        for (int i = 0; i < 10; i++) {
            this.add(new Piece(Color.BLUE));
        }
        this.pack();
    }

    public static void main(String[] args) {
        new GridPane().setVisible(true);
    }
}
class Piece extends JLabel{
    public Piece (Color color){
        this.setText("");
        this.setFont(new Font("Consolas", Font.BOLD, 20));

    }
}
