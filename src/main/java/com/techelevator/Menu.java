package com.techelevator;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class Menu {
	private static String userChoice = "";
	private static Scanner userInput = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("*********************");
		System.out.println("** Vendo-Matic 800 **");
		System.out.println("**  Umbrella Corp  **");
		System.out.println("*********************");	
		
		System.out.println();
		
	while(true) {
		System.out.println("***Main Menu***");
		System.out.println();
		System.out.println("[1] Display Vending Machine Options");
        System.out.println("[2] Purchase");
        System.out.println("[3] Exit");
        System.out.println();
        System.out.print("Please choose an option>>> ");
        String userChoice = userInput.nextLine();

        System.out.println();
        if((userChoice.equals("1"))){
        	System.out.flush();
        	Scanner myScanner = new Scanner(new File("vendingmachine.csv")); //bob to add new sheet for updatable inventory 
        	myScanner.useDelimiter("|");  
        	while (myScanner.hasNext()) {  
         
        	System.out.print(myScanner.next());  
        	System.out.flush();
        	}   
        myScanner.close();
        	
        }else if(userChoice.equals("2")) {
        	;
        	System.out.println("figure out how to get to purchase menu");
        	System.out.println();
        	
        	
        }else if(userChoice.equals("3")) {
        	System.out.println("Thank you for using the Vendo-Matic 800 \nHave a nice day :)");
        	System.out.flush();
        	System.exit(0);
        	
        }
		
	}
	}
}

