package Output;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import com.sun.org.apache.bcel.internal.generic.ALOAD;

import Algorithms.Algorithms;
import Algorithms.Dijkstra;
import Algorithms.MaximumFlow;
import Graph.*;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

public class GUI extends JFrame {

	public GUI() {
		getContentPane().setLayout(new BorderLayout(0, 0));
	}

	private JPanel contentPane;
	int numberOfVertices = 0;
	boolean maxFlowCheck = false;
	boolean dijkestraCheck = false;
	ArrayList<String> getEdges = new ArrayList<String>();
	ArrayList<String> getVertices = new ArrayList<String>();
	String startPoint, endPoint;
	Graph input;
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */

	public void initialize() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 20, 400, 650);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Team Number: 33");
		setResizable(false);
		setVisible(true);
		contentPane.setLayout(null);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(25, 90, 133, 22);
		contentPane.add(textArea_1);

		JLabel lblNewLabel_1 = new JLabel("Insert a Vertex");
		lblNewLabel_1.setBounds(25, 75, 125, 13);
		contentPane.add(lblNewLabel_1);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(25, 160, 135, 22);
		contentPane.add(textArea_2);

		JLabel lblNewLabel_2 = new JLabel("Insert an Edge");
		lblNewLabel_2.setBounds(25, 145, 125, 13);
		contentPane.add(lblNewLabel_2);

		JCheckBox maxFlowBox = new JCheckBox("Maximum Flow");
		maxFlowBox.setBounds(10, 30, 154, 21);
		contentPane.add(maxFlowBox);

		JCheckBox dijkestraBox = new JCheckBox("Dijkestra");
		dijkestraBox.setBounds(160, 30, 105, 21);
		contentPane.add(dijkestraBox);

		JCheckBox unDirected = new JCheckBox("unDirected");
		unDirected.setBounds(270, 30, 105, 21);
		contentPane.add(unDirected);
		
		JLabel lblNewLabel_32 = new JLabel("Insert The start Point");
		lblNewLabel_32.setBounds(200, 75, 125, 13);
		contentPane.add(lblNewLabel_32);

		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(200, 90, 133, 22);
		contentPane.add(textArea_3);

		JLabel lblNewLabel_4 = new JLabel("Insert The end Point");
		lblNewLabel_4.setBounds(200, 145, 125, 13);
		contentPane.add(lblNewLabel_4);

		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(200, 160, 133, 22);
		contentPane.add(textArea_4);

		JLabel lblNewLabel_5 = new JLabel("Steps.....");
		lblNewLabel_5.setBounds(25, 235, 125, 13);
		contentPane.add(lblNewLabel_5);

		JTextArea textArea_5 = new JTextArea();
		textArea_5.setBounds(25, 250, 350, 200);
		contentPane.add(textArea_5);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 250, 350, 200);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(textArea_5);

		JButton btnAddEdges = new JButton("Add edge");
		btnAddEdges.setBounds(25, 185, 135, 21);
		btnAddEdges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp=textArea_2.getText();
				String[] inp = temp.split(" ");
				if (inp.length != 3) {
					JOptionPane.showMessageDialog(null, "wrong edge input");
				}
				else{
					getEdges.add(textArea_2.getText());
					textArea_2.setText("");
				} 

			}
		});
		contentPane.add(btnAddEdges);

		JButton btnAddAnotherVertex = new JButton("Add Vertex");
		btnAddAnotherVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean correction = false;
				for (int i = 0; i < getVertices.size(); i++) {
					if (textArea_1.getText().equals(getVertices.get(i))) {
						correction = true;
						break;
					}
				}
				if (correction) {
					JOptionPane.showMessageDialog(null, "This vertex is already exists");
				} else {
					getVertices.add(textArea_1.getText());
					textArea_1.setText("");
					numberOfVertices++;
				}
			}
		});
		btnAddAnotherVertex.setBounds(25, 115, 133, 21);
		contentPane.add(btnAddAnotherVertex);

		JLabel lblNewLabel_3 = new JLabel("Team Members : ");
		lblNewLabel_3.setBounds(20, 440, 460, 60);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_33 = new JLabel("1-Peter Malak 20170079        2-Fatma Ashraf 20170192");
		lblNewLabel_33.setBounds(20, 470, 460, 60);
		contentPane.add(lblNewLabel_33);

		JLabel lblNewLabel_34 = new JLabel("3-Mostafa Badr 20170286     4-Mina Farid 20170310");
		lblNewLabel_34.setBounds(20, 500, 460, 60);
		contentPane.add(lblNewLabel_34);

		JLabel lblNewLabel_35 = new JLabel("5-Nourhan Atef 20170325");
		lblNewLabel_35.setBounds(20, 530, 460, 60);
		contentPane.add(lblNewLabel_35);

		JButton btnNewButton = new JButton("Run");
		btnNewButton.setBounds(250, 560, 60, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(getVertices);
				input = new Graph(numberOfVertices, !unDirected.isSelected(), getVertices);
				maxFlowCheck = maxFlowBox.isSelected();
				dijkestraCheck = dijkestraBox.isSelected();

				startPoint = textArea_3.getText();
				endPoint = textArea_4.getText();

				numberOfVertices = getVertices.size();
				GUISteps.steps = "Number of vertices is: " + numberOfVertices + "\n Vertices: ";
				for (int i = 0; i < getVertices.size(); i++) {
					GUISteps.steps += getVertices.get(i) + " ";
				}
				GUISteps.steps += "\n Edges: ";

				for (int i = 0; i < getEdges.size(); i++) {
					GUISteps.steps += getEdges.get(i) + " ";
				}
				GUISteps.steps += "\nThe Output will be....\n";

				Algorithms algorithm = null;
				if (maxFlowCheck && !dijkestraCheck) {
					algorithm = new MaximumFlow();

				} else if (!maxFlowCheck && dijkestraCheck) {
					algorithm = new Dijkstra();
				} else {
					JOptionPane.showMessageDialog(null, "Sorry, you must select at least one option");
				}

				for (int i = 0; i < getEdges.size(); i++) {
					String[] split = null;
					split = getEdges.get(i).split(" ");
					input.addEdge(split[0], split[1], Integer.parseInt(split[2]));
				}

				GraphPanel gp = new GraphPanel(input, "input", 500, 600, !unDirected.isSelected());
				// drawRepresentation(input ,input.representation, input.representationcost,
				// "Input");
				List<Graph> OutPut = algorithm.run(input, startPoint, endPoint);
				Output frame = new Output(OutPut, maxFlowCheck);
				frame.setLocation(500, 100);
				frame.setVisible(true);
				// Graph output = algorithm.run(input, startPoint, endPoint);

				// GraphPanel gp1 = new GraphPanel(output,"output");
				// drawRepresentation(output,output.representation, output.representationcost,
				// "Output");

				textArea_5.setText(GUISteps.steps);
				GUISteps.steps = "";
			}
		});
		contentPane.add(btnNewButton);

	}
}
