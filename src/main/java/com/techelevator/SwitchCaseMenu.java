package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

public class SwitchCaseMenu {//class open
	public static Scanner inputScanner = new Scanner(System.in);
	public static Scanner purchaseScanner = new Scanner(System.in);
	public static int totalMoney = 0;//this stores the money each time user feeds money
	
	public static void main(String[] args) throws FileNotFoundException {//main open
	boolean exit = false;
	boolean purchExit = false;
		System.out.println("*********************");
		System.out.println("** Vendo-Matic 800 **");
		System.out.println("**  Umbrella Corp  **");
		System.out.println("*********************");	
		
		
		do {//do open
			System.out.println();
			System.out.println();
			System.out.println("***Main Menu***");
			System.out.println();
			System.out.println("[1] Display Vending Machine Options");
	        System.out.println("[2] Purchase");
	        System.out.println("[3] Exit");
	        System.out.println();
	        System.out.print("Please choose an option>>> ");
	        String userChoice = inputScanner.nextLine();
	        System.out.println();
	        switch (userChoice) {
	        
	    //When the customer selects "(1) Display Vending Machine Items", they're presented 
		//with a list of all items in the vending machine with its quantity remaining:   
	        
			case "1": 
				System.out.flush();
	        	Scanner myScanner = new Scanner(new File("vendingmachine.csv")); //Sam to add new sheet for updatable inventory 
	        	myScanner.useDelimiter("|");  
	        	while (myScanner.hasNext()) {  
	         
	        	System.out.print(myScanner.next());  
	        	System.out.flush();
	        	}   
	        myScanner.close();
				break;
				
		//When the customer selects "(2) Purchase", they are guided through the purchasing
		//process menu:	
				
			case "2":
				do {//purchase do open
					System.out.println("***Purchase Menu***");
					System.out.println();
					System.out.println("[4] Feed Money");
			        System.out.println("[5] Select Product");
			        System.out.println("[6] Finish Transaction");
			        System.out.println();
			        System.out.print("Please choose >>> ");
			        exit = false;
			        String userPurchase = inputScanner.nextLine();
				switch (userPurchase) {// purchase switch open
				
			//	Selecting "(1) Feed Money" allows the customer to repeatedly feed money into the
			//  machine in valid, whole dollar amounts (e.g. $1, $2, $5, or $10).
				
					case "4":
						System.out.println();
						System.out.print("please enter the amount of money to be added(in whole dollars)>>> ");
						int moneyAdded = purchaseScanner.nextInt();
		        	
						while(moneyAdded >= 0) {//while open
							totalMoney += moneyAdded;
							System.out.println("Current Money Provided: $" + totalMoney);
							System.out.println();
							break;
		        	}//while close
					break;
					
			//Selecting "(2) Select Product" allows the customer to select a product to
			//purchase.		
					
					case "5":
					
					//enter code (SAM)
					
					break;
					
			//Selecting "(3) Finish Transaction" allows the customer to complete the
			//transaction and receive any remaining change.		
					
					case "6":
						System.out.println("return to Main Menu");
						System.out.println();
						purchExit = true;
					break;
					
					default:
						System.out.println("Invalid choice, please try again");
						System.out.println();
					break;
				}//purchase switch close
				
				}while(!purchExit);//purchase do close
			break;
		//main menu closes and the program exits
				
			case "3":
				//input code
				System.out.println("Thank you for using the Vendo-matic 800 \nHave a nice day :)");
				System.exit(0);
				exit = true;
			default:
				System.out.println("Invalid choice, please try again");
				System.out.println();
				
				break;
			}
		}while(!exit);//do close
		
	}//main close

}//class close
