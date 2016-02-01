package de.thkoeln.bankaccount.entities;

import java.util.Collection;

import javax.persistence.Column;
// stdlib import
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	private static final long serialVersionUID = 5060910864394673463L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String name;
	private Collection<Account> accounts;
	
	protected Customer() {
		super();
	}

	public Customer(String name) {
		super();
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade=javax.persistence.CascadeType.ALL, mappedBy="customer")
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
		return "Customer [id=" + id + ", name=" + name + "]";
	}
	
}
