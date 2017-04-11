package com.company.group18.baltimoress;

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
	public static int binSearch(Arrest[] array, Location location){
		//set initial upper and lower bounds of search, and index between the bounds
		int hi = array.length-1;
		int lo = 0;
		int mid = lo + (hi - lo)/2;
		
		while (lo <= hi){
			//set new mid to point to index between upper and lower bound
			mid = lo + (hi - lo)/2;

			//create temporary location object at middle inddex for comparison
			Location temp = array[mid].getLocation();
			
			if(temp.compareTo(location) > 0){	//search target is in left half of array
				hi = mid - 1;
			}else if(temp.compareTo(location) < 0){	//search target is in right half of array
				lo = mid + 1;
			}else{	//search hit
				return mid;
			}
		}
		return mid;
	}
}
