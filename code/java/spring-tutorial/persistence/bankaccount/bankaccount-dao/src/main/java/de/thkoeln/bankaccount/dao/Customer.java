package de.thkoeln.bankaccount.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.io.Serializable;
import java.util.Collection;

@Entity
public class Customer extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = -7747919968778335729L;
	@Column(nullable=false)
	private String name;
	@OneToMany(cascade=javax.persistence.CascadeType.ALL, mappedBy="customer")
	private Collection<Account> accounts;
	
	protected Customer() {
		super();
	}

	public Customer(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccount(Account account){
		this.getAccounts().add(account);
	}

	@Override
	public String toString() {
		return "Customer [id=" + this.getId() + ", name=" + name + "]";
	}
	
}
