package Algorithms;

import java.util.ArrayList;
import java.util.List;

import Graph.*;
import Output.GraphPanel;

public class MaximumFlow implements Algorithms {

    static int x2 = 10, y2 = 0;
    List<Edge> closededge = new ArrayList<Edge>();
    Graph Finalresult;

    public List<Edge> getPath(Graph graph, int startNode, int endnode) {

        int currentNode = startNode;
        int previousNode = -1;
        List<Edge> originpath = new ArrayList<Edge>();
        originpath.addAll(graph.edges);
        List<Edge> path = new ArrayList<Edge>();
        int i = 0;
        while (true) {
            if (i == originpath.size()) {
                if (path.size() == 0) {
                    break;
                }
                for (int j = 0; j < path.size(); j++) {
                    originpath.remove(path.get(j));
                }
                path.clear();
                currentNode = startNode;
                previousNode = -1;
                i = 0;
            }
            if (currentNode == endnode) {
                break;
            }
            if (originpath.get(i).first == currentNode && originpath.get(i).second != previousNode) {
                path.add(originpath.get(i));
                previousNode = currentNode;
                currentNode = originpath.get(i).second;

                i = -1;
            }
            i++;
        }

        return path;

    }

    public List<Edge> updatePath(List<Edge> Path) {
        List<Edge> newPath = new ArrayList<Edge>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < Path.size(); i++) {
            if (Path.get(i).cost < min) {
                min = Path.get(i).cost;
            }
        }
        for (int i = 0; i < Path.size(); i++) {
            if (Path.get(i).cost - min == 0) {
                closededge.add(Path.get(i));
            } else {
                Edge e = new Edge(Path.get(i).first, Path.get(i).second, Path.get(i).cost - min);
                newPath.add(e);
                Edge e1 = new Edge(Path.get(i).first, Path.get(i).second, min);
                closededge.add(e1);
            }

        }
        return newPath;
    }

    public Graph updategraph(List<Edge> Path, List<Edge> originPath, Graph graph) {
        List<String> v = new ArrayList<String>();

        for (int i = 0; i < graph.vertices.size(); i++) {
            v.add(graph.vertices.get(i).name);
        }
        Graph newgraph = new Graph(graph.numOfVertices, true, v);

        for (int i = 0; i < graph.edges.size(); i++) {

            if (!originPath.contains(graph.edges.get(i)) && !closededge.contains(graph.edges.get(i))) {
                newgraph.addEdge(graph.edges.get(i).first, graph.edges.get(i).second, graph.edges.get(i).cost);
            }

        }
        for (int i = 0; i < Path.size(); i++) {
            newgraph.addEdge(Path.get(i).first, Path.get(i).second, Path.get(i).cost);
        }
        return newgraph;
    }

    public List<Graph> run(Graph input, String v1, String v2) {
        List<Graph> Listofgraphs = new ArrayList<Graph>();
        List<String> v = new ArrayList<String>();
        for (int i = 0; i < input.vertices.size(); i++) {
            v.add(input.vertices.get(i).name);
        }
        Graph copyGraph = new Graph(input.numOfVertices, true, v);
        copyGraph.copyGraph(input);
        int startNode = input.getVertexId(v1);
        int endnode = input.getVertexId(v2);
        while (true) {
            copyGraph.displayeadgs();
            List<Edge> newpath = getPath(copyGraph, startNode, endnode);
            System.out.println();
            if (newpath.size() == 0) {
                break;
            }
            List<Edge> temppath = updatePath(newpath);
            copyGraph = updategraph(temppath, newpath, copyGraph);

        }
        for (int i = 0; i < closededge.size(); i++) {
            copyGraph.addEdge(closededge.get(i).second, closededge.get(i).first, closededge.get(i).cost);
            Listofgraphs.add(copyGraph);
            //	GraphPanel ob = new GraphPanel(copyGraph, "Step "+(i+1), x2, y2);
            //	x2 += 20;y2+=5;
        }

        return Listofgraphs;
    }

}
