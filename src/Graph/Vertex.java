/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author fatma
 */
@SuppressWarnings("unchecked")

public class Vertex {
	public int id;
	public String name;
	public Vertex(int id,String name) {
		this.id=id;
		this.name=name;
	}
	public void display()
	{
		System.out.println("Id :"+id+" name :"+name);
	}
}
