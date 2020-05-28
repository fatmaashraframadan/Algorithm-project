package Output;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Algorithms.Algorithms;
import Algorithms.MaximumFlow;
import Graph.*;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 285, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Algorithms");
		setResizable(false);
		contentPane.setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(37, 115, 72, 22);
		contentPane.add(textArea);

		JLabel lblNewLabel = new JLabel("Enter the number of vertices");
		lblNewLabel.setBounds(37, 100, 183, 13);
		contentPane.add(lblNewLabel);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(37, 180, 133, 22);
		contentPane.add(textArea_1);

		JLabel lblNewLabel_1 = new JLabel("Enter the vertice");
		lblNewLabel_1.setBounds(37, 155, 125, 13);
		contentPane.add(lblNewLabel_1);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(37, 270, 135, 22);
		contentPane.add(textArea_2);

		JLabel lblNewLabel_2 = new JLabel("Enter the Edge");
		lblNewLabel_2.setBounds(37, 245, 125, 13);
		contentPane.add(lblNewLabel_2);

		
		
		JRadioButton rdbtnMaxFlow = new JRadioButton("Maximum Flow");
		rdbtnMaxFlow.setBounds(37, 30, 154, 21);
		contentPane.add(rdbtnMaxFlow);
		
		JRadioButton rdbtnDijestra = new JRadioButton("Dijkestra");
		rdbtnDijestra.setBounds(37, 60, 105, 21);
		contentPane.add(rdbtnDijestra);
		
		ArrayList<String> getEdges = new ArrayList<String>();
		
		JButton btnAddEdges = new JButton("Add edge");
		btnAddEdges.setBounds(35, 302, 135, 21);
		btnAddEdges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getEdges.add(textArea_2.getText());
				textArea_2.setText("");
			}
		});
		contentPane.add(btnAddEdges);
		
		
		ArrayList<String> getVertices = new ArrayList<String>();
		JButton btnAddAnotherVertex = new JButton("Add Vertex");
		btnAddAnotherVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getVertices.add(textArea_1.getText());
				textArea_1.setText("");
			}
		});
		btnAddAnotherVertex.setBounds(37, 212, 133, 21);
		contentPane.add(btnAddAnotherVertex);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.setBounds(81, 338, 89, 39);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getNumberOfVertices = textArea.getText();
				int numberOfVertices = Integer.parseInt(getNumberOfVertices);
				
				for (int i = 0; i < getEdges.size(); i++) {
					String [] split = null;
					String vertex1, vertex2 , cost;
					split = getEdges.get(i).split(" ");
					vertex1 = split[0];
					vertex2 = split[1];
					cost = split[2];
				}
				
			}
		});
		contentPane.add(btnNewButton);

	}
}
