package Graph;
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
