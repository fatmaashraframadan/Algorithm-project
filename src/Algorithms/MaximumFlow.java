/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Graph.Edge;
import Graph.Graph;
import Output.GUISteps;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fatma
 */
@SuppressWarnings("unchecked")

public class MaximumFlow implements Algorithms {

	static int x2 = 10, y2 = 0;
	List<Edge> closededge = new ArrayList<Edge>();
	Graph Finalresult;

	public List<Edge> getPath(Graph graph, int startNode, int endnode) {

		int currentNode = startNode;
		List<Edge> originpath = new ArrayList<Edge>();
		originpath.addAll(graph.edges);
		List<Edge> path = new ArrayList<Edge>();
		List<Integer> visited = new ArrayList<Integer>();
		visited.add(startNode);
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
				visited.clear();
				visited.add(startNode);
				i = 0;
				continue;
			}
			if (currentNode == endnode) {
				break;
			}
			
			if (originpath.get(i).first == currentNode && !visited.contains(originpath.get(i).second)) {
				path.add(originpath.get(i));
				visited.add(originpath.get(i).second);
				currentNode = originpath.get(i).second;
				i = -1;
			}
			i++;
		}
		if (path.size() != 0) {
			GUISteps.steps += "the path is ";

			for (int j = 0; j < path.size(); j++) {
				// path.get(j).displayedge();
				GUISteps.steps += graph.getVertexById(path.get(j).first) + " ";
			}
			GUISteps.steps += graph.getVertexById(path.get(path.size() - 1).second) + " ";
			GUISteps.steps += "\n";
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
		GUISteps.maxflowvalue += min;
		GUISteps.steps += "the minimum value is :" + min + "\n";
		for (int i = 0; i < Path.size(); i++) {
			if (Path.get(i).cost - min == 0) {
				closededge.add(new Edge(Path.get(i).first, Path.get(i).second, 0));

			} else {
				Edge e = new Edge(Path.get(i).first, Path.get(i).second, Path.get(i).cost - min);
				newPath.add(e);
				Edge e1 = new Edge(Path.get(i).first, Path.get(i).second,  Path.get(i).cost - min);
				closededge.add(e1);
			}

		}
		return newPath;
	}

	public Graph updategraph(List<Edge> Path, List<Edge> originPath, Graph graph, boolean isDirectGraph) {
		List<String> v = new ArrayList<String>();

		for (int i = 0; i < graph.vertices.size(); i++) {
			v.add(graph.vertices.get(i).name);
		}
		Graph newgraph = new Graph(graph.numOfVertices, true, v);


		for (int i = 0; i < graph.edges.size(); i++) {


			if (!isDirectGraph) {
				boolean flag=true;
				for (int j = 0; j <originPath.size(); j++) {
					if(graph.edges.get(i).first==originPath.get(j).second&&graph.edges.get(i).second==originPath.get(j).first)
					{
						flag=false;
						break;
					}
				}
				if (!originPath.contains(graph.edges.get(i)) && !closededge.contains(graph.edges.get(i))&&flag) {
					newgraph.addEdge(graph.edges.get(i).first, graph.edges.get(i).second, graph.edges.get(i).cost);
				}
			}
			else
			{
				if (!originPath.contains(graph.edges.get(i)) && !closededge.contains(graph.edges.get(i))) {
					newgraph.addEdge(graph.edges.get(i).first, graph.edges.get(i).second, graph.edges.get(i).cost);
				}
			}

		}

		for (int i = 0; i < Path.size(); i++) {

			newgraph.addEdge(Path.get(i).first, Path.get(i).second, Path.get(i).cost);
			if (!isDirectGraph) {
				newgraph.addEdge(Path.get(i).second, Path.get(i).first, Path.get(i).cost);
			}
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
		// copyGraph.displayeadgs();
		// System.out.println();
		int startNode = input.getVertexId(v1);
		int endnode = input.getVertexId(v2);
		//copyGraph.displayeadgs();
		while (true) {
			copyGraph.displayeadgs();
			List<Edge> newpath = getPath(copyGraph, startNode, endnode);
			System.out.println();
			if (newpath.size() == 0) {
				break;
			}

			List<Edge> temppath = updatePath(newpath);
			copyGraph = updategraph(temppath, newpath, copyGraph, input.isDirectGraph);

		}
		Graph res = new Graph(input.numOfVertices, input.isDirectGraph, v);
		for (int i = 0; i < closededge.size(); i++) {
			res.addEdge(closededge.get(i).first, closededge.get(i).second, closededge.get(i).cost);
			Graph g = new Graph(input.numOfVertices,  input.isDirectGraph, v);
			g.copyGraph(res);
			Listofgraphs.add(g);
			// GraphPanel ob = new GraphPanel(copyGraph, "Step "+(i+1), x2, y2);
			// x2 += 20;y2+=5;
		}
		GUISteps.steps += "\nthe maximum flow value is:" + GUISteps.maxflowvalue + "\n";
		return Listofgraphs;
	}

}