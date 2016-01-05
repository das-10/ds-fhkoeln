package de.fhk.jaxws.prakt2.service.bean;

import java.util.ArrayList;
import java.util.List;

import de.fhk.jaxws.prakt2.service.bean.Account;;

public class Customer {
	private String name;
	private ArrayList<Account> accounts;
	
	/**
	 * 
	 * @param name
	 */
	public Customer(String name) {
		this.name = name;
		accounts = new ArrayList<Account>();
	}
	
	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @param account
	 */
	public void addAccount(Account account){
		accounts.add(account);
		
	}
	/**
	 * 
	 * @return the accounts
	 */
	public List<Account> getAccounts(){
		return accounts;
	}
	
}
