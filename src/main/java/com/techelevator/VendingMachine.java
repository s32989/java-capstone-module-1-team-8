package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine  {

	public static Map <String, VendingMachineItem> vendingMachineItems = new TreeMap<String, VendingMachineItem>();  // each time you run VendingMachine.java there's a static hashMap that can hold VendingMachineItem objects with their associated key

	
	public static void main (String[] args) {
		
		initializeVendingMachineMap();
		Menu main = new Menu(vendingMachineItems);
		main.run();
		
	}
	
	public static void initializeVendingMachineMap() {
		
		String path = "vendingmachine.csv"; 					
		
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
