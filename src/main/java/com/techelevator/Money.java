package com.techelevator;



public class Money {
	
	private final int QUARTER = 25;
	private final int DIME = 10;
	private final int NICKEL = 5;
	private int dollars;
	private double change;
	private double balance = 0.00;
	private double fedMoney;
	
	Money(){
		
	}
	
	public void formatChange() {
		
		dollars = (int) balance;										
		
		change = (double) (balance - dollars);									
	}
	
	public String makeChange() {									//***** will return output later as program develops
		
		
		int quarterAmount = dollars * 4; 		
		int dimeAmount = 0;
		int nickelAmount = 0;
		
		double centsAsDouble = change * 100.00;						//multiplying change value by 100
		
		int cents = (int) Math.round(centsAsDouble);				//rounding up from double format (i.e. 19.999999999 cents goes to 20 cents)				
		
		
		
		quarterAmount += cents / QUARTER;							//how many quarters?
		
		int dimesAndNickelsRemaining = cents % QUARTER;				//is there change left after dividing into quarters?
		
		dimeAmount += dimesAndNickelsRemaining / DIME;				//how many dimes?
		
		int nickelsRemaining = dimesAndNickelsRemaining % DIME;		//is there change left after dividing into dimes?
		
		nickelAmount += nickelsRemaining / NICKEL;					//how many nickels? (there can only ever be one nickel)
		
		return cents + ") You receive " + quarterAmount + " quarter(s), " + dimeAmount + " dime(s), and " + nickelAmount + " nickels in change.";
	}
	
	
	public void feedMoney(double moneyFed) {
		
		balance += moneyFed;
		
		fedMoney = moneyFed;
		
		//System.out.println(moneyFed + " "+ getBalance()); 		//the argument variable and the getBalance method will let us log how we want
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
	
	public double fedMoney() {
		return fedMoney;
	}
	
	

}
