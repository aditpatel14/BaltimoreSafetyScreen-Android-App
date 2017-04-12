import java.util.ArrayList;

public class ClosestArrestFind {

	public static Arrest find(ArrayList<Arrest> arrestList, Arrest[] sorted){
		
		Location tofind = new Location(39.2970007586, -76.5793864662);
		
		//change last parameter for different threshold---------------------VVVVV
		ArrayList<Arrest> closestList =  DataManipulation.findClosestArrests(sorted, tofind, 0.005);

		Arrest[] closefind = closestList.toArray(new Arrest[closestList.size()]);
		System.out.println("\nPrinting the # of close arrests:\n" + closefind.length + "\n\nPrinting the close arrests:");

		//prints the closest arrests
		for(Arrest c: closefind){
			System.out.println(c);
		}	
		
		//store the found arrest (closest to location specified)
		Arrest found = sorted[BinarySearch.binSearch(sorted, tofind)];  
		
		//set up graph with first vertex as the found arrest and the rest as the ones close to it
		EdgeWeightedGraph g = new EdgeWeightedGraph(closefind.length + 1);
		g.addVertex(found);
		for(Arrest c: closefind){
			g.addVertex(c);
			System.out.println(g.vertexID());
		}	
		
		//determine the distance from the found arrest to each of its close arrests and create edges
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		for(Arrest c: closefind){
			double distance = DataManipulation.distance(found.getLocation().getXcrd(), found.getLocation().getYcrd(),
					c.getLocation().getXcrd(), c.getLocation().getYcrd());
			Vertex v = g.arrestToVertex(found);
			Vertex w = g.arrestToVertex(c);
			Edge toAdd = new Edge(v, w, distance);
			edges.add(toAdd);
			g.addEdge(toAdd);
		}
		
		
		Edge min = new Edge(null,null, 10000000);
		for (Edge e: edges){
			if (e.weight() < min.weight()){
				min = e;
			}
		}
		System.out.println(min.weight());
		return min.getNonCentralArrest();
	}
}