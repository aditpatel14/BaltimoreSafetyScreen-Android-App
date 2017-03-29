import java.util.ArrayList;

public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private ArrayList<Edge>[] adj;
	
	public EdgeWeightedGraph(int V){
		this.V = V;
		this.E = E;
		adj = (ArrayList<Edge>[]) new ArrayList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayList<Edge>();
	}
	
	public void addEdge(Edge e){
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
}
