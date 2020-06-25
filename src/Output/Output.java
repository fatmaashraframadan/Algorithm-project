/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

import Graph.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

/**
 * @author fatma
 */
@SuppressWarnings("unchecked")

public class Output extends JFrame {

    private JFrame frame;
    private JPanel contentPane;
    int step_num = -1;
    GraphPanel gp;

    /**
     * Create the frame.
     */

    public Output(List<Graph> result, boolean ismaxflow, boolean isDirected, boolean isDijkstra) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 487, 396);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JLabel step_number = new JLabel();
        step_number.setBounds(111, 27, 89, 23);
        contentPane.add(step_number);
        step_num++;
        gp = new GraphPanel(result.get(step_num), "step number :" + step_num, 10, 50, isDirected, isDijkstra);
        JButton back = new JButton("back");
        JButton next = new JButton("next");
        step_number.setText(step_num + "" + " out of " + (result.size() - 1));
        if (step_num <= 0) {
            back.setEnabled(false);
        }
        if (step_num >= result.size() - 1) {
            next.setEnabled(false);
        }
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                gp.colseFrame();
                step_num--;
                gp = new GraphPanel(result.get(step_num), "step number :" + step_num, 10, 50, isDirected, isDijkstra);
                step_number.setText(step_num + "" + " out of " + (result.size() - 1));
                next.setEnabled(true);
                if (step_num <= 0) {
                    back.setEnabled(false);
                }
            }
        });
        back.setBounds(10, 316, 89, 23);
        contentPane.add(back);

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gp.colseFrame();
                step_num++;
                gp = new GraphPanel(result.get(step_num), "step number :" + step_num, 10, 50, isDirected, isDijkstra);
                step_number.setText(step_num + "" + " out of " + (result.size() - 1));

                back.setEnabled(true);
                if (step_num >= result.size() - 1) {
                    next.setEnabled(false);
                }
            }
        });
        next.setBounds(140, 316, 89, 23);
        contentPane.add(next);

        JButton final_result = new JButton("final result");
        final_result.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gp.colseFrame();
                step_num = result.size() - 1;
                gp = new GraphPanel(result.get(step_num), "step number :" + step_num, 10, 50, isDirected, isDijkstra);
                step_number.setText(step_num + "" + " out of " + (result.size() - 1));

                if (step_num <= 0) {
                    back.setEnabled(false);
                } else back.setEnabled(true);
                next.setEnabled(false);
            }
        });
        final_result.setBounds(332, 316, 111, 23);
        contentPane.add(final_result);
        JLabel lblStepNumber = new JLabel("step number :");
        lblStepNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblStepNumber.setBounds(10, 27, 89, 23);
        contentPane.add(lblStepNumber);
        
        JLabel lblAllSteps = new JLabel("all steps");
        lblAllSteps.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblAllSteps.setBounds(20, 65, 82, 22);
        contentPane.add(lblAllSteps);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 90, 438, 215);
        contentPane.add(scrollPane);
        
        JTextArea steps = new JTextArea();
        scrollPane.setViewportView(steps);
        steps.setText(GUISteps.steps);

        if (ismaxflow) 
        {
            JLabel maxFlowValue = new JLabel();
            maxFlowValue.setBounds(324, 27, 62, 23);
            contentPane.add(maxFlowValue);
            maxFlowValue.setText(GUISteps.maxflowvalue + "");

            JLabel maxflowvalue = new JLabel("max flow value :");
            maxflowvalue.setFont(new Font("Tahoma", Font.BOLD, 11));
            maxflowvalue.setBounds(210, 28, 104, 22);
            contentPane.add(maxflowvalue);
        }


    }

    public void clear() {
        gp.colseFrame();
        this.dispose();
        ;
    }
}
