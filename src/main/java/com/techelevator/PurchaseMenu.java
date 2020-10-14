package com.techelevator;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class PurchaseMenu {
	private String userChoice = "";													//keeps track of menu input entered		(<<<PRETTY SURE THESE ALL COULD BE THE SAME INSTANCE VARIABLE LOL)
	private String bill = "";														//keeps track of bill entered			(<<<PRETTY SURE THESE ALL COULD BE THE SAME INSTANCE VARIABLE LOL)
	private String itemKeyChoice = "";												//keeps track of item key entered 		(<<<PRETTY SURE THESE ALL COULD BE THE SAME INSTANCE VARIABLE LOL)
	private Scanner userInput = new Scanner(System.in);
	private Map<String, VendingMachineItem> vendingMachineItems;
	private Money balance = new Money();
	private File log;		
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	

	
	public PurchaseMenu (Map<String, VendingMachineItem> data, File logFile) {		//constructor for purchase Menu class
		this.vendingMachineItems = data;
		this.log = logFile;
		
	}
	
	public void run() {																//derived(?) method that contains methods from this class that 1) display menu in console, 2) take user input and 3) handles user input
		showMenu();
		getInput();
		useInput();
	}
	

//********* SHOWMENU AND ASSOCIATED METHODS **************
	
	
	private void showMenu() {														//this method prints the menu to the console
		System.out.println();
		System.out.println("***Purchase Menu***");
		System.out.println();
		System.out.println("[1] Feed Money");
        System.out.println("[2] Select Product");
        System.out.println("[3] Finish Transaction");
        System.out.println();
        showBalance();
        
	}
	
	private void showBalance() {
		System.out.println(balance.displayBalance());
		System.out.println();
	}
	
	
//********* GETINPUT AND ASSOCIATED METHODS ***********
	
	
	private void getInput() {														//this method asks the user for input
		
		System.out.print("Please choose an option>>> ");
		String rawInput = userInput.nextLine();
		System.out.println();
		
		userChoice = validateInput(rawInput);
		
	}
	
	private String validateInput(String rawInput) {									//this method validates the user's input
		
		String validateMe = rawInput.trim();
		while (!validateMe.equals("1") && !validateMe.equals("2") && !validateMe.equals("3")) {
			System.out.println("Please choose (1), (2), or (3) >>> ");
			validateMe = userInput.nextLine().trim();
		}
		return validateMe;
	}
	
	
//************ USEINPUT AND ASSOCIATED METHODS ***********
	
	
	private void useInput() { 
		
		if (userChoice.equals("1")) {
		
			getABill();
			
			validateBillEntered();
			
			logBillEntered(); 
			
			run();
		
		}else if(userChoice.equals("2")) {
			
			showProductMenu();
			
			getItemKeyInput();
			
			tryPurchase();
			
		} else {
			
			double balanceBeforeDispense = balance.getBalance();
			
			dispenseChange();
			
			logChangeGiven(balanceBeforeDispense);
			
			Menu m = new Menu(vendingMachineItems, log);	
			
			m.run();
			
		}
		
	}
	
	private void getABill() {														//"take a bill" from the user
		
		System.out.print("Please add a $1, $2, $5 or $10 bill>>> ");
		
		String moneyEntered = userInput.nextLine().trim();
		
		bill = moneyEntered;
		
	}
	
	private void validateBillEntered() {												//ensures a valid bill denomination was entered and adds it the balance
		
		while(!bill.equals("1") && !bill.equals("2") && !bill.equals("5") && !bill.equals("10")) {
			System.out.println("Please add a $1, $2, $5 or $10 bill. Type exit to go back to the Purchase menu.");
			
			bill = userInput.nextLine();
			
			if (bill.trim().toLowerCase().equals("exit")) {
				
				run();
				
			}
		}
		
		double addMeToBalance = Double.parseDouble(bill);
		balance.feedMoney(addMeToBalance);
		
	}
	
	private void logBillEntered() {													//records each time a bill is entered
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
    	String dateString2 = dateFormat2.format(new Date()).toString();
    	
		try{
			
			FileWriter logger = new FileWriter(log,true);
		
			String logData = dateString2 + " FEED MONEY " + formatter.format(balance.fedMoney()) + " " + formatter.format(balance.getBalance());
			
			try (PrintWriter pw = new PrintWriter(logger)){
				pw.println(logData);
			}catch(Exception e){
				
			}
				
		}catch (Exception e) {
			
		}
		
	}
	
	private void showProductMenu(){													//this method shows a menu with information for each product
		
		for (String key: vendingMachineItems.keySet()) {					
			VendingMachineItem vMI = vendingMachineItems.get(key);
			System.out.print(vMI.getItemKey() + " |" + vMI.getProduct() + ": " + formatter.format(vMI.getPrice()) + " |" + vMI.getProductType() + " |" + vMI.getInventory() + " remaining");
			if(vMI.getInventory() == 0) {
				System.out.print(":  SOLD OUT");
			}
			System.out.println();
		
			
		}
		System.out.println();
		System.out.print("Please select product code>>> ");
		
	}
	
	private void getItemKeyInput() {													//this method validates and stores the desired item key from the user
		
		String theirInput = userInput.nextLine().trim().toUpperCase();
		
		while (!vendingMachineItems.keySet().contains(theirInput)) {
			System.out.println();
			System.out.println("Please enter a valid key: ");
			System.out.println();
	
			theirInput = userInput.nextLine().trim().toUpperCase();
		}
		
		itemKeyChoice = theirInput;
	}
	
	private void tryPurchase() {																							//*** attempts to allow user to purchase selected item
		
		if(checkMapPrice() > balance.getBalance()) {																	//if the price of the item is greater than the user's balance
			System.out.println();
			System.out.println("Not enough money in balance.");
			run();
		}
		if(vendingMachineItems.get(itemKeyChoice).getInventory() < 1) {													//if the item is out of stock
			System.out.println();
			System.out.println("Out of stock.");
			run();
		}
		
		if (checkMapPrice() <= balance.getBalance() && vendingMachineItems.get(itemKeyChoice).getInventory() >= 1){		//successful purchase
			
			double balanceBefore = balance.getBalance();
			
			updateBalance();
			
			updateInventory();
			
			displayPurchaseMessage();
			
			logPurchase(balanceBefore);

			run();
		}
		
	}
	
	private double checkMapPrice() {																	//this method checks the price of the item in the TreeMap using the key the user provided		
		
		return vendingMachineItems.get(itemKeyChoice).getPrice();
		
	}
	
	private void updateBalance() {																	//subtracts item cost from balance after purchase 
		
		balance.updateBalanceAfterPurchase(vendingMachineItems.get(itemKeyChoice).getPrice());
		
	}
	
	private void updateInventory() {																	//updates inventory data after a purchase
		
		vendingMachineItems.get(itemKeyChoice).setInventory();
		
	}
	
	private void displayPurchaseMessage() {												
		
		System.out.println();
		System.out.println(vendingMachineItems.get(itemKeyChoice).getProduct() + ", $" + vendingMachineItems.get(itemKeyChoice).getPrice() + ", " + balance.displayBalance() + ", " + vendingMachineItems.get(itemKeyChoice).getItemMessage());
		System.out.println();
	}
	
	private void logPurchase(double beforeBalance) {													//records each time a successful purchase was made
		
		VendingMachineItem vMI = vendingMachineItems.get(itemKeyChoice);
    	DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
    	String dateString2 = dateFormat2.format(new Date()).toString();
    	
		try{
			FileWriter logger = new FileWriter(log,true);
			
			String logData = dateString2 + " " + vMI.getProduct() + " " + formatter.format(beforeBalance) + " " + formatter.format(balance.getBalance());
			try (PrintWriter pw = new PrintWriter(logger)){
				pw.println(logData);
			}catch(Exception e){
				
			}
			
		}catch (Exception e) {
			
		}
		
	}
	
	private void dispenseChange() {																	//formats and empties the balance, then "dispenses change" to user
		
		balance.formatChange();
		System.out.println(balance.makeChange());
		balance.emptyBalanceToMakeChange();
		
	}
	
	private void logChangeGiven(double remainingBal) {												//records each time change is dispensed

    	DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
    	String dateString2 = dateFormat2.format(new Date()).toString();
		
		try{
			FileWriter logger = new FileWriter(log,true);
			
			String logData = dateString2 + " MAKE CHANGE " +  formatter.format(remainingBal) + " " + formatter.format(balance.getBalance());
			try (PrintWriter pw = new PrintWriter(logger)){
				pw.println(logData);
			}catch(Exception e){
				
			}
			
		}catch (Exception e) {
			
		}
	}
}