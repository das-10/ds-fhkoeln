package de.thkoeln.bankaccount.dao;

import java.util.Map;

public interface AccountRepositoryExtended {
  
	 public void deposit(Map<String, String> transactionData);
	 public void cashOut(Map<String, String> transactionData);
}
