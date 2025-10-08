package gui.browser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardMenu extends JFrame {
    JLabel fullName, salary, shoeSize;
    ImageIcon avatar;
    JPanel lowerMenu;
    JButton next, previous, first, last;
    int cursor;

    CardMenu(){
        cursor = 0;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,400);
        this.setResizable(false);

        this.setLayout(new BorderLayout());
        lowerMenu = new JPanel();
        lowerMenu.setLayout(new FlowLayout());


        fullName = new JLabel();
        salary = new JLabel();
        shoeSize = new JLabel();

        fullName.setFont(new Font("MV Boli", Font.BOLD, 18));
        salary.setFont(new Font("MV Boli", Font.BOLD, 18));
        shoeSize.setFont(new Font("MV Boli", Font.BOLD, 18));

//        avatar = MainMenu.personData.get(0).avatar;//zaciname na prvnim
        avatar = new ImageIcon(MainMenu.personData.get(cursor).avatar.getImage().getScaledInstance(500,450, Image.SCALE_DEFAULT));
        fullName.setText("Full name: " + MainMenu.personData.get(cursor).name + " " + MainMenu.personData.get(cursor).surname);

        fullName.setIcon(avatar);
        fullName.setHorizontalTextPosition(JLabel.CENTER);
        fullName.setVerticalTextPosition(JLabel.TOP);
        fullName.setIconTextGap(5);
        fullName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        this.add(fullName, BorderLayout.CENTER);

        shoeSize.setText("Shoe size: " + MainMenu.personData.get(cursor).shoeSize);
        salary.setText("Salary: " + MainMenu.personData.get(cursor).salary);
        previous = new JButton("Previous");
        next = new JButton("Next");
        first = new JButton("First");
        first.addActionListener(e->{
            cursor=0;
            previous.setEnabled(false);
            next.setEnabled(true);
            first.setEnabled(false);
            draw(cursor);
        });
        last = new JButton("Last");
        lowerMenu.add(first);
        lowerMenu.add(previous);
        previous.setEnabled(false);
        first.setEnabled(false);
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cursor<=0){
                    previous.setEnabled(false);
                    first.setEnabled(false);
                }else {
                    first.setEnabled(true);
                    previous.setEnabled(true);
                    next.setEnabled(true);
                    cursor--;
                    draw(cursor);
                    if (cursor<=0){
                        previous.setEnabled(false);
                        first.setEnabled(false);
                    }

                }

            }
        });



        lowerMenu.add(shoeSize);
        lowerMenu.add(salary);
        next.addActionListener(e -> {
            if (cursor>=MainMenu.personData.size()-1){
                next.setEnabled(false);
            } else {
                previous.setEnabled(true);
                first.setEnabled(true);
                cursor++;
                draw(cursor);
                if (cursor>=MainMenu.personData.size()-1){
                    next.setEnabled(false);
                }
            }


        });
        last.addActionListener(e->{
            cursor=MainMenu.personData.size()-1;
            previous.setEnabled(true);
            next.setEnabled(false);
            previous.setEnabled(true);
            first.setEnabled(true);
            draw(cursor);
        });
        lowerMenu.add(next);
        lowerMenu.add(last);

        this.add(lowerMenu, BorderLayout.SOUTH);
        this.pack();
    }
    void draw(int cursor){
        avatar = new ImageIcon(MainMenu.personData.get(cursor).avatar.getImage().getScaledInstance(500,450, Image.SCALE_DEFAULT));
        fullName.setText("Full name: " + MainMenu.personData.get(cursor).name + " " + MainMenu.personData.get(cursor).surname);
        fullName.setIcon(avatar);

        shoeSize.setText("Shoe size: " + MainMenu.personData.get(cursor).shoeSize);
        salary.setText("Salary: " + MainMenu.personData.get(cursor).salary);
    }

}
