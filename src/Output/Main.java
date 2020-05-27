package Output;

import java.util.ArrayList;
import java.util.List;

import Algorithms.*;
import Graph.*;
public class Main {

	public static void main(String[] args) {
		List<String>v=new ArrayList<String>();
		v.add("s");
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("d");
		v.add("t");
		Graph g =new Graph(6, true, v);
		g.addEdge("s", "a", 5);
		g.addEdge("s", "c", 10);
		g.addEdge("a", "b", 4);
		g.addEdge("c", "a", 6);
		g.addEdge("a", "d", 5);
		g.addEdge("c", "d", 5);
		g.addEdge("b", "d", 6);
		g.addEdge("d", "t", 12);
		g.addEdge("b", "t", 4);
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
		Graph res=maximumFlow.updategraph(e1,e, g);
		res.displayeadgs();*/
		Algorithms algo=new MaximumFlow();
		Graph res=algo.run(g, "s", "t");
		res.displayeadgs();
	}

}
