package com.techelevator;



public class Money {
	
	int billAmountEntered = 0;
	double balance = 0;
	
	
	
	public void feedMoney(double moneyFed) {
		
		balance += moneyFed;
	}
	
	public double getBalance() {
		
		return balance;
	}
	
	public void updateBalanceAfterPurchase(double itemCost) {
		
		balance -= itemCost;	
	}
	
	public String displayBalance() {
		return "Current Money Provided $ " + balance;
	}
	
	
	

}
