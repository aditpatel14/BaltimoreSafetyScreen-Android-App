
public class Edge implements Comparable<Edge> {
	private final Vertex v;
	private final Vertex w;
	private final double weight;
	
	public Edge(Vertex v, Vertex w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight(){
		return weight;
	}
	
	public int either(){
		return v.uid;
	}
	
	public int other(int vertex){
		if (vertex == v.uid)
			return w.uid;
		else if (vertex == w.uid)
			return v.uid;
		else throw new IllegalArgumentException();
	}
	
	public int compareTo(Edge that){
		if (this.weight() < that.weight())
			return -1;
		else if (this.weight() > that.weight())
			return 1;
		else
			return 0;
	}
	
	public String toString(){
		return v.uid + " " + w.uid + " " + weight;
	}
}
