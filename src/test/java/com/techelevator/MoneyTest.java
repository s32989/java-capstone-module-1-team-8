package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	
	private Money moneyTester;
	private int quarter = 25;
	private int dime = 10;
	private int nickel = 5;
	
	
	@Before
	public void refresher() {
		moneyTester = new Money();
	}
	
	@Test
	public void test_feed_money() {
		double input = 12.5;
		double output = input + moneyTester.getBalance();
		moneyTester.feedMoney(input);
		Assert.assertEquals(input, output, 0);
	}
	
	@Test
	public void test_update_balance() {
		double input = 22.73;
		double output = input + moneyTester.getBalance();
		moneyTester.updateBalanceAfterPurchase(input);
		Assert.assertEquals(input, output, 0);
		
	}
	
	@Test
	public void test_format_change() {
		int input = 0;
		double output = moneyTester.getBalance() - input;
		moneyTester.formatChange();
		Assert.assertEquals(input, output, 0);
	}
	
	@Test
	public void test_make_change() {
		String input = "You receive " + quarter + " quarter(s), " + dime + " dime(s), and " + nickel + " nickels in change.";
		String output = moneyTester.makeChange();
		Assert.assertNotEquals(output, input);
	}

}
