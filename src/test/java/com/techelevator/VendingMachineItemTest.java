package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class VendingMachineItemTest {
	
	private VendingMachineItem vendItem;
	
	
//	@Before
//	public void helper() {
//		vendItem = new VendingMachineItem(null);
//	}
	
	@Test
	public void constructor_test() {
	String[] input = {"Z7", "chunkos", "2", "pretzels", "yummy"};
	vendItem = new VendingMachineItem(input);
	String output = (vendItem.getProduct());
	Assert.assertEquals(input[1], output);	
	}
	
}
