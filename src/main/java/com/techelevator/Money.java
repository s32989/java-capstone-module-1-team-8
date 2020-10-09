package com.techelevator;



public class Money {
	
	private final int QUARTER = 25;
	private final int DIME = 10;
	private final int NICKEL = 5;
	private int dollars;
	private double change;
	private double balance = 0;
	
	Money(){
		
	}
	
	public void formatChange() {
		
		dollars = (int) balance;										
		
		change = balance - dollars;									
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
		
		nickelAmount += nickelsRemaining / NICKEL;					//how many nickels? (there can only ever be one nickel)
		
		return "You receive " + quarterAmount + " quarter(s), " + dimeAmount + " dime(s), and " + nickelAmount + " nickels in change.";
	}
	
	
	public void feedMoney(double moneyFed) {
		
		balance += moneyFed;
	}
	
	public double getBalance() {
		
		return balance;
	}
	
	public void emptyBalanceToMakeChange() {
		this.balance = 0.00;
	}
	
	public void updateBalanceAfterPurchase(double itemCost) {
		
		balance -= itemCost;	
	}
	
	public String displayBalance() {
		return "Current Money Provided $ " + balance;
	}
	
	
	

}
