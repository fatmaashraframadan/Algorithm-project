package Output;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Graph.Graph;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Output extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	int step_num = -1;
	GraphPanel gp;

	/**
	 * Create the frame.
	 */

	public Output(List<Graph> result, boolean ismaxflow) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextArea step_number = new JTextArea();
		step_number.setBounds(94, 25, 55, 22);
		contentPane.add(step_number);
		step_num++;
		gp = new GraphPanel(result.get(step_num), "step number :" + step_num, 10, 50,true);
		JButton back = new JButton("back");
		JButton next = new JButton("next");
		step_number.setText(step_num + "");
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
				gp = new GraphPanel(result.get(step_num), "step number :" + step_num, 10, 50,true);
				step_number.setText(step_num + "");
				next.setEnabled(true);
				if (step_num <= 0) {
					back.setEnabled(false);
				}
			}
		});
		back.setBounds(10, 74, 89, 23);
		contentPane.add(back);

		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gp.colseFrame();
				step_num++;
				gp = new GraphPanel(result.get(step_num), "step number :" + step_num, 10, 50,true);
				step_number.setText(step_num + "");
				back.setEnabled(true);
				if (step_num >= result.size() - 1) {
					next.setEnabled(false);
				}
			}
		});
		next.setBounds(109, 74, 89, 23);
		contentPane.add(next);

		JButton final_result = new JButton("final result");
		final_result.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gp.colseFrame();
				step_num = result.size() - 1;
				gp = new GraphPanel(result.get(step_num), "step number :" + step_num, 10, 50,true);
				step_number.setText(step_num + "");
				back.setEnabled(false);
				next.setEnabled(false);
			}
		});
		final_result.setBounds(232, 74, 111, 23);
		contentPane.add(final_result);
		JLabel lblStepNumber = new JLabel("step number");
		lblStepNumber.setBounds(10, 27, 74, 20);
		contentPane.add(lblStepNumber);

		if (ismaxflow) 
		{
			JTextArea maxFlowValue = new JTextArea();
			maxFlowValue.setBounds(275, 25, 55, 22);
			contentPane.add(maxFlowValue);
			maxFlowValue.setText(GUISteps.maxflowvalue + "");

			JLabel maxflowvalue = new JLabel("max flow value");
			maxflowvalue.setBounds(172, 26, 96, 21);
			contentPane.add(maxflowvalue);
		}
		
		
	}
}
