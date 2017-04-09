package com.company.group18.baltimoress;

public class Vertex implements Comparable<Vertex> {
	public Arrest arrest;
	public int uid;

	public Vertex(Arrest v, int i) {
		arrest = v;
		uid = i;
	}

	public int compareTo(Vertex other) {
		return arrest.compareTo(other.arrest);
	}

	public String toString(){
		return arrest.toString();
	}
}