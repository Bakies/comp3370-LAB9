package edu.wit.cs.comp3370;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/* Calculates the minimal spanning tree of a graph 
 * 
 * Wentworth Institute of Technology
 * COMP 3370
 * Lab Assignment 9
 * 
 */

public class LAB9 {
	
	// TODO document this method
	public static void FindMST(Graph g) {
		for (Vertex v : g.getVertices())
			v.makeSet();
		g.sortVertices();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (Vertex x : g.getVertices()) {
			for (Vertex y : g.getVertices()) {
				if (x == y)
					continue;
				edges.add(new Edge(x, y, Math.sqrt(Math.pow(x.x - y.x, 2) + Math.pow(x.y - y.y, 2))));
			}
		}
		Collections.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Double.compare(o1.cost, o2.cost);
			}
		});

		for (Edge e : edges) {
			if (e.src.findSet() != e.dst.findSet()) {
				g.addEdge(e.src, e.dst);
				union(e.src, e.dst);
			}
		}
	}

	
	private static void union(Vertex u, Vertex v) {
		Vertex x = u.findSet();
		Vertex y = v.findSet();

		if (x.rank > y.rank)
			y.parent = x;
		else {
			x.parent = y;
			if (x.rank == y.rank)
				y.rank++;
		}
	}

	/********************************************
	 * 
	 * You shouldn't modify anything past here
	 * 
	 ********************************************/
	

	// reads in an undirected graph from a specific file formatted with one
	// x/y node coordinate per line:
	private static Graph InputGraph(String file1) {
		
		Graph g = new Graph();
		try (Scanner f = new Scanner(new File(file1))) {
			while(f.hasNextDouble()) // each vertex listing
				g.addVertex(f.nextDouble(), f.nextDouble());
		} catch (IOException e) {
			System.err.println("Cannot open file " + file1 + ". Exiting.");
			System.exit(0);
		}
		
		return g;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String file1;
		
		System.out.printf("Enter <points file> <edge neighborhood>\n");
		System.out.printf("(e.g: points/small .5)\n");
		file1 = s.next();

		// read in vertices
		Graph g = InputGraph(file1);
		g.epsilon = s.nextDouble();
		
		FindMST(g);

		s.close();

		System.out.printf("Weight of tree: %f\n", g.getTotalEdgeWeight());
	}

}
