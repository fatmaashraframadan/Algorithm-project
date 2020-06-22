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

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * @author fatma
 */
@SuppressWarnings("unchecked")
/*
*
* // s a 10 s b 10 a c 4 c t 10 a d 8 d t 10 d c 6 b d 9 a b 2
       //s c 10 c b 5 b t 12 a t 4 c a 6 a b 5 s a 5
* */

public class GUI extends JFrame {

    public GUI() {

    }

    private JPanel contentPane;
    int numberOfVertices = 0;
    boolean maxFlowCheck = false, dijkestraCheck = false, flag = false;
    public ArrayList<String> getEdges = new ArrayList<String>();
    public ArrayList<String> getVertices = new ArrayList<String>();
    String startPoint, endPoint;
    Graph input;
    JCheckBox unDirected = new JCheckBox("unDirected");
    GraphPanel gp;
    Output frame_output;

    /**
     * Create the frame.
     */

    public void initialize() throws FontFormatException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 10, 400, 570);

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle("Graph Algorithms");
        setResizable(true);
        setVisible(true);
        contentPane.setLayout(null);

        JTextArea textArea_1 = new JTextArea();
        textArea_1.setBounds(25, 90, 133, 25);
        contentPane.add(textArea_1);


        JLabel lblNewLabel_1 = new JLabel("Insert a Vertex");
        lblNewLabel_1.setBounds(25, 75, 125, 13);
        contentPane.add(lblNewLabel_1);

        JTextArea textArea_2 = new JTextArea();
        textArea_2.setBounds(25, 160, 135, 25);
        contentPane.add(textArea_2);

        JLabel lblNewLabel_2 = new JLabel("Insert an Edge");
        lblNewLabel_2.setBounds(25, 145, 125, 13);
        contentPane.add(lblNewLabel_2);

        JCheckBox maxFlowBox = new JCheckBox("Maximum Flow");
        maxFlowBox.setBounds(10, 40, 154, 21);
        contentPane.add(maxFlowBox);

        JCheckBox dijkestraBox = new JCheckBox("Dijkestra");
        dijkestraBox.setBounds(160, 40, 105, 21);
        contentPane.add(dijkestraBox);

        unDirected.setBounds(270, 40, 105, 21);
        contentPane.add(unDirected);


        JLabel lblNewLabel_32 = new JLabel("Source Vertex");
        lblNewLabel_32.setBounds(200, 75, 125, 13);
        contentPane.add(lblNewLabel_32);

        JTextArea textArea_3 = new JTextArea();
        textArea_3.setBounds(200, 90, 133, 25);
        contentPane.add(textArea_3);

        JLabel lblNewLabel_4 = new JLabel("Destination Vertex");
        lblNewLabel_4.setBounds(200, 145, 125, 13);
        contentPane.add(lblNewLabel_4);

        JTextArea textArea_4 = new JTextArea();
        textArea_4.setBounds(200, 160, 133, 25);
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
                boolean check = false;
                String temp = textArea_2.getText();
                String[] inp = temp.split(" ");
                if (getVertices.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Please Enter vertices first");
                    textArea_2.setText("");
                    check = true;
                } else if (inp.length != 3) {
                    JOptionPane.showMessageDialog(null, "wrong edge input");
                    textArea_2.setText("");
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
                    getEdges.add(textArea_2.getText());
                    textArea_2.setText("");
                }

            }
        });
        contentPane.add(btnAddEdges);


        JButton btnAddAnotherVertex = new JButton("Add Vertex");
        btnAddAnotherVertex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String temp = textArea_1.getText();
                String[] inp = temp.split(" ");
                if (inp.length != 1 || textArea_1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "wrong vertex input");
                } else {
                    boolean check = true;
                    for (int i = 0; i < getVertices.size(); i++) {
                        if (textArea_1.getText().equals(getVertices.get(i))) {
                            check = false;
                            JOptionPane.showMessageDialog(null, "This vertex is already exists");
                            break;
                        }
                    }
                    if (check) {
                        getVertices.add(textArea_1.getText());
                        textArea_1.setText("");
                        numberOfVertices++;
                    }
                }
            }
        });

        btnAddAnotherVertex.setBounds(25, 115, 133, 21);
        contentPane.add(btnAddAnotherVertex);


        JButton clear = new JButton("Clear");
        clear.setBounds(100, 460, 80, 40);

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getEdges.clear();
                getVertices.clear();

                maxFlowBox.setSelected(false);
                dijkestraBox.setSelected(false);
                unDirected.setSelected(false);
                textArea_3.setText("");
                textArea_4.setText("");
                textArea_5.setText("");
                GUISteps.steps = "";
                GUISteps.maxflowvalue = 0;
                numberOfVertices = 0;
                startPoint = "";
                endPoint = "";
                flag = false;
                gp.colseFrame();

                System.out.println("Here");
                frame_output.clear();
            }
        });
        contentPane.add(clear);

        JButton btnNewButton = new JButton("Run");
        btnNewButton.setBounds(250, 460, 80, 40);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // System.out.println(getVertices);
                flag = false;

                startPoint = textArea_3.getText();
                endPoint = textArea_4.getText();

                if (getVertices.size() == 0  ) {
                    JOptionPane.showMessageDialog(null, "Please enter graph data");
                }
                else if(startPoint.equals("") || endPoint.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter source and destination vertices");
                }
                else {
                    input = new Graph(numberOfVertices, !unDirected.isSelected(), getVertices);
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
                    GUISteps.steps += "\nThe Output will be....\n";

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
                        System.out.println("here" + getEdges.get(i));
                        String[] split = null;
                        split = getEdges.get(i).split(" ");
                        System.out.println(split[0] + "   " + split[1] + "  " + Integer.parseInt(split[2]));
                        input.addEdge(split[0], split[1], Integer.parseInt(split[2]));
                    }
                    if (!flag) {

                        gp = new GraphPanel(input, "input", 500, 600, !unDirected.isSelected(),false);

                        if(!dijkestraCheck)
                        {
                            List<Graph> OutPut = algorithm.run(input, startPoint, endPoint);
                            frame_output = new Output(OutPut, maxFlowCheck, !unDirected.isSelected(),false);
                            frame_output.setLocation(500, 100);
                            frame_output.setVisible(true);
                        }
                        else{
                            List<Graph> OutPut = algorithm.run(input, startPoint, endPoint);
                            frame_output = new Output(OutPut, maxFlowCheck, !unDirected.isSelected(),true);
                            frame_output.setLocation(500, 100);
                            frame_output.setVisible(true);
                        }


                        textArea_5.setText(GUISteps.steps);
                        GUISteps.steps = "";
                    }

                }


            }
        });
        contentPane.add(btnNewButton);

    }
}
