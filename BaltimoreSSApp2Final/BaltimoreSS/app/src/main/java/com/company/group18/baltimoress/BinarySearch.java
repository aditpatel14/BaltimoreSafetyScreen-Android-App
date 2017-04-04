package com.company.group18.baltimoress;

public class BinarySearch {

	public static int binSearch(Arrest[] array, Location location){
		int hi = array.length-1;
		int lo = 0;
		
		while (lo <= hi){
			int mid = lo + (hi - lo)/2;
			
			Location temp = array[mid].getLocation();
			
			if(temp.compareTo(location) > 0){
				hi = mid - 1;
			}else if(temp.compareTo(location) < 0){
				lo = mid + 1;
			}else{
				return mid;
			}
		}
		return -1;
	}
}
