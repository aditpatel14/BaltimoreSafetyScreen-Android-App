package com.company.group18.baltimoress;

import java.util.ArrayList;

/**
 *  Class for adding weights to edges in order to find shortest path
 */

public class EdgeWeightedGraph {
	private int E, V, vertexID;
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	public ArrayList<Edge>[] adj;

	/**
	 * Constructor for class
	 * @param V Vertex in which we are adding to adjacency list
	 */
	public EdgeWeightedGraph(int V){
		this.V = V;
		this.E = vertexID = 0;
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		adj = (ArrayList<Edge>[]) new ArrayList[this.V];
		for (int v = 0; v < V; v++){
			adj[v] = new ArrayList<Edge>();
		}
	}

	/**
	 * Adding an arrest as a vertex in the graph
	 * @param a the arrest we are adding to the graph
	 */
	public void addVertex(Arrest a){
		if (vertexID < V){
			Vertex v = new Vertex(a,vertexID++);
			vertices.add(v);
		}else{
			System.out.println("Max Number Of Vertices added");
		}
	}

	/**
	 * Adding an edge to connect two arrests
	 * @param e The edge we are adding
	 */
	public void addEdge(Edge e){
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		edges.add(e);
		E++;
	}

	/**
	 * Takes an integer ID and turns it into a vertex type
	 * @param id the integer id were transforming
	 * @return the vertex type of the id if valid, if id is not valid return null
	 */
	public Vertex vertexIDtoVertex(int id){
		for (Vertex v: vertices){
			if(v.uid == id){
				return v;
			}
		}
		return null;
	}

	/**
	 * Transforms an arrest type to a vertex type
	 * @param a The arrest we are turning into a vertex type
	 * @return the vertex type of the arrest if valid, if arrest is not valid return null
	 */
	public Vertex arrestToVertex(Arrest a){
		for (Vertex v: vertices){
			if (v.arrest.compareTo(a) == 0){
				return v;
			}
		}
		return null;
	}

	/**
	 * Edge array list
	 * @return the edges in the graph
	 */
	public ArrayList<Edge> edges() {
		return edges;
	}

	/**
	 * Adjacency array list
	 * @param v index of the adjacency lidt
	 * @return the adjacent vertices in the graph
	 */
	public Iterable<Edge> adj(int v){
		return adj[v];
	}

	/**
	 * Getter for vertices
	 * @return number of vertices in graph
	 */
	public int V(){
		return V;
	}

	/**
	 * Getter for ID of vertex
	 * @return ID of vertex
	 */
	public int vertexID(){
		return vertexID;
	}

	/**
	 * Getter for number of edges in graph
	 * @return number of edges in graph
	 */
	public int E(){
		return E;
	}
}