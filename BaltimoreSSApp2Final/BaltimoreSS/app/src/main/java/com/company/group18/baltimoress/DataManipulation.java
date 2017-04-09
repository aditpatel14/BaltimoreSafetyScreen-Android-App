package com.company.group18.baltimoress;


import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataManipulation {
	public static Arrest[] sorted ;
	public static ArrayList<Arrest> arrestList = new ArrayList<Arrest>();
	public static int index;

//	public static void setupFromData(){
//		//fills a static arraylist
//		fillArrestList();
//
//		//sort arrestList by location
//		sorted = arrestList.toArray(new Arrest[arrestList.size()]);
//		sorted = MergeSort.mergeSort(sorted);
////		System.out.println("\nPrinting Sorted:");
////		for(Arrest a:sorted){
////			System.out.println(a);
////		}
//	}

//	public static void main(String[] args){
//		//fills a static arraylist
//		fillArrestList();
//
//		//sort arrestList by location
//		sorted = arrestList.toArray(new Arrest[arrestList.size()]);
//		sorted = MergeSort.mergeSort(sorted);
////		System.out.println("\nPrinting Sorted:");
////		for(Arrest a:sorted){
////			System.out.println(a);
////		}
//
//		//search Test
//		//System.out.println("\n\nPrinting Search Result:\n" + sorted[BinarySearch.binSearch(sorted, new Location(39.2970007586, -76.5793864662))].toString());
//
//		//closest location search test
//		Location currentUserLocation = new Location(39.2970007586, -76.5793864662);
//
//		//change last parameter for different threshold---------------------VVVVV
//		ArrayList<Arrest> closestList =  findClosestArrests(sorted, currentUserLocation, 0.005);
//
//		Arrest[] closefind = closestList.toArray(new Arrest[closestList.size()]);
//		//System.out.println("\nPrinting the # of close arrests:\n" + closefind.length + "\n\nPrinting the close arrests:");
//
//		//prints the closest arrests
////		for(Arrest c: closefind){
////			//System.out.println(c);
////		}
//
//		EdgeWeightedGraph g = new EdgeWeightedGraph(closefind.length);
//
//		//g.addEdge(new Edge(currentUserLocation, closestList.get(0));
//
//
//	}



	public static ArrayList<Arrest> findClosestArrests(Arrest[] arrests, Location spot, double threshold){
		ArrayList<Arrest> closest = new ArrayList<Arrest>();

		int index = BinarySearch.binSearch(arrests, spot);

		
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
				//System.out.println(a.toString());
			}
		}
	}


	private static Date stringToDate(String string) {
		if(string.equals("blank")){
			return new Date(-1,-1,-1);
		}
		String[] temp = string.split("[//]");
		int d = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		int y = Integer.parseInt(temp[2]);

		//		System.out.println(y + " "+m+" "+ d);
		return new Date(d,m,y);
	}
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
	private static int stringToPost(String s){
		if(s.equals("blank"))
			return -1;
		return Integer.parseInt(s);
	}
	private static int stringToArrestId(String s){
		if(s.equals("blank"))
			return -1;
		return Integer.parseInt(s);
	}

	private static int stringToAge(String s){
		if(s.equals("blank"))
			return -1;
		return Integer.parseInt(s);
	}

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

	private static void writeToFile(String s){
		try {	
			FileWriter f = new FileWriter("data/output.txt", true);
			PrintWriter out = new PrintWriter(new BufferedWriter(f));
			out.println(s);
			out.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

