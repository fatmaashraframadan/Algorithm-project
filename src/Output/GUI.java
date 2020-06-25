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

    public GUI() {}

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
        setBounds(400, 10, 550, 630);


        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle("Graph Algorithms");
        setResizable(true);
        setVisible(true);
        contentPane.setLayout(null);

        JTextField textArea_1 = new JTextField();
        textArea_1.setBounds(25, 40, 180, 25);
        contentPane.add(textArea_1);


        JLabel lblNewLabel_1 = new JLabel("Insert a Vertex");
        lblNewLabel_1.setBounds(25, 20, 150, 13);
        lblNewLabel_1.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        contentPane.add(lblNewLabel_1);

        JTextField textArea_2 = new JTextField();
        textArea_2.setBounds(25, 130, 180, 25);
        contentPane.add(textArea_2);

        JLabel lblNewLabel_2 = new JLabel("Insert an Edge");
        lblNewLabel_2.setBounds(25, 110, 150, 13);
        lblNewLabel_2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        contentPane.add(lblNewLabel_2);

        JCheckBox maxFlowBox = new JCheckBox("Maximum Flow");
        maxFlowBox.setBounds(15, 490, 154, 21);
        maxFlowBox.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        contentPane.add(maxFlowBox);

        JCheckBox dijkestraBox = new JCheckBox("Dijkestra");
        dijkestraBox.setBounds(225, 490, 105, 21);
        dijkestraBox.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        contentPane.add(dijkestraBox);

        JCheckBox unDirected = new JCheckBox("unDirected");
        unDirected.setBounds(425, 490, 105, 21);
        unDirected.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        contentPane.add(unDirected);


        JLabel lblNewLabel_32 = new JLabel("Source Vertex");
        lblNewLabel_32.setBounds(280, 20, 150, 13);
        lblNewLabel_32.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        contentPane.add(lblNewLabel_32);


        JTextField textArea_3 = new JTextField();
        textArea_3.setBounds(280, 40, 180, 25);
        contentPane.add(textArea_3);

        JLabel lblNewLabel_4 = new JLabel("Destination Vertex");
        lblNewLabel_4.setBounds(280, 110, 150, 13);
        lblNewLabel_4.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        contentPane.add(lblNewLabel_4);

        JTextField textArea_4 = new JTextField();
        textArea_4.setBounds(280, 130, 180, 25);
        contentPane.add(textArea_4);

        JLabel lblNewLabel_5 = new JLabel("Steps...");
        lblNewLabel_5.setBounds(20, 210, 125, 13);
        lblNewLabel_5.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        contentPane.add(lblNewLabel_5);

        JTextArea textArea_5 = new JTextArea();
        textArea_5.setBounds(15, 230, 500, 250);
        textArea_5.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
        textArea_5.setEditable(false);
        contentPane.add(textArea_5);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(15, 230, 500, 250);
        scrollPane.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
        contentPane.add(scrollPane);

        scrollPane.setViewportView(textArea_5);

        RoundButton btnAddEdges = new RoundButton("Add edge");
        btnAddEdges.setBounds(50, 160, 135, 25);
        btnAddEdges.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        btnAddEdges.setBackground(new Color(211,211,211));

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


        RoundButton btnAddAnotherVertex = new RoundButton("Add Vertex");
        btnAddAnotherVertex.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        btnAddAnotherVertex.setBackground(new Color(211,211,211));
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

        btnAddAnotherVertex.setBounds(50, 70, 133, 25);
        contentPane.add(btnAddAnotherVertex);


        RoundButton clear = new RoundButton("Clear");
        clear.setBounds(120, 530, 80, 40);
        clear.setBackground(new Color(211,211,211));


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
                counter = 0;
                counter2 = 0;
                gp.colseFrame();

                frame_output.clear();
            }
        });
        contentPane.add(clear);

        RoundButton btnNewButton = new RoundButton("Run");
        btnNewButton.setBounds(325, 530, 80, 40);
        btnNewButton.setBackground(new Color(211,211,211));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // System.out.println(getVertices);
                if (counter > 0) gp.colseFrame();
                if (counter2 > 0) frame_output.clear();

                flag = false;
                GUISteps.maxflowvalue = 0;
                GUISteps.steps = "";


                startPoint = textArea_3.getText();
                endPoint = textArea_4.getText();

                if (getVertices.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Please enter graph data");
                } else if (startPoint.equals("") || endPoint.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter source and destination vertices");
                } else {
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
                    //GUISteps.steps += "\nThe Output will be....\n";

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
                        gp = new GraphPanel(input, "Original Graph", 500, 600, !unDirected.isSelected(), false);

                        if (!dijkestraCheck) {
                            counter2++;
                            List<Graph> OutPut = algorithm.run(input, startPoint, endPoint);
                            frame_output = new Output(OutPut, maxFlowCheck, !unDirected.isSelected(), false);
                            frame_output.setLocation(500, 100);
                            frame_output.setVisible(true);
                        } else {
                            List<Graph> OutPut = algorithm.run(input, startPoint, endPoint);
                            if (OutPut.size() == 0) {
                                GUISteps.steps = Dijkstra.ResultSteps;

                            } else {
                                counter2++;
                                frame_output = new Output(OutPut, maxFlowCheck, !unDirected.isSelected(), true);
                                frame_output.setLocation(500, 100);
                                frame_output.setVisible(true);
                            }
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