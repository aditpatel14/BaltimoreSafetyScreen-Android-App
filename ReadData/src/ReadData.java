import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class ReadData {

	public static void main(String[] args){
		ArrayList<ArrayList<String>> list = readInput();		
	}

	private static ArrayList<ArrayList<String>> readInput(){
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		try {		
			Scanner input = new Scanner(new File("data/BPD_Arrests_Formatted.txt"));
			int counter = 0;
			while(input.hasNext()){
				
				//Scans line from input file into string
				String current = input.nextLine();

				//edits string to remove opening and closing brackets
				current = current.replaceAll( "[\t]" , "%" );


				//Splits String to remove unnecessary characters and makes array
				String[] temp = current.split("[%]");

//				System.out.println(counter+" "+temp.length + " " +Arrays.toString(temp));
				System.out.println(counter);
				counter++;

				ArrayList<String> item = new ArrayList<String>();
				for(int i = 0; i<temp.length; i++){
					String itemParse = temp[i];
					item.add(itemParse);
				}

				String s2 = String.format("%-35s %-35s %-35s %-35s %-35s %-35s %-35s %-35s %-35s %-35s %-100s %-35s %-35s %-40s %-40s",
					temp[0],
					temp[1],
					temp[2],
					temp[3],
					temp[4],
					temp[5],
					temp[6],
					temp[7],
					temp[8],
					temp[9],
					temp[10],
					temp[11],
					temp[12],
					temp[13],
					temp[14]						
					);
				
				writeToFile(s2);
				
				//Adds array made form scanned string into jobs ArrayList to used for testing
				list.add(item);
			}
			input.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return list; 
		
	}
	
	
	/**Method used to write test case results to output.txt file
	 * 
	 * @param s, String to be written to output.txt
	 */
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

