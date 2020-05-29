package Output;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

import Algorithms.Algorithms;
import Algorithms.Dijkstra;
import Algorithms.MaximumFlow;
import Graph.*;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class GUI extends JFrame {
	
	public GUI() {
		getContentPane().setLayout(new BorderLayout(0, 0));
	}

	private JPanel contentPane;
	private JFrame frame;
	int numberOfVertices = 0;
	boolean maxFlowCheck = false;
	boolean dijkestraCheck = false;
	ArrayList<String> getEdges = new ArrayList<String>();
	ArrayList<String> getVertices = new ArrayList<String>();
	String startPoint, endPoint;

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
	
	private void drawRepresentation(Integer[][] representation, Integer[][] costReprestentation, String name) {

        construct_graph(representation, costReprestentation, name);
    }

    private void construct_graph(Integer[][] nodes_list, Integer[][] costReprestentation, String name) {

        int x = 1;

        DirectedSparseGraph<Integer, String> graph = new DirectedSparseGraph<Integer, String>();
        for (int i = 0; i < nodes_list.length; i++) {
            graph.addVertex(i);
            for (int j = 0; j < nodes_list.length; j++) {

                // System.out.println("\nCost" + costReprestentation[i][j] );
                if (nodes_list[i][j] == 1) {
                    // System.out.println("Cost"+i + " " +j + costReprestentation[i][j] );
                    String s = "COST " + x + ":" + "(" + costReprestentation[i][j] + ")";
                    graph.addEdge(s, i, j);
                    x++;
                }
            }
        }
        
        Layout<Integer, String> layout = new CircleLayout(graph);
        layout.setSize(new Dimension(400, 400)); // sets the initial size of the space
        // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Integer, String> vv
                = new BasicVisualizationServer<Integer, String>(layout);
        vv.setPreferredSize(new Dimension(640, 480)); //Sets the viewing area size
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
    

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 400, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("");
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
		maxFlowBox.setBounds(25, 30, 154, 21);
		contentPane.add(maxFlowBox);

		JCheckBox dijkestraBox = new JCheckBox("Dijkestra");
		dijkestraBox.setBounds(175, 30, 105, 21);
		contentPane.add(dijkestraBox);
		
		JLabel lblNewLabel_3 = new JLabel("Insert The start Point");
		lblNewLabel_3.setBounds(200, 75, 125, 13);
		contentPane.add(lblNewLabel_3);
		
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
		textArea_5.setBounds(25, 250, 315, 100);
		contentPane.add(textArea_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 250, 315, 100);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(textArea_5);
		
		JButton btnAddEdges = new JButton("Add edge");
		btnAddEdges.setBounds(25, 185, 135, 21);
		btnAddEdges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getEdges.add(textArea_2.getText());
				textArea_2.setText("");
			}
		});
		contentPane.add(btnAddEdges);

		
		JButton btnAddAnotherVertex = new JButton("Add Vertex");
		btnAddAnotherVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getVertices.add(textArea_1.getText());
				textArea_1.setText("");
			}
		});
		btnAddAnotherVertex.setBounds(25, 115, 133, 21);
		contentPane.add(btnAddAnotherVertex);

		JButton btnNewButton = new JButton("Run");
		btnNewButton.setBounds(150, 370, 60, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   
				maxFlowCheck = maxFlowBox.isSelected();
				dijkestraCheck = dijkestraBox.isSelected();
				
				startPoint = textArea_3.getText();
				endPoint = textArea_4.getText();
				
				numberOfVertices = getVertices.size();
				GUISteps.steps = "Number of vertices is: "+numberOfVertices+"\n Vertices: ";
				for (int i = 0; i < getVertices.size(); i++) {
					GUISteps.steps += getVertices.get(i) + " ";
				}
				GUISteps.steps +="\n Edges: ";
				
				for (int i = 0; i < getEdges.size(); i++) {
					GUISteps.steps += getEdges.get(i) + " ";
				}
				GUISteps.steps +="\nThe Output will be....\n";
				Graph input = new Graph(numberOfVertices, true, getVertices);
				
				Algorithms algorithm = null;
				if (maxFlowCheck && !dijkestraCheck) {
					algorithm = new MaximumFlow();
						
				}else if (!maxFlowCheck && dijkestraCheck) {				
							algorithm = new Dijkstra();
				}else {
					JOptionPane.showMessageDialog(null, "Sorry, you must select at least one option");
				}
				
				for (int i = 0; i < getEdges.size(); i++) {
					String[] split = null;
					split = getEdges.get(i).split(" ");
					input.addEdge(split[0], split[1], Integer.parseInt(split[2]));	
				}
				Graph output = algorithm.run(input, startPoint, endPoint);
				drawRepresentation(output.representation, output.representationcost, "Output");
				
				textArea_5.setText(GUISteps.steps);
				GUISteps.steps = "";
			}
		});
		contentPane.add(btnNewButton);

	}
}
