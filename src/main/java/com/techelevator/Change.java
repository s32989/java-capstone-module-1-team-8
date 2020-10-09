package com.techelevator;

public class Change extends Money {
	
	final int QUARTER = 25;
	final int DIME = 10;
	final int NICKEL = 5;
	int dollars;
	double change;
	int quarters;
	int dimes;
	int nickels;
	

	public void formatChange() {
		
		double bal = getBalance();									//get balance from the parent class
		
		dollars = (int) bal;										//change this to dollars casting off decimal (These by default will be given back as 4 quarters in the makeChange method)
		
		change = bal - dollars;										//get the spare change amount
		
	}
	
	public String makeChange() {									//***** will return output later as program develops
		
		int quarterAmount = dollars * 4; 		
		int dimeAmount = 0;
		int nickelAmount = 0;
		
		int cents = (int)(change * 100.00);							//cast the double change to an integer to cents amount
		
		quarterAmount += cents / QUARTER;							//how many quarters?
		
		int dimesAndNickelsRemaining = cents % QUARTER;				//is there change left after dividing into quarters?
		
		dimeAmount += dimesAndNickelsRemaining / DIME;				//how many dimes?
		
		int nickelsRemaining = dimesAndNickelsRemaining % DIME;		//is there change left after dividing into dimes?
		
		nickelAmount += nickelsRemaining / NICKEL;					//how many nickels?
		
		return "You receive " + quarterAmount + " quarter(s), " + dimeAmount + " dime(s), and " + nickelAmount + " nickels in change.";
	}
	
	
	

}
