package Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private boolean isDirectGraph;
	public int numOfVertices;
	public Integer[][] representation;
	public Integer [][]representationcost;
	public List<Vertex> vertices;
	public List<Edge> edges;

	public Graph(int numOfVertices, boolean isDirectGraph, List<String> V) {
		vertices = new ArrayList<Vertex>();
		for (int i = 0; i < numOfVertices; i++) {
			Vertex v = new Vertex(i, V.get(i));
			vertices.add(v);
		}
		edges = new ArrayList<Edge>();
		this.numOfVertices = numOfVertices;
		this.isDirectGraph = isDirectGraph;
		representation = new Integer[numOfVertices][numOfVertices];
		representationcost = new Integer[numOfVertices][numOfVertices];
		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				representation[i][j] = 0;
				representationcost[i][j]=0;
			}
		}
	}

	public int getVertexId(String V) {
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).name.equals(V)) {
				return vertices.get(i).id;
			}
		}
		return -1;
	}

	public void addEdge(int vId1, int vId2, int cost) {
		representation[vId1][vId2]++;
		representationcost[vId1][vId2]+= cost;
		edges.add(new Edge(vId1, vId2, cost));
		if (!isDirectGraph) {
			representation[vId2][vId1]++;
			representationcost[vId2][vId1]+= cost;
			edges.add(new Edge(vId2, vId1, cost));
		}
	}
	public void addEdge(String vId1, String vId2, int cost) {
		int x=this.getVertexId(vId1);
		int y=this.getVertexId(vId2);
		representation[x][y]++;
		representationcost[x][y]+= cost;
		edges.add(new Edge(x, y, cost));
		if (!isDirectGraph) {
			representation[y][x]++;
			representationcost[x][y]+= cost;
			edges.add(new Edge(y, x, cost));
		}
	}

	public void displayRepresentation() {
		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				System.out.print(representation[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void displayeadgs() {
		for (int i = 0; i < edges.size(); i++) {
			edges.get(i).displayedge();
		}
	}

	public int getEdgeCost(int x, int y) {

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
				representationcost[i][j]=0;
			}
		}
	}

	public String getVertexById(int ID) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).id == ID) {
                return vertices.get(i).name;
            }
        }
        return "";
    }
	
	public void copyGraph(Graph g) {
		this.vertices.clear();
		this.edges.clear();
		this.edges.addAll(g.edges);
		this.vertices.addAll(g.vertices);
		this.numOfVertices = g.numOfVertices;
		this.representation = new Integer[g.numOfVertices][g.numOfVertices];
		for (int i = 0; i < this.numOfVertices; i++) {
			for (int j = 0; j < this.numOfVertices; j++) {
				this.representation[i][j] = g.representation[i][j];
				this.representationcost[i][j] = g.representationcost[i][j];
			}
		}
	}
}
