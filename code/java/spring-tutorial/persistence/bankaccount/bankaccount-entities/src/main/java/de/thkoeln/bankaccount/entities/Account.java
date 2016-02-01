package de.thkoeln.bankaccount.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable=false, unique=true)
	private int accountNumber;
	@Column(nullable=false)
	private int stand;
	private Customer customer;
	
	
	protected Account() {
		super();
	}

	public Account(int accountNumber, int stand) {
		super();
		this.accountNumber = accountNumber;
		this.stand = stand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getStand() {
		return stand;
	}

	public void setStand(int stand) {
		this.stand = stand;
	}
	
	
@ManyToOne public Customer getCustomer() {
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
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", stand=" + stand + "]";
	}
	
}
