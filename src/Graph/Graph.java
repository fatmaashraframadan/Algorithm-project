package Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private boolean isDirectGraph;
	int numOfVertices;
	int[][] representation;
	List<Vertex> vertices;
	List<Edge> edges;

	Graph(int numOfnumOfVertices, boolean isDirectGraph, List<String> V) {
		vertices = new ArrayList<Vertex>();
		for (int i = 0; i < numOfnumOfVertices; i++) {
			Vertex v = new Vertex(i, V.get(i));
			vertices.add(v);
		}
		edges = new ArrayList<Edge>();
		this.numOfVertices = numOfnumOfVertices;
		this.isDirectGraph = isDirectGraph;
		representation = new int[numOfnumOfVertices][numOfnumOfVertices];
		for (int i = 0; i < numOfnumOfVertices; i++) {
			for (int j = 0; j < numOfnumOfVertices; j++) {
				representation[i][j] = 0;
			}
		}
	}

	int getVertexId(String V) {
		for (int i = 0; i < numOfVertices; i++) {
			if (vertices.get(i).name.equals(V)) {
				return vertices.get(i).id;
			}
		}
		return -1;
	}

	void addEdge(int vId1, int vId2, int cost) {
		representation[vId1][vId2]++;
		edges.add(new Edge(vId1, vId2, cost));
		if (!isDirectGraph) {
			representation[vId2][vId1]++;
			edges.add(new Edge(vId2, vId1, cost));
		}
	}

	void displayRepresentation() {
		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				System.out.print(representation[i][j] + " ");
			}
			System.out.println();
		}
	}

	void displayeadgs() {
		for (int i = 0; i < edges.size(); i++) {
			edges.get(i).displayedge();
		}
	}

	int getEdgeCost(int x, int y) {

		for (int j = 0; j < edges.size(); j++) {
			if (x == edges.get(j).first && y == edges.get(j).second) {
				return edges.get(j).cost;
			}
		}
		return 0;
	}

	void reset() {
		this.edges.clear();
		for (int i = 0; i < this.numOfVertices; i++) {
			for (int j = 0; j < this.numOfVertices; j++) {
				representation[i][j] = 0;
			}
		}
	}

	void copyGraph(Graph g) {
		this.edges.addAll(g.edges);
		this.numOfVertices = g.numOfVertices;
		this.representation = new int[g.numOfVertices][g.numOfVertices];
		for (int i = 0; i < this.numOfVertices; i++) {
			for (int j = 0; j < this.numOfVertices; j++) {
				this.representation[i][j] = g.representation[i][j];
			}
		}
	}
}
