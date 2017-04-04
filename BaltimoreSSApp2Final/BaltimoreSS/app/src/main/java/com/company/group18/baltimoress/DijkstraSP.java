package com.company.group18.baltimoress;

public class DijkstraSP {
	private Edge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	
	public DijkstraSP(EdgeWeightedGraph G, int s){
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		
		pq.insert(s,  0.0);
		while (!pq.isEmpty())
			relax(G, pq.delMin());
	}
	
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
