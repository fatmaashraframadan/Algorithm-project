package Output;

import oracle.jrockit.jfr.JFR;
import org.apache.commons.collections15.functors.ConstantTransformer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tst extends JFrame {
    ImageIcon img;
    JLabel label;

    JPanel con = new JPanel();

    tst() {
        setTitle("Graph Algorithms");
        // setLayout(new FlowLayout());
        img = new ImageIcon(getClass().getResource("HomaPage.jpg"));
        label = new JLabel(img);

        RoundButton b = new RoundButton("Start");
        b.setForeground(Color.black);
        b.setEnabled(true);
        b.setBounds(20, 600, 80, 50);
        b.setBackground(Color.lightGray);
        // b.setBorder(BorderFactory.createEtchedBorder(0)); //Raised Border Line
        b.setBackground(Color.lightGray);
        // b.setBorder(BorderFactory.createBevelBorder(1, Color.BLUE, Color.blue)); // Two Colors Inner Bevel


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
        tst t = new tst();
        t.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        t.setVisible(true);
        t.pack();
        t.setSize(900, 693);
        t.setResizable(false);
    }
}
