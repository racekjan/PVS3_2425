package exams;

//import gui.Piece;

import javax.swing.*;
import java.awt.*;

public class GUITest extends JFrame {
    public GUITest() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setSize(1000,720);
        JPanel panelNorth = new JPanel();
        JPanel panelCentre = new JPanel();
        JPanel panelSOUTH = new JPanel();
        JPanel panelWEST = new JPanel();
        JPanel panel5 = new JPanel();
        int rows = 11;
        int cols = 10;
        JTextField textinput = new JTextField("Something");
        JButton button = new JButton("OK");
        textinput.setFont(new Font("Consolas", Font.BOLD, 18));
        textinput.setHorizontalAlignment(SwingConstants.CENTER);
        button.setFont(new Font("Consolas", Font.BOLD, 18));

        panelNorth.setLayout(new FlowLayout());
        panelNorth.add(textinput);
        panelNorth.add(button);
        this.add(panelNorth,BorderLayout.NORTH);

        panelCentre.setLayout(new GridLayout(rows,cols));
        for (int i = 1; i < 111; i++) {
            panelCentre.add(new Piece(Color.magenta,i, "label"));
        }
        panelSOUTH.add(new Piece(Color.white,rows*cols, "total "));

        ButtonGroup group = new ButtonGroup();
        panelWEST.setLayout(new GridLayout(5,1));

        JRadioButton button1 = new JRadioButton("Something");
        JRadioButton button2 = new JRadioButton("Something");
        JRadioButton button3 = new JRadioButton("Something");
        JRadioButton button4 = new JRadioButton("Something");
        JRadioButton button5 = new JRadioButton("Something");

        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);
        group.add(button5);

        panelWEST.add(button1);
        panelWEST.add(button2);
        panelWEST.add(button3);
        panelWEST.add(button4);
        panelWEST.add(button5);


        this.add(panelCentre, BorderLayout.CENTER);
        this.add(panelSOUTH, BorderLayout.SOUTH);
        this.add(panelWEST, BorderLayout.WEST);







        this.pack();
    }


    public static void main(String[] args) {
        new GUITest().setVisible(true);
    }


    class Piece extends JLabel {
        public Piece(Color color, int num, String something) {
            this.setText(something + num);
            this.setBackground(color);
            this.setFont(new Font("Consolas", Font.BOLD, 20));


        }
    }
}
