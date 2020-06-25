/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

import Algorithms.Algorithms;
import Algorithms.Dijkstra;
import Algorithms.MaximumFlow;
import Graph.Graph;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author fatma
 */
@SuppressWarnings("unchecked")
/*
*/

public class GUI extends JFrame {

	public GUI() {
	}

	int counter = 0, counter2 = 0;
	private JPanel contentPane;
	int numberOfVertices = 0;
	boolean maxFlowCheck = false, dijkestraCheck = false, flag = false;
	public ArrayList<String> getEdges = new ArrayList<String>();
	public ArrayList<String> getVertices = new ArrayList<String>();
	String startPoint, endPoint;
	Graph input;
	GraphPanel gp;
	Output frame_output;

	/**
	 * Create the frame.
	 */

	public void initialize() throws FontFormatException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 10, 550, 450);

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Graph Algorithms");

		int r = 238, g = 238, b = 238;
		getContentPane().setBackground(new Color(r, g, b));
		setResizable(true);
		setVisible(true);
		contentPane.setLayout(null);

		
		JLabel vertex_Label = new JLabel("Insert a Vertex");
		vertex_Label.setBounds(85, 60, 150, 13);
		vertex_Label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		contentPane.add(vertex_Label);

		JTextField vertex_textArea = new JTextField();
		vertex_textArea.setBounds(45, 80, 180, 25);
		contentPane.add(vertex_textArea);

		JLabel edge_Label = new JLabel("Insert an Edge");
		edge_Label.setBounds(340, 50, 150, 30);
		edge_Label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		contentPane.add(edge_Label);

		JTextField edge_textArea = new JTextField();
		edge_textArea.setBounds(300, 80, 180, 25);
		contentPane.add(edge_textArea);

		JCheckBox maxFlowBox = new JCheckBox("Maximum Flow");
		maxFlowBox.setBounds(110, 250, 154, 21);
		maxFlowBox.setBackground(new Color(r, g, b));
		maxFlowBox.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		contentPane.add(maxFlowBox);

		JCheckBox dijkestraBox = new JCheckBox("Dijkestra");
		dijkestraBox.setBounds(320, 250, 105, 21);
		dijkestraBox.setBackground(new Color(r, g, b));
		dijkestraBox.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		contentPane.add(dijkestraBox);

		JCheckBox Directed = new JCheckBox("Directed");
		Directed.setBounds(110, 290, 154, 21);
		Directed.setBackground(new Color(r, g, b));
		Directed.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		contentPane.add(Directed);

		JLabel source_Label = new JLabel("Source Vertex");
		source_Label.setBounds(85, 160, 155, 30);
		source_Label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		contentPane.add(source_Label);

		JTextField source_textArea = new JTextField();
		source_textArea.setBounds(45, 190, 180, 25);
		contentPane.add(source_textArea);

		JLabel destination_Label = new JLabel("Destination Vertex");
		destination_Label.setBounds(325, 170, 150, 13);
		destination_Label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		contentPane.add(destination_Label);

		JTextField destination_textArea = new JTextField();
		destination_textArea.setBounds(300, 190, 180, 25);
		contentPane.add(destination_textArea);

		RoundButton btnAddAnotherVertex = new RoundButton("Add Vertex");
		btnAddAnotherVertex.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		btnAddAnotherVertex.setBounds(70, 110, 133, 25);
		btnAddAnotherVertex.setBackground(new Color(165, 198, 255));
		btnAddAnotherVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = vertex_textArea.getText();
				String[] inp = temp.split(" ");
				if (inp.length != 1 || vertex_textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "wrong vertex input");
				} else {
					boolean check = true;
					for (int i = 0; i < getVertices.size(); i++) {
						if (vertex_textArea.getText().equals(getVertices.get(i))) {
							check = false;
							JOptionPane.showMessageDialog(null, "This vertex is already exists");
							break;
						}
					}
					if (check) {
						getVertices.add(vertex_textArea.getText());
						vertex_textArea.setText("");
						numberOfVertices++;
					}
				}
			}
		});

		contentPane.add(btnAddAnotherVertex);

		RoundButton btnAddEdges = new RoundButton("Add edge");
		btnAddEdges.setBounds(320, 110, 133, 25);
		btnAddEdges.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		btnAddEdges.setBackground(new Color(165, 198, 255));

		btnAddEdges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = false;
				String temp = edge_textArea.getText();
				String[] inp = temp.split(" ");
				if (getVertices.size() == 0) {
					JOptionPane.showMessageDialog(null, "Please Enter vertices first");
					edge_textArea.setText("");
					check = true;
				} else if (inp.length != 3) {
					JOptionPane.showMessageDialog(null, "wrong edge input");
					edge_textArea.setText("");
					check = true;
				} else if (getVertices.size() > 0) {

					for (int i = 0; i < inp.length - 1; i++) {
						if (!getVertices.contains(inp[i])) {
							JOptionPane.showMessageDialog(null, "this vertex: " + inp[i] + " doesn't exist");
							check = true;
							break;
						}
					}
				}
				if (!check) {
					getEdges.add(edge_textArea.getText());
					edge_textArea.setText("");
				}

			}
		});
		contentPane.add(btnAddEdges);

	
		RoundButton clear = new RoundButton("Clear");
		clear.setBounds(120, 345, 80, 40);
		clear.setBackground(new Color(165, 198, 255));

		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getEdges.clear();
				getVertices.clear();

				maxFlowBox.setSelected(false);
				dijkestraBox.setSelected(false);
				Directed.setSelected(false);
				source_textArea.setText("");
				destination_textArea.setText("");
				GUISteps.steps = "";
				GUISteps.maxflowvalue = 0;
				numberOfVertices = 0;
				startPoint = "";
				endPoint = "";
				flag = false;
				counter = 0;
				counter2 = 0;
				if (gp != null) {
					gp.colseFrame();
				}
				if (frame_output != null) {
					frame_output.clear();
				}
			}
		});
		contentPane.add(clear);

		RoundButton btnNewButton = new RoundButton("Run");
		btnNewButton.setBounds(325, 345, 80, 40);
		btnNewButton.setBackground(new Color(165, 198, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println(getVertices);
				if (counter > 0)
					gp.colseFrame();
				if (counter2 > 0)
					frame_output.clear();

				flag = false;
				GUISteps.maxflowvalue = 0;
				GUISteps.steps = "";

				startPoint = source_textArea.getText();
				endPoint = destination_textArea.getText();

				if (getVertices.size() == 0) {
					JOptionPane.showMessageDialog(null, "Please enter graph data");
				} else if (startPoint.equals("") || endPoint.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter source and destination vertices");
				} else {
					input = new Graph(numberOfVertices, Directed.isSelected(), getVertices);
					maxFlowCheck = maxFlowBox.isSelected();
					dijkestraCheck = dijkestraBox.isSelected();

					numberOfVertices = getVertices.size();
					GUISteps.steps = "Number of vertices is: " + numberOfVertices + "\n Vertices: ";
					for (int i = 0; i < getVertices.size(); i++) {
						GUISteps.steps += getVertices.get(i) + " ";
					}
					GUISteps.steps += "\n Edges: ";

					for (int i = 0; i < getEdges.size(); i++) {
						GUISteps.steps += getEdges.get(i) + " ";
					}
					// GUISteps.steps += "\nThe Output will be....\n";

					Algorithms algorithm = null;
					if (maxFlowCheck && !dijkestraCheck) {
						algorithm = new MaximumFlow();

					} else if (!maxFlowCheck && dijkestraCheck) {
						algorithm = new Dijkstra();
					} else {
						JOptionPane.showMessageDialog(null, "Sorry, you must select at least one option");
						flag = true;
					}
					for (int i = 0; i < getEdges.size(); i++) {
						String[] split = null;
						split = getEdges.get(i).split(" ");
						input.addEdge(split[0], split[1], Integer.parseInt(split[2]));
					}
					if (!flag) {
						counter++;
						gp = new GraphPanel(input, "Original Graph", 500, 600, Directed.isSelected(), false);

						if (!dijkestraCheck) {
							counter2++;
							List<Graph> OutPut = algorithm.run(input, startPoint, endPoint);
							frame_output = new Output(OutPut, maxFlowCheck, Directed.isSelected(), false);
							frame_output.setTitle("output");
							frame_output.setLocation(500, 100);
							frame_output.setVisible(true);
						} else {
							List<Graph> OutPut = algorithm.run(input, startPoint, endPoint);
							if (OutPut.size() == 0) {
								GUISteps.steps = Dijkstra.ResultSteps;

							} else {
								counter2++;
								frame_output = new Output(OutPut, maxFlowCheck, Directed.isSelected(), true);
								frame_output.setTitle("output");
								frame_output.setLocation(500, 100);
								frame_output.setVisible(true);
							}
						}

						GUISteps.steps = "";
					}

				}

			}
		});
		contentPane.add(btnNewButton);

	}
}