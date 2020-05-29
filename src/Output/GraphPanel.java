package Output;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

import Graph.Graph;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;

public class GraphPanel extends Container
{
    static final long serialVersionUID = 420001L;
    DirectedSparseGraph<Integer, Integer> graph = null;
    VisualizationViewer<Integer, Integer> vv = null;
    PickedState<Number> pickedState = null;
    SparseMultigraph<Integer,String> euler = new SparseMultigraph();

    public GraphPanel(Graph graph1)
    {
        try
        {
            graph = new DirectedSparseGraph<Integer, Integer>();
            construct_graph(graph1);

            vv = new VisualizationViewer<Integer, Integer>
                    (new CircleLayout<Integer, Integer>(graph), new Dimension(400, 400));


            // The following code adds capability for mouse picking of
            // vertices/edges. Vertices can even be moved!
        }
        catch (Exception e)
        {
            System.err.println("Failed to construct graph!\n");
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }
    public void attach_to_frame(JFrame frame)
    {
        frame.setContentPane(vv);
    }


    private void construct_graph(Graph graph1)
    {
        int x=0;
        for (int i = 0; i < graph1.vertices.size(); i++) {
            euler.addVertex(i);
            for (int j = 0; j < graph1.vertices.size(); j++) {
                if(graph1.representation[i][j]==1) {
                    graph.addEdge(x,i, j);
                    x++;
                }
            }
        }
    }
}
