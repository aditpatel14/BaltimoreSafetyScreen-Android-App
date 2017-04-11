package com.company.group18.baltimoress;

/**
 * Vertex class for the project
 */
public class Vertex implements Comparable<Vertex> {
	public Arrest arrest;
	public int uid;

	/**
	 * Constructor for the class
	 * @param v arrest represented as the vertex of the graph
	 * @param i id of the arrest
	 */
	public Vertex(Arrest v, int i) {
		arrest = v;
		uid = i;
	}

	/**
	 * Comparing arrests
	 * @param other arrest we are comparing to
	 * @return integer representation that accurately portrays the compare to
	 */
	public int compareTo(Vertex other) {
		return arrest.compareTo(other.arrest);
	}

	/**
	 * String format for output
	 * @return String output of arrests
	 */
	public String toString(){
		return arrest.toString();
	}
}