package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine  {

	public static Map <String, VendingMachineItem> vendingMachineItems = new TreeMap<String, VendingMachineItem>();  // each time you run VendingMachine.java there's a static hashMap that can hold VendingMachineItem objects with their associated key

	public static File log = new File("log.txt");
	
	public static void main (String[] args) {
		
		initializeVendingMachineMap();																				//we run this method to populate our TreeMap with data from the text file
		Menu main = new Menu(vendingMachineItems, log);																//we make a new Menu object, we pass in our TreeMap and our log file to be used in that code
		main.run();																									//we call the run method(a derived method in the Menu class) that displays our menu)
		
	}
	
	public static void initializeVendingMachineMap() {
		
		String path = "vendingmachine.csv"; 					
		
		File vendingMachineItemInfo = new File(path);																//create a file object that is at the path; 
		
		try {
			Scanner fileScanner = new Scanner(vendingMachineItemInfo);												//Open a scanner to take in the file
			
			while (fileScanner.hasNextLine()) {																		//"for as long at there is another line in the input file"
				
				String splitMe = fileScanner.nextLine();															//make a String that contains the line of text
				
				String[] info = splitMe.split("\\|");																//split that String into a String array (split it at each "pipe": |)
				
				VendingMachineItem vItem = new VendingMachineItem(info);											//make a new VendingMachine item object (VendingMachineItems take a string array argument for their constructor)
				
				vendingMachineItems.put(info[0], vItem);															//knowing how the input file is formatted, we know that the first piece of information is the product key, we chose to use that as our key in our TreeMap. We store the rest of the data from the input file in a vendingMachineItem object (vItem). 
				
			}
			
			fileScanner.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		
	}
	

}
