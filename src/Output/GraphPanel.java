package Output;

import java.awt.*;
import javax.swing.JFrame;

import Graph.Graph;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;

public class GraphPanel extends Container {
    static final long serialVersionUID = 420001L;
    DirectedSparseGraph<Integer, Integer> graph = null;
    VisualizationViewer<Integer, Integer> vv = null;
    PickedState<Number> pickedState = null;
    SparseMultigraph<Integer, String> euler = new SparseMultigraph();

    public GraphPanel(Graph graph1, String name, int x, int y) {

        construct_graph(graph1, graph1.representation, graph1.representationcost, name, x, y);
    }

    private void draw_Undirected(List<Integer> adjacencyList[] , String Frame_Name) {
        SparseMultigraph<Integer, String> euler = new SparseMultigraph();
        int c = 1;
        for (int i = 0; i < adjacencyList.length; i++) {
            if (adjacencyList[i].size() > 0) {
                euler.addVertex(i);
                // euler.degree(i);
                for (int j = 0; j < adjacencyList[i].size(); j++) {
                    euler.addEdge(Integer.toString(c++), i, adjacencyList[i].get(j));
                }
            }
        }

        Layout<Integer, String> layout = new CircleLayout(euler);
        layout.setSize(new Dimension(300, 300)); // sets the initial size of the space
        BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(layout);
        vv.setPreferredSize(new Dimension(350, 350)); // Sets the viewing area size
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame(Frame_Name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }

    public void attach_to_frame(JFrame frame) {
        frame.setContentPane(vv);
    }

    public void construct_graph(Graph input, Integer[][] nodes_list, Integer[][] costReprestentation, String name, int x2, int y) {

        int x = 1;

        SparseMultigraph<String, String> graph = new SparseMultigraph<String, String>();
        for (int i = 0; i < nodes_list.length; i++) {
            graph.addVertex(input.getVertexById(i));
            for (int j = 0; j < nodes_list.length; j++) {
                if (nodes_list[i][j] == 1) {
                    String s = "COST " + x + ":" + "(" + costReprestentation[i][j] + ")";
                    graph.addEdge(s, input.getVertexById(i), input.getVertexById(j), EdgeType.DIRECTED);
                    x++;
                }
            }
        }

        Layout<Integer, String> layout = new CircleLayout(graph);
        layout.setSize(new Dimension(400, 400)); // sets the initial size of the space
        // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(layout);
//        if (name.equals("Input")) {
//            x+=10;
//            vv.setPreferredSize(new Dimension(x, 700)); //Sets the viewing area size
//        } else {
//x+=10;
//            vv.setPreferredSize(new Dimension(x, 480)); //Sets the viewing area size
//            System.out.println("This : " +(x));
//        }
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame(name);
        if (name.equals("input")) {

            frame.setLocation(750, 30);
       //     frame.setSize(900, 900);


        } else
            frame.setLocation(x2, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();

        frame.setVisible(true);

    }

}
