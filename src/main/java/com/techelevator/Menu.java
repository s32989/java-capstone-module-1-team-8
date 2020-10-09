package com.techelevator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
	private String userChoice = "";
	private Scanner userInput = new Scanner(System.in);
	private Map<String, VendingMachineItem> vendingMachineData;
	private List<String> itemList;
	
	public Menu(Map<String, VendingMachineItem> data, List<String> itemList) {
		
		this.itemList = itemList;
		this.vendingMachineData = data;
		
	}
	
	public void showHeader() {
		
		System.out.println("*********************");
		System.out.println("** Vendo-Matic 800 **");
		System.out.println("**  Umbrella Corp  **");
		System.out.println("*********************");	
		
		System.out.println();
		
	}
	
	public void showMenu() {
		System.out.println("***Main Menu***");
		System.out.println();
		System.out.println("[1] Display Vending Machine Options");
        System.out.println("[2] Purchase");
        System.out.println("[3] Exit");
        System.out.println();
        
	}
	
	public void getInput() {
		
		System.out.print("Please choose an option>>> ");
		userChoice = userInput.nextLine();
		
		String userChoiceTrim = userChoice.trim();
		
		while (!userChoiceTrim.equals("1") && !userChoiceTrim.equals("2") && !userChoiceTrim.equals("3")) {
			System.out.println("Please choose (1), (2), or (3) >>> ");
			userChoice = userInput.nextLine();
			userChoiceTrim = userChoice.trim();
		}
		
		userChoice = userChoiceTrim;
		
	}
	
	public void useInput() {
		if (userChoice.equals("1")) {
			
			for (String key: vendingMachineData.keySet()) {
				VendingMachineItem vMI = vendingMachineData.get(key);
				System.out.println(vMI.getProduct() + ": " + vMI.getInventory() + " remaining.");
			}
			
			showMenu();
			getInput();
			useInput();
			
		}else if(userChoice.equals("2")) {
			goToPurchaseMenu();
		}else {
			System.exit(0);
		}
	}
	
	
	
	public void goToPurchaseMenu() {
		PurchaseMenu pM = new PurchaseMenu(vendingMachineData, itemList);
		pM.run();
	}
	
	public void run() {
		showHeader();
		showMenu();
		getInput();
		useInput();
	}
}

