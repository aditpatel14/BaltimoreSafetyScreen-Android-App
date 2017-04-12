package com.company.group18.baltimoress;

/**
 * represents a weighted edge between vertices in a graph
 */
public class Edge implements Comparable<Edge> {
	private final Vertex v;
	private final Vertex w;
	private final double weight;

	/**
	 * initializes edge
	 *
	 * @param v vertex adjacent to edge
	 * @param w vertex adjacent to edge
	 * @param weight double representing weight of the edge
	 */
	public Edge(Vertex v, Vertex w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	/**
	 * returns the weight of the edge
	 *
	 * @return double representing weight of the edge
	 */
	public double weight(){
		return weight;
	}

	/**
	 * returns either vertex adjacent to the edge
	 *
	 * @return instance of Vertex adjacent to the edge
	 */
	public int either(){
		return v.uid;
	}

	/**
	 * returns the vertex connected by the edge to the given vertex
	 *
	 * @param vertex instance of Vertex
	 * @return instance of Vertex connected to the given vertex by the edge
	 */
	public int other(int vertex){
		if (vertex == v.uid)
			return w.uid;
		else if (vertex == w.uid)
			return v.uid;
		else throw new IllegalArgumentException();
	}

	/**
	 * Method that checks if a vertex is either end of the edge
	 * @param c is the vertex to look for
	 * @return True if it is, false otherwise
	 */
	public boolean contains(Arrest c){
		return v.arrest.compareTo(c) == 0 || w.arrest.compareTo(c) == 0;
	}

	/**
	 * compares the weight of one vertex to another
	 *
	 * @param that instance of Vertex to be compared
	 * @return 1 if vertex has greater weight than that of vertex 'that', -1 if vertex has lesser
	 * weight than that of vertex 'that', 0 if the weights are equal
	 */
	public int compareTo(Edge that){
		if (this.weight() < that.weight())
			return -1;
		else if (this.weight() > that.weight())
			return 1;
		else
			return 0;
	}

	/**
	 * returns string representation of the edge
	 *
	 * @return string representation of the edge
	 */
	public String toString(){
		return v.uid + " " + w.uid + " " + weight;
	}

	/**
	 * returns arrest object associated with vertex w
	 *
	 * @return arrest associated with vertex w
	 */
	public Arrest getNonCentralArrest(){
		return w.arrest;
	}

	/**
	 * returns arrest object assoc. with vertex v
	 *
	 * @return arrest object assoc. with v
	 */
	public Arrest getCentralArrest(){
		return v.arrest;
	}
}