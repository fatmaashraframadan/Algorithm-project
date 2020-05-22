package Graph;

public class Vertex {
	int id;
	String name;
	public Vertex(int id,String name) {
		this.id=id;
		this.name=name;
	}
	public void display()
	{
		System.out.println("Id :"+id+" name :"+name);
	}
}
