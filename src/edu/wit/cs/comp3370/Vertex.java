package edu.wit.cs.comp3370;

// represents a vertex in a graph, including a unique ID to keep track of vertex
public class Vertex {

	public Vertex parent;
	public int rank;
	
	public void makeSet() {
		this.rank = 0;
		this.parent = this;

	}

	public Vertex findSet() {
		if (this != this.parent)
			this.parent = this.parent.findSet();
		return this.parent;
	}

	/********************************************
	 * 
	 * You shouldn't modify anything past here
	 * 
	 ********************************************/

	public double x;
	public double y;
	public int ID;

}
