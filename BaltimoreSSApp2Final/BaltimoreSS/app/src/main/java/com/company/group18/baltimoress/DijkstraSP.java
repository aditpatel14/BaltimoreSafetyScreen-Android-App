package com.company.group18.baltimoress;

/**
 * classic implementation of Dijkstra's Algorithm for finding shortest path in a graph
 */
public class DijkstraSP {
	private Edge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;

	/**
	 * performs shortest path calculation
	 *
	 * @param G graph to find shortest path
	 * @param s source vertex of shortest path calculation
	 */
	public DijkstraSP(EdgeWeightedGraph G, int s){
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());

		//initialize distance from source of each vertex to positive infinity
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;

		//construct shortest path by finding vertex with shortest distance from tree and
		//relaxing its edges
		pq.insert(s,  0.0);
		while (!pq.isEmpty())
			relax(G, pq.delMin());
	}

	/**
	 * updates distance to a vertex when a shorter path is found
	 *
	 * @param G graph being operated on
	 * @param v vertex to relax
	 */
	private void relax(EdgeWeightedGraph G, int v){
		for (Edge e : G.adj(v)){
			int w = e.either();
			if (distTo[w] > distTo[v] + e.weight()){
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (pq.contains(w)) pq.changeKey(w, distTo[w]);
				else				pq.insert(w, distTo[w]);
			}
		}
	}
}
