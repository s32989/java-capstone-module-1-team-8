package com.techelevator;

import java.io.File;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Scanner;

public class Menu {
	private String userChoice = "";
	private Scanner userInput = new Scanner(System.in);
	private Map<String, VendingMachineItem> vendingMachineItems;
	private File log;

	
	public Menu(Map<String, VendingMachineItem> data, File logFile) {			//"to make a new Menu you must have a Map (that has String keys and vendingMachineItem Objects) and a logFile 
		
		this.vendingMachineItems = data;
		this.log = logFile;
	}
	
	public void run() {															//the "run" method is a derived method that 1)displays the header 2) displays the menu contents 3) gets user input and 4) uses that input
		showHeader();
		showMenu();
		getInput();
		useInput();
	}
	
	private void showHeader() {													//this method prints the menu head to the console.
		System.out.println();
		System.out.println("*********************");
		System.out.println("** Vendo-Matic 800 **");
		System.out.println("**  Umbrella Corp  **");
		System.out.println("*********************");	
		
		System.out.println();
		
	}
	
	private void showMenu() {													//this method prints the menu to the console.
		System.out.println();
		System.out.println("***Main Menu***");
		System.out.println();
		System.out.println("[1] Display Vending Machine Items");
        System.out.println("[2] Purchase");
        System.out.println("[3] Exit");
        System.out.println();
        
	}

//********* GETINPUT AND ASSOCIATED METHODS **********
	
	private void getInput() {													//this method asks the user for input
		
		System.out.print("Please choose an option>>> ");
		String rawInput = userInput.nextLine();
		System.out.println();
		
		userChoice = validateInput(rawInput);
		
	}
	
	private String validateInput(String rawInput) {								//this method validates the user's input
		
		String validateMe = rawInput.trim();
		while (!validateMe.equals("1") && !validateMe.equals("2") && !validateMe.equals("3")) {
			System.out.println("Please choose (1), (2), or (3) >>> ");
			validateMe = userInput.nextLine().trim();
		}
		return validateMe;
	}
	
//************* USEINPUT AND ASSOCIATED METHODS ************
	
	private void useInput() {													//this method handles the user's input
		if (userChoice.equals("1")) {
			
			displayItemMenu();
			
			showMenu();
			getInput();
			useInput();
			
		}else if(userChoice.equals("2")) {
			goToPurchaseMenu();
		}else {
			System.out.println("Thank you for using the Vend-O-Matic 800 \n Have a nice day!");
			System.exit(0);
		}
	}
	
	private void displayItemMenu() {												//this method displays the list of items with their information (key/product/price/product type/inventory)
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		for (String key: vendingMachineItems.keySet()) {					
			VendingMachineItem vMI = vendingMachineItems.get(key);
			System.out.print(vMI.getItemKey() + " |" + vMI.getProduct() + ": " + formatter.format(vMI.getPrice()) + " |" + vMI.getProductType() + " |" + vMI.getInventory() + " remaining");
			if(vMI.getInventory() == 0) {
				System.out.print(":  SOLD OUT");
			}
			System.out.println();
		}
	}
	
	private void goToPurchaseMenu() {										//this method allows takes the user to the purchase menu						
		PurchaseMenu pM = new PurchaseMenu(vendingMachineItems, log);		//"create a new purchase menu object (this object uses data from the TreeMap vendingMachineData and also can write to our log file)	
		pM.run();															//***** NOTE: it has a run method that operates much like the run method in this class			
	}
	
}