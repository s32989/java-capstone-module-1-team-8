package com.techelevator;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class PurchaseMenu {
	private String userChoice = "";
	private String itemKeyChoice = "";
	private Scanner userInput = new Scanner(System.in);
	public int inputBillSum = 0;
	private Map<String, VendingMachineItem> vendingMachineData;
	private Money balance = new Money();
	private File log;														///NEW file 
	

	
	public PurchaseMenu (Map<String, VendingMachineItem> data, File logFile) {
		this.vendingMachineData = data;
		this.log = logFile;
		
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
		System.out.println();
		
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
			
			
			System.out.print("Please add a $1, $2, $5 or $10 bill>>> ");
			
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
			
			DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
	    	String dateString2 = dateFormat2.format(new Date()).toString();
	    	
			try{
				
				FileWriter logger = new FileWriter(log,true);
			
				String logData = dateString2 + " FEED MONEY $" + balance.fedMoney() + " $" + balance.getBalance() + "\n";
				
				try (PrintWriter pw = new PrintWriter(logger)){
					pw.write(logData);
				}catch(Exception e){
					
				}
				
				
			}catch (Exception e) {
				
			}
		
			showMenu();
			getInput();
			useInput();
		
		}else if(userChoice.equals("2")) {
			
			
			for (String key: vendingMachineData.keySet()) {					
				VendingMachineItem vMI = vendingMachineData.get(key);
				System.out.print(vMI.getItemKey() + " |" + vMI.getProduct() + ": $" + vMI.getPrice() + " |" + vMI.getProductType() + " |" + vMI.getInventory() + " remaining");
				if(vMI.getInventory() == 0) {
					System.out.print(":  SOLD OUT");
				}
				System.out.println();
			
				
			}
			System.out.println();
			System.out.print("Please select product code>>> ");
			
			getItemKeyInput();
			
			
			if(checkMapPrice() > balance.getBalance()) {
				System.out.println();
				System.out.println("Not enough money in balance.");
				run();
			}
			if(vendingMachineData.get(itemKeyChoice).getInventory() < 1) {
				System.out.println();
				System.out.println("Out of stock.");
				run();
			}
			
			if (checkMapPrice() <= balance.getBalance() && vendingMachineData.get(itemKeyChoice).getInventory() >= 1){
				double beforeBalance = balance.getBalance(); //record before balance
				
				balance.updateBalanceAfterPurchase(vendingMachineData.get(itemKeyChoice).getPrice());
				vendingMachineData.get(itemKeyChoice).setInventory();
				
				//log the purchase
				
				VendingMachineItem vMI = vendingMachineData.get(itemKeyChoice);
		    	DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
		    	String dateString2 = dateFormat2.format(new Date()).toString();
		    	
				
				try{
					FileWriter logger = new FileWriter(log,true);
					
					String logData = dateString2 + " " + vMI.getProduct() + " $" + beforeBalance + " $" + balance.getBalance()+ "\n";
					try (PrintWriter pw = new PrintWriter(logger)){
						pw.write(logData);
					}catch(Exception e){
						
					}
					
				}catch (Exception e) {
					
				}
				
				
				System.out.println();
				System.out.println(vendingMachineData.get(itemKeyChoice).getProduct() + ", " + vendingMachineData.get(itemKeyChoice).getPrice() + ", " + balance.displayBalance() + ", " + vendingMachineData.get(itemKeyChoice).getItemMessage());
				System.out.println();
	
				showMenu();
				getInput();
				useInput();
			}
			
		} else {
			
			double remainingBal = balance.getBalance();
			
			balance.formatChange();
			System.out.println(balance.makeChange());
			balance.emptyBalanceToMakeChange();
			

	    	DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
	    	String dateString2 = dateFormat2.format(new Date()).toString();
			
			try{
				FileWriter logger = new FileWriter(log,true);
				
				String logData = dateString2 + " MAKE CHANGE $" +  remainingBal + " $" + balance.getBalance() + "\n";
				try (PrintWriter pw = new PrintWriter(logger)){
					pw.write(logData);
				}catch(Exception e){
					
				}
				
			}catch (Exception e) {
				
			}
			

			Menu m = new Menu(vendingMachineData, log);
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
			run();
			
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

	

