package edu.wit.cs.comp3370;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// represents a graph as a list of vertices and edges
public class Graph implements GraphInterface {
	public void sortVertices() {
		Collections.sort(vs, new Comparator<Vertex>() {
			@Override
			public int compare(Vertex o1, Vertex o2) {
				return Double.compare(o1.x, o2.x);
			}
		});
	}

	/********************************************
	 * 
	 * You shouldn't modify anything past here
	 * 
	 ********************************************/

	private ArrayList<Vertex> vs;
	private ArrayList<Edge> edges;
	public double epsilon = 0; // set to maximum edge distance
	private int nextVertexID = 0;	// unique ID of each vertex
	
	public Graph() {
		vs = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
	}
	
	@Override
	public void addVertex(double x, double y) {
		Vertex v = new Vertex();
		v.x = x; v.y = y; v.ID = nextVertexID++;
		vs.add(v);
	}
	
	// adds an edge to graph if it is within epsilon limit
	public void addEdge(Vertex src, Vertex dst) {
		if (dist(src, dst) < epsilon)
			edges.add(new Edge(src, dst, dist(src, dst)));
	}
	
	// finds the cartesian distance between two vertices
	private static double dist(Vertex s, Vertex d) {
		return Math.sqrt(Math.pow(s.x-d.x, 2) + Math.pow(s.y-d.y, 2));
	}
	
	public int size() {
		return vs.size();
	}
	
	@Override
	public Vertex[] getVertices() {
		return vs.toArray(new Vertex[vs.size()]);
	}
	
	@Override
	public Edge[] getEdges() {
		return edges.toArray(new Edge[edges.size()]);
	}
	
	// sums up the costs of all edges in the graph
	@Override
	public double getTotalEdgeWeight() {
		double ret = 0;
		for (Edge e: edges)
			ret += e.cost;
		return ret;
	}

}
