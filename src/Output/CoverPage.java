package Output;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoverPage extends JFrame {
    ImageIcon img;
    JLabel label;

    JPanel con = new JPanel();

    CoverPage() {
        setTitle("Graph Algorithms");
        setLocation(40, 10);
        img = new ImageIcon(getClass().getResource("HomaPage.jpg"));
        label = new JLabel(img);

        JLabel name_1 = new JLabel("1- Peter Malak Mikael             20170079");
        name_1.setBounds(22, 250, 400, 30);
        name_1.setFont(new Font("Arial", Font.BOLD, 14));
        add(name_1);

        JLabel name_2 = new JLabel("2- Fatma Ashraf Ramadan      20170192");
        name_2.setBounds(22, 280, 400, 30);
        name_2.setFont(new Font("Arial", Font.BOLD, 14));
        add(name_2);

        JLabel name_3 = new JLabel("3- Mostafa Badr El-Sayed       20170286");
        name_3.setBounds(22, 310, 450, 30);
        name_3.setFont(new Font("Arial", Font.BOLD, 14));
        add(name_3);

        JLabel name_4 = new JLabel("4- Mina Farid Saad                  20170310");
        name_4.setBounds(22, 340, 400, 30);
        name_4.setFont(new Font("Arial", Font.BOLD, 14));
        add(name_4);

        JLabel name_5 = new JLabel("5- Nourhan Atef Radwan        20170325");
        name_5.setBounds(22, 370, 400, 30);
        name_5.setFont(new Font("Arial", Font.BOLD, 14));
        add(name_5);

        RoundButton b = new RoundButton("Start");
        b.setForeground(Color.black);
        b.setEnabled(true);
        b.setBounds(20, 590, 85, 60);
        b.setBackground(Color.lightGray);

        b.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                getContentPane().setLayout(new BorderLayout(0, 0));
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {

                            GUI window = new GUI();
                            
                            window.initialize();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        add(b);
        add(label);
    }

    public static void main(String[] args) {
        CoverPage t = new CoverPage();
        t.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        t.setVisible(true);
        t.pack();
        t.setSize(900, 693);
        t.setResizable(false);
    }
}
