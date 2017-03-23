
public class BinarySearch {

	public static int binSearch(Arrest[] array, Location location){
		int hi = array.length-1;
		int lo = 0;
		
		while (lo <= hi){
			int mid = (hi + lo)/2;
			
			System.out.println(array[mid]);
			Location temp = array[mid].getLocation();
			if(temp.compareTo(location) > 1)
				hi = mid-1;
			else if(temp.compareTo(location) < 1)
				lo = mid+1;
			else
				return mid;
		}
		//modify so it returns the closest 5 arrest locations
		return -1;
	}
}
