package com.techelevator;

public class VendingMachineItem {
	
	private int inventory = 5;
	private String product;
	private double price;
	private String productType;
	private String itemKey;
	
	
	
	public VendingMachineItem(String[] productInfo) {
		this.itemKey = productInfo[0];
		this.product = productInfo[1];								///just using the format given in vendingmachine.csv to assign elements of string array to their appropriate instance variables
		this.price = Double.parseDouble(productInfo[2]);
		this.productType = productInfo[3];
		
	}

	public String getProduct() {
		return product;
	}

	public double getPrice() {
		return price;
	}

	public String getProductType() {
		return productType;
	}
	
	public int getInventory() {
		return inventory;
	}
	
	public void setInventory(int deductMe) {
		inventory -= deductMe;
	}
	/*@Override
	public String toString() {
		return this.itemKey + ": " + this.product + " " + this.price + " " + this.productType;
	}*/
	
	

}
