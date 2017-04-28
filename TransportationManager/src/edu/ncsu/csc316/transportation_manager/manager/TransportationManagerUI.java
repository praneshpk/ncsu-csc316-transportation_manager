package edu.ncsu.csc316.transportation_manager.manager;

import java.util.Scanner;

/**
 * The TransportationManagerUI class allows the user to interface with 
 * the TransportationManager
 * @author Pranesh Kamalakanthan
*/
public class TransportationManagerUI {

	/**
	 * Starts the program
	 * @param args commandline arguments
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the path to the map: ");
		TransportationManager t = new TransportationManager(in.nextLine());
		System.out.print("Are you trying to minimize cost or asphalt? ");
		String choice = in.next();
		if(choice.toLowerCase().equals("cost"))
			System.out.println(t.getMinimumHighways("COST"));
		else
			System.out.println(t.getMinimumHighways("ASPHALT"));
		System.out.println(t.getAdjacencyList());
		
	}

}
