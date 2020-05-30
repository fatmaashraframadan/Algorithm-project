package Algorithms;

import Graph.Graph;
import Graph.Vertex;
import Graph.Edge;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import javax.swing.*;
import java.beans.IntrospectionException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Dijkstra implements Algorithms {

    String ResultSteps="";


    //Return min Vertex ID.
    int minDistance(Graph input, int Distances[], boolean Not_In_MST[], int des) {
        int mini = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < input.numOfVertices; v++) {
            //	System.out.println("Distances : " +Distances[v]);
            if (!Not_In_MST[v] && (Distances[v] < mini)) {
                mini = Distances[v];
                min_index = v;
            }
        }
        return min_index;
    }


    public Graph run(Graph input, String v1, String v2) {
        ArrayList<String> Path = new ArrayList<String>();//Vertices Names.

        int Distances[] = new int[input.numOfVertices];
        boolean Not_In_MST[] = new boolean[input.numOfVertices];
        int Parent[] = new int[100];

        for (int i = 0; i < input.numOfVertices; i++) {
            Distances[i] = Integer.MAX_VALUE;
            Not_In_MST[i] = false;
        }

        //source distance is 0.
        Distances[input.getVertexId(v1)] = 0;
        Parent[input.getVertexId(v1)] = -1;

        int destination = input.getVertexId(v2);

        for (int i = 1; i < input.numOfVertices; i++) {

            int d = minDistance(input, Distances, Not_In_MST, input.getVertexId(v2));
            Not_In_MST[d] = true;

            Path.add(input.getVertexById(d));

            for (int j = 0; j < input.numOfVertices; j++) {
                int NewDis = Distances[d] + input.getEdgeCost(d, j);
                if (!Not_In_MST[j] && input.getEdgeCost(d, j) != 0 && NewDis < Distances[j]) {
                    Parent[j] = d;
                    Distances[j] = NewDis;
                }
            }
        }

        Vector<Integer> v = GetPath(input, Distances, Parent, v2);

        ArrayList<String> Vertices = new ArrayList<>();
        ArrayList<Edge> Edges = new ArrayList<Edge>();

        for (int i = 0; i < v.size(); i++) {
            Vertices.add(input.getVertexById(v.get(i)));
        }

        Graph result = new Graph(v.size(), true, Vertices);

        Vector<Integer>vec = new Vector<>();

        for (int i = 1; i < v.size(); i++)
        {
            int x =  v.get(i-1), y =  v.get(i);
            int cost = input.getEdgeCost(y,x);
            vec.add(cost);
        }
        for (int i = 1; i < result.vertices.size(); i++) {
            int x = result.vertices.get(i - 1).id, y = result.vertices.get(i ).id;
            result.addEdge(y,x, vec.get(vec.size()-i));
        }
        return result;
    }


    Vector<Integer> GetPath(Graph input, int dist[], int par[], String v2) {
        Vector<Integer> vec = new Vector<>();String ResultSteps2="";
        int destination = input.getVertexId(v2);
        for (int i = 0; i < input.numOfVertices; i++) {
            int temp = par[i];
            //System.out.print(i + " <- ");
            if (i == destination) {
                ResultSteps2+=Integer.toString(i) ;
                vec.add(destination);
                while (temp != -1) {
                    ResultSteps2+=" >- "+temp  ;
                    // System.out.print(temp + " <- ");
                    vec.add(temp);
                    temp = par[temp];
                }
                for (int d = ResultSteps2.length()-1; d >=0; d--) {
                    ResultSteps+=ResultSteps2.charAt(d);
                }
                ResultSteps+="\t Distance : "+dist[i];
            }
        }
        return vec;


    }
}
