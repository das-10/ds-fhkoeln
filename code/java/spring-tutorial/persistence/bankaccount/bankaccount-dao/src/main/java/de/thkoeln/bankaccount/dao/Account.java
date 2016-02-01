package de.thkoeln.bankaccount.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Account extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 6773234118996367478L;
	@Column(nullable=false, unique=true)
	private int accountNumber;
	@Column(nullable=false)
	private int stand;
	@ManyToOne
	private Customer customer;
	
	
	protected Account() {
		super();
	}

	public Account(int accountNumber, int stand) {
		super();
		this.accountNumber = accountNumber;
		this.stand = stand;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getStand() {
		return stand;
	}

	public void setStand(int stand) {
		this.stand = stand;
	}
	
	
 public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void deposit(int amount){
		this.stand = this.stand + amount;
	}
	
	public boolean cashOut(int amount){
		boolean retval=false;
		if (this.stand >= amount){
			 this.stand = this.stand - amount;
			 retval = true;
		}
		return retval;
	}

	@Override
	public String toString() {
		return "Account [id=" +this.getId() + ", accountNumber=" + accountNumber + ", stand=" + stand + "]";
	}
	
}
