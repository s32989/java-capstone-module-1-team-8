package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine  {

	public static Map <String, VendingMachineItem> vendingMachineItems = new HashMap<String, VendingMachineItem>();  // each time you run VendingMachine.java there's a static hashMap that can hold VendingMachineItem objects with their associated key
	
	//public static File log;																						// if we want to do the log challenge
	
	public static void main (String[] args) {
		
		initializeVendingMachineMap();
		//run main menu here
		
		//tests to show hashMap works
		System.out.println(vendingMachineItems.get("A1").getProduct());
		System.out.println(vendingMachineItems.get("A1").getPrice());
		System.out.println(vendingMachineItems.get("A1").getProductType());
		System.out.println(vendingMachineItems.get("A1").getInventory());
		
		System.out.println(vendingMachineItems.get("D4").getProduct());
		System.out.println(vendingMachineItems.get("D4").getPrice());
		System.out.println(vendingMachineItems.get("D4").getProductType());
		System.out.println(vendingMachineItems.get("D4").getInventory());
		
		
	}
	
	public static void initializeVendingMachineMap() {
		
		String path = "/Users/student/workspace/java-capstone-module-1-team-8/vendingmachine.csv"; 					//***** using this until we get userInput *****
		
		File vendingMachineItemInfo = new File(path);
		
		try {
			Scanner fileScanner = new Scanner(vendingMachineItemInfo);
			
			while (fileScanner.hasNextLine()) {																		//for as long at there is another line in the input file
				String splitMe = fileScanner.nextLine();
				
				String[] info = splitMe.split("\\|");																//split input for each line delimited by |
				
				VendingMachineItem vItem = new VendingMachineItem(info);											//VendingMachineItem class passes a string array, (but never uses the first element :o)
				
				vendingMachineItems.put(info[0], vItem);															//first element is the vending machine code, list the items in hashMap by their code.
				
			}
			
			fileScanner.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
		
	}
}
