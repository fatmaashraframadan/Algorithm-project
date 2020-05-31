package Output;

import java.util.ArrayList;
import java.util.List;

import Algorithms.*;
import Graph.*;

public class Main {

    public static void main(String[] args) {
        List<String> v = new ArrayList<String>();
        v.add("s");
        v.add("a");
        v.add("b");
        v.add("c");
        v.add("d");
        v.add("h");
        v.add("f");
        v.add("g");
        v.add("t");
        Graph g = new Graph(9, true, v);
        g.addEdge("s", "a", 4);
        g.addEdge("s", "g", 8);

        g.addEdge("g", "t", 7);
        g.addEdge("g", "f", 1);
        g.addEdge("g", "a", 11);
        g.addEdge("a", "b", 8);

        g.addEdge("b", "t", 2);

        g.addEdge("b", "c", 7);

        g.addEdge("f", "t", 6);
        g.addEdge("h", "f", 2);


        g.addEdge("b", "h", 4);
        g.addEdge("h", "c", 14);
        g.addEdge("d", "c", 9);
        g.addEdge("h", "d", 10);

		/*MaximumFlow maximumFlow=new MaximumFlow();
		List<Edge>e= maximumFlow.getPath(g, 0, 5);
		for (int i = 0; i < e.size(); i++) {
			e.get(i).displayedge(); 
		}
		System.out.println();
		List<Edge>e1=maximumFlow.updatePath(e);
		for (int i = 0; i < e1.size(); i++) {
			e1.get(i).displayedge(); 
		}
		System.out.println();
		g.displayeadgs();
		System.out.println();
		Graph res=maximumFlow.updategraph(e1,e, g);
		res.displayeadgs();
		*/
//		Algorithms algo=new MaximumFlow();
//		Graph res=algo.run(g, "s", "t");
//		res.displayeadgs();
        Algorithms algo = new Dijkstra();
        Graph res = algo.run(g, "s", "h");
//        System.out.println(res.numOfVertices);
//        for (int i = 0; i < res.numOfVertices; i++) {
//            System.out.println(res.vertices.get(i).id+"   "+res.vertices.get(i).name+" ");
//        }
		//res.displayeadgs();
    }

}
