package gui;

import javax.swing.*;
import java.awt.*;

public class OwnComponent extends JFrame {
    public OwnComponent(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(400,400);
        this.setLayout(new GridLayout(2,5));
        for (int i = 0; i < 10; i++) {
            this.add(new HeaderTile("nadpis", 1));
        }
    }

    public static void main(String[] args) {
        new OwnComponent().setVisible(true);
    }
}

class HeaderTile extends JPanel{
    String header;
    int num;

    public HeaderTile(String header, int num) {
        this.header = header;
        this.num = num;
        JLabel headerLabel = new JLabel(header);
        JLabel numLabel = new JLabel(String.valueOf(num));

        headerLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        numLabel.setFont(new Font("Consolas", Font.BOLD, 18));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numLabel.setBackground(Color.GREEN);
        numLabel.setOpaque(true);

        headerLabel.setBackground(Color.white);

        this.setLayout(new GridLayout(5,2));

    }
}
