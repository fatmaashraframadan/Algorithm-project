/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Output;

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
import org.apache.commons.collections15.functors.ConstantTransformer;

import java.awt.*;
import javax.swing.JFrame;

/**
 * @author fatma
 */
@SuppressWarnings("unchecked")


public class GraphPanel extends Container {
    static final long serialVersionUID = 420001L;
    DirectedSparseGraph<Integer, Integer> graph = null;
    VisualizationViewer<Integer, Integer> vv = null;
    PickedState<Number> pickedState = null;
    SparseMultigraph<Integer, String> euler = new SparseMultigraph();
    JFrame frame;

    public GraphPanel(Graph graph1, String name, int x, int y, boolean isDirected, boolean isDijkstra) {
        construct_graph(graph1, graph1.representation, graph1.representationcost, name, x, y, isDirected, isDijkstra);
    }

    public void attach_to_frame(JFrame frame) {
        frame.setContentPane(vv);
    }

    public void construct_graph(Graph input, Integer[][] nodes_list, Integer[][] costReprestentation, String name,
                                int x2, int y, boolean isDirected, boolean isDijkstra) {

        int x = 1;
        System.out.println("isDirected : " + isDirected);

        SparseMultigraph<String, String> graph = new SparseMultigraph<String, String>();


        if (isDijkstra) {
            int result = 0;
            for (int i = 0; i < nodes_list.length; i++) {
                graph.addVertex(input.getVertexById(i));
                if (isDirected) {
                    for (int j = 0; j < nodes_list[i].length; j++) {
                        if (nodes_list[i][j] >= 1) {
                            String s = "( " + input.getVertexById(j) + " ) " + ": \n" + result + " + " + costReprestentation[i][j] + " = " + (result + costReprestentation[i][j]);
                            graph.addEdge(s, input.getVertexById(i), input.getVertexById(j), EdgeType.DIRECTED);
                            result += costReprestentation[i][j];
                        }
                    }
                } else {
                    for (int j = 0; j < i + 1; j++) {
                        if (nodes_list[i][j] >= 1) {
                            //   System.out.println("Here :  " +input.getVertexById(i) + input.getVertexById(j));
                            String s = "( " + input.getVertexById(j) + " ) " + ": \n" + result + " + " + costReprestentation[i][j] + " = " + (result + costReprestentation[i][j]);
                            graph.addEdge(s, input.getVertexById(i), input.getVertexById(j), EdgeType.UNDIRECTED);
                            result += costReprestentation[i][j];
                        }
                    }
                }

            }
        } else {
            for (int i = 0; i < nodes_list.length; i++) {
                graph.addVertex(input.getVertexById(i));
                if (isDirected) {
                    for (int j = 0; j < nodes_list[i].length; j++) {
                        if (nodes_list[i][j] >= 1) {
                            String s = x + " : " + "( " + costReprestentation[i][j] + " )";
                            graph.addEdge(s, input.getVertexById(i), input.getVertexById(j), EdgeType.DIRECTED);
                            x++;
                        }
                    }
                } else {
                    for (int j = 0; j < i + 1; j++) {
                        if (nodes_list[i][j] >= 1) {
                            String s = x + " : " + "( " + costReprestentation[i][j] + " )";
                            graph.addEdge(s, input.getVertexById(i), input.getVertexById(j), EdgeType.UNDIRECTED);
                            x++;
                        }
                    }
                }

            }
        }


        Layout<Integer, String> layout = new CircleLayout(graph);
        layout.setSize(new Dimension(450, 450)); // sets the initial size of the space
        // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(layout);

        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeFontTransformer(new ConstantTransformer(new Font("Comic Sans MS", Font.BOLD | Font.CENTER_BASELINE | Font.TYPE1_FONT, 10)));

        //vv.setFont(new Font("SANS_SERIF",Font.BOLD,28));
        frame = new JFrame(name);
        if (name.equals("Original Graph")) {

            frame.setLocation(750, 30);
        } else
            frame.setLocation(x2, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();

        frame.setVisible(true);

    }

    public void colseFrame() {
        frame.dispose();
    }
}
