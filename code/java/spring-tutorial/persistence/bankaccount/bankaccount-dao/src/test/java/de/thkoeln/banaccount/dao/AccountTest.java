package de.thkoeln.banaccount.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.thkoeln.bankaccount.dao.Account;

public class AccountTest{
	
	private Account account;

	@Before
	public void setUp() throws Exception {
		account = new Account(100284, 10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStandShoud30() {
		account.deposit(20);
		assertEquals(30, account.getStand());
	}
	
	@Test
	public void testStandShould0(){
		account.cashOut(10);
		assertEquals(0, account.getStand());
	}
	
	@Test
	public void testStandShould5(){
		account.cashOut(5);
		assertEquals(5, account.getStand());
	}
	
	@Test
	public void testCashOutShouldFail(){
		assertFalse(account.cashOut(30));
	}
}
