import java.util.ArrayList;

/**
 * classic implementation of Binary Search for arrests
 */
public class BinarySearch {

	/**
	 * searches for nearest arrest to a given location
	 *
	 * @param array	array of arrests
	 * @param location instance of Location
	 * @return array index of closest arrest location
	 */
	public static Arrest binSearch(Arrest[] array, Location location){
		//set initial upper and lower bounds of search, and index between the bounds
		int hi = array.length - 1;
		int lo = 0;
		int mid = lo + (hi - lo)/2;

		ArrayList<Arrest> al = new ArrayList<Arrest>();


		while (lo <= hi){
			//set new mid to point to index between upper and lower bound
			mid = lo + (hi - lo)/2;

			//create temporary location object at middle inddex for comparison
			Location temp = array[mid].getLocation();
			
			if(temp.compareTo(location) > 0){	//search target is in left half of array
				hi = mid - 1;
				al.add(array[mid]);
			}
			else if(temp.compareTo(location) < 0){	//search target is in right half of array
				lo = mid + 1;
				al.add(array[mid]);
			}
			else if(temp.compareTo(location) == 0){	//search hit
				al.add(array[mid]);
				break;
			}
		}

		double distance = DataManipulation.distance(al.get(0).getLocation().getXcrd(), al.get(0).getLocation().getYcrd(),location.getXcrd(), location.getYcrd());
		Arrest closest = (al.get(0));

		for(int i = 0; i < al.size(); i++){
			double distance2 = Math.min(DataManipulation.distance(al.get(i).getLocation().getXcrd(), al.get(i).getLocation().getYcrd(),location.getXcrd(), location.getYcrd()), distance);
			if(distance2 < distance){
				distance = distance2;
				closest = al.get(i);
			}
		}
		return closest;
	}

}
