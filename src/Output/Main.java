package Output;

import java.util.ArrayList;
import java.util.List;

import Algorithms.*;
import Graph.*;

public class Main {

    public static void main(String[] args) {
        List<String> v = new ArrayList<String>();
   //test case 1
       	v.add("s");
        v.add("a");
        v.add("b");
        v.add("c");
        v.add("d");
        v.add("t");
/*   test case 2
       	v.add("s");
        v.add("a");
        v.add("b");
        v.add("c");
        v.add("t");
*/

        Graph g = new Graph(6, false, v);
        //test case 1
        g.addEdge("s", "a", 10);
        g.addEdge("s", "b", 10);
        g.addEdge("a", "c", 4);
        g.addEdge("c", "t", 10);
        g.addEdge("a", "d", 8);
        g.addEdge("d", "t", 10);
        g.addEdge("d", "c", 6);
        g.addEdge("b", "d", 9);
        g.addEdge("a", "b", 2);
        /*  //test case 2   
        g.addEdge("s", "c", 10);
        g.addEdge("c", "b", 5);
        g.addEdge("b", "t", 12);
        g.addEdge("a", "t", 4);
        g.addEdge("c", "a", 6);
        g.addEdge("a", "b", 5);
        g.addEdge("s", "a", 5);
        */
        Algorithms algo = new MaximumFlow();
        List<Graph> listofg = algo.run(g, "s", "t");
        System.out.println(GUISteps.steps);
        for (int i = 0; i < listofg.size(); i++) {
			listofg.get(i).displayeadgs();
			System.out.println();
		}
    }

}
