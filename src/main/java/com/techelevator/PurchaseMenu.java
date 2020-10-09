package com.techelevator;



import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PurchaseMenu {
	private String userChoice = "";
	private String itemKeyChoice = "";
	private Scanner userInput = new Scanner(System.in);
	public int inputBillSum = 0;
	private Map<String, VendingMachineItem> vendingMachineData;
	private List<String> itemList;
	private Money balance = new Money();
	

	
	public PurchaseMenu (Map<String, VendingMachineItem> data, List<String> vItemList) {
		this.itemList = vItemList;
		this.vendingMachineData = data;
		
	}
	
	
	public void showMenu() {
		System.out.println();
		System.out.println("***Purchase Menu***");
		System.out.println();
		System.out.println("[1] Feed Money");
        System.out.println("[2] Select Product");
        System.out.println("[3] Finish Transaction");
        System.out.println();
        showBalance();
        
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
			
			System.out.println("Please add a $1, $2, $5 or $10 bill.");
			
			String moneyEntered = userInput.nextLine();
			
			String moneyTrimmed = moneyEntered.trim();
			
			while(!moneyTrimmed.equals("1") && !moneyTrimmed.equals("2") && !moneyTrimmed.equals("5") && !moneyTrimmed.equals("10")) {
				System.out.println("Please add a $1, $2, $5 or $10 bill. Type exit to go back to the Purchase menu.");
				
				moneyTrimmed = userInput.nextLine();
				
				if (moneyTrimmed.trim().toLowerCase().equals("exit")) {
					
					showMenu();
					getInput();
					useInput();
					
				}
				
			}
			
			double addMeToBalance = Double.parseDouble(moneyTrimmed);
			balance.feedMoney(addMeToBalance);
		
			showMenu();
			getInput();
			useInput();
		
		}else if(userChoice.equals("2")) {
			
			for (int i = 0; i < itemList.size(); i++) {
				System.out.println(itemList.get(i));
			}
			
			getItemKeyInput();
			
			if(checkMapPrice() > balance.getBalance()) {
				System.out.println("Not enough money in balance.");
				run();
			}
			if(vendingMachineData.get(itemKeyChoice).getInventory() < 1) {
				System.out.println("Out of stock.");
				run();
			}
			
			if (checkMapPrice() <= balance.getBalance() && vendingMachineData.get(itemKeyChoice).getInventory() >= 1){
				balance.updateBalanceAfterPurchase(vendingMachineData.get(itemKeyChoice).getPrice());
				vendingMachineData.get(itemKeyChoice).setInventory();
				
				showMenu();
				getInput();
				useInput();
			}
			
		} else {
			
			balance.formatChange();
			System.out.println(balance.makeChange());
			balance.emptyBalanceToMakeChange();
			

			Menu m = new Menu(vendingMachineData, itemList);
			m.run();
			
		}
		
	}
	
	public void showBalance() {
		System.out.println(balance.displayBalance());
		System.out.println();
	}
	
	public void getItemKeyInput() {
		String theirInput = userInput.nextLine().trim().toUpperCase();
		
		
		while (!vendingMachineData.keySet().contains(theirInput)) {
			System.out.println();
			System.out.println("Please enter a valid key: ");
			System.out.println();
			for (int i = 0; i < itemList.size(); i++) {
				System.out.println(itemList.get(i));
			}
			
			theirInput = userInput.nextLine();
		}
		
		itemKeyChoice = theirInput;
	}
	
	public double checkMapPrice() {
		
		return vendingMachineData.get(itemKeyChoice).getPrice();
		
	}
	
	public void checkMapInventory() {
		
	}
	
	public void run() {
		showMenu();
		getInput();
		useInput();
		
	}

}

	

