package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PurchaseMenu {
	private String userChoice = "";
	private static Scanner userInput = new Scanner(System.in);
	public static int sum = 0;


	public int getSum() {
		return sum;
	}
	public String getUserChoice(){
		return userChoice;
	}
	public Scanner getUserInput() {
		return userInput;
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("*********************");
		System.out.println("** Vendo-Matic 800 **");
		System.out.println("**  Umbrella Corp  **");
		System.out.println("*********************");	
		
		System.out.println();
		
	while(true) {
		System.out.println("***Purchase Menu***");
		System.out.println();
		System.out.println("[1] Feed Money");
        System.out.println("[2] Select Product");
        System.out.println("[3] Finish Transaction");
        System.out.println();
        System.out.print("Please choose >>> ");
        String userChoice = userInput.nextLine();

        
        
        if((userChoice.equals("1"))){
        	
        	System.out.print("please enter the amount of money to be added(in whole dollars)>>> ");
        	int moneyAdded = userInput.nextInt();
        	while(moneyAdded >= 0) {
        	sum += moneyAdded;
        	
        	System.out.println("Current Money Provided: $" + sum);
        	System.out.println();
       
        	break;
			}
        	
        	//Bob's code
       }else if(userChoice.equals("2")) {
        	Scanner myScanner = new Scanner(new File("vendingmachine.csv"));  
        	myScanner.useDelimiter("|");  
        	while (myScanner.hasNext()) {  
         
        	System.out.print(myScanner.next());  
        	System.out.flush();
        	}   
        myScanner.close();
        
		
        	//Sam's code
        }else if(userChoice.equals("3")) {
        	System.out.println("Returning to main menu ");
        	System.out.flush();
        	
        
        }
		
	}
	}
}

	

