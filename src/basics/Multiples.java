package basics;

import javax.swing.*;
import java.awt.*;

public class Multiples extends JFrame {
    public Multiples() throws HeadlessException{
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        JTextField textinput = new JTextField("Area here");
        JButton draw = new JButton("Draw");

        textinput.setFont(new Font("Consolas", Font.BOLD, 30));
        textinput.setHorizontalAlignment(SwingConstants.CENTER);
        draw.setFont(new Font("Consolas", Font.BOLD, 30));

        draw.addActionListener(e -> {
            int number;
            try {
                number = Integer.parseInt(textinput.getText());
                //new GridPane(number).setVisible(true);
            }catch (NumberFormatException ne){
                JOptionPane.showMessageDialog(null,"Not a number");
            }
        });

        this.add(textinput);
        this.add(draw);
        this.pack();

    }

    public static void main(String[] args) {
        new Multiples().setVisible(true);
    }
}
