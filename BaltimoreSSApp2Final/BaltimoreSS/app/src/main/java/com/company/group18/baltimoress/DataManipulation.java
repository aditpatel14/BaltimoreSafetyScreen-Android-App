package com.company.group18.baltimoress;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class is used to manipulate the data 
 */
public class DataManipulation {
	
	public static Arrest[] sorted;
	public static ArrayList<Arrest> arrestList = new ArrayList<Arrest>();
	public static int index;



	/**
	 * @param arrests an array of arrest to search through 
	 * @param spot the centre of the location want want to search from 
	 * @param threshold the radius of from the location 
	 * @return the arrest in the given from in the spot and threshold
	 */
	public static ArrayList<Arrest> findClosestArrests(Arrest[] arrests, Location spot, double threshold){
		ArrayList<Arrest> closest = new ArrayList<Arrest>();

		//Added after pushing to playstore----------------------------------
		Arrest closestArrestToSpot = BinarySearch.binSearch(arrests, spot);
		closest.add(closestArrestToSpot);
		//==================================================================
		
		for (int i = 0; i < arrests.length; i++){
			if(spot.withinRegion(arrests[i].getLocation(), threshold)){
				//dosen't add toFind, adds all the ones around it.
				if (arrests[i].getLocation().compareTo(spot) != 0){
					closest.add(arrests[i]);
				}
			}
		}
		return closest;
	}


	/**
	 * @param dl the arraylist of arraylist of string data from the file 
	 */
	public static void fillArrestList(ArrayList<ArrayList<String>> dl){
		ArrayList<ArrayList<String>> list = dl;
//		System.out.println(list.get(0).toString());



		if(list.size() != 0){
			for (int i = 1; i < 1000; i++) {
				int arrestId = stringToArrestId(list.get(i).get(0));
				int age = stringToAge(list.get(i).get(1));
				String sex = list.get(i).get(2);
				String race = list.get(i).get(3);
				Date date = stringToDate(list.get(i).get(4));
				Time time = stringToTime(list.get(i).get(5));
				String arrestLocation = list.get(i).get(6);
				String incidentOffense = list.get(i).get(7);
				String incidentLocation = list.get(i).get(8);
				String charge = list.get(i).get(9);
				String chargeDescription = list.get(i).get(10);
				String district = list.get(i).get(11);
				int post = stringToPost(list.get(i).get(12));
				String neighborhood = list.get(i).get(13);
				Location location = stringToLocation(list.get(i).get(14));

				Arrest a = new Arrest(arrestId, age, sex, race, date, time,
						arrestLocation, incidentOffense, incidentLocation, charge,
						chargeDescription, district, post, neighborhood, location);

				arrestList.add(a);
			}
		}
	}


	/**
	 * Purpose is to convert string into Date object 
	 * @param string the date in string form
	 * @return the Date as date object 
	 */
	private static Date stringToDate(String string) {
		if(string.equals("blank")){
			return new Date(-1,-1,-1);
		}
		String[] temp = string.split("[//]");
		int d = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		int y = Integer.parseInt(temp[2]);

		return new Date(d,m,y);
	}

	/**
	 * Purpose is to convert string into time object
	 * @param string the string as Time 
	 * @return the Time (as Time object) 
	 */
	private static Time stringToTime(String string) {
		if(string.equals("blank")){
			return new Time(-1,-1);
		}
		String[] temp = string.split("[.:]");
		int h = 0;
		
		if(temp.length < 2){
			h = Integer.parseInt(temp[0]);
			return new Time(h,0);
		}
		int m = Integer.parseInt(temp[1]);

		return new Time(h,m);
	}

	/**
	 * Converts the postal code from string to int 
	 * @param s Postal code (string) 
	 * @return the postal code 
	 */
	private static int stringToPost(String s){
		if(s.equals("blank"))
			return -1;
		return Integer.parseInt(s);
	}

	/**
	 * Converts the Arrest ID from string to int 
	 * @param s Arrest ID (as String) 
	 * @return returns the postal code (int) 
	 */
	private static int stringToArrestId(String s){
		if(s.equals("blank"))
			return -1;
		return Integer.parseInt(s);
	}

	/**
	 * Converts the Age from string to int 
	 * @param s the age (string) 
	 * @return the age (int) 
	 */
	private static int stringToAge(String s){
		if(s.equals("blank"))
			return -1;
		return Integer.parseInt(s);
	}

	/**
	 * 
	 * Converts the latitude and longitude to location object 
	 * @param loc the latitude and longitude 
	 * @return The location as Location object 
	 */
	private static Location stringToLocation(String loc) {
		if(loc.equals("blank")){
			return new Location(-1,-1);
		}
		loc = loc.replaceAll( "[\"() ]" , "" );
		String[] locationPoints = loc.split("[,]");
		double x = Double.parseDouble(locationPoints[0]);
		double y = Double.parseDouble(locationPoints[1]);
		//		System.out.println(x + " " + y);
		return new Location(x,y);
	}


	/**
	 * CITE THIS source
	 * http://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
	 *
	 *
	 * Calculate distance between two points in latitude and longitude.
	 * If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 *
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @returns Distance in Meters
	 */
	public static double distance(double lat1, double lon1, double lat2, double lon2) {

		final int R = 6371; // Radius of the earth

		Double latDistance = Math.toRadians(lat2 - lat1);
		Double lonDistance = Math.toRadians(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance);
	}

}

