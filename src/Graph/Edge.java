/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author fatma
 */@SuppressWarnings("unchecked")

public class Edge {

	public Integer first,second,cost;
	public Edge(int f,int s, int c) {
		first=f;
		second=s;
		cost=c;
	}
	public void displayedge(){

		System.out.println("\tfirst :"+first+" \tsecond :"+second+"\t cost :"+cost );
	}
	
}