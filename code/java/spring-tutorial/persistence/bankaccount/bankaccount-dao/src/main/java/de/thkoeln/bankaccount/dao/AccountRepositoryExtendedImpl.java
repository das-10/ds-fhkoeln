package de.thkoeln.bankaccount.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AccountRepositoryExtendedImpl implements AccountRepositoryExtended {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	@Transactional
	public void deposit(Map<String, String> transactionData) {
		int transactionAmount =Integer.parseInt( transactionData.get("amount"));
		int accountNUmber = Integer.parseInt(transactionData.get("accountNumber"));
		Account account = accountRepository.findByAccountNumber(accountNUmber);
		account.deposit(transactionAmount);
		accountRepository.save(account);
	}

	@Override
	@Transactional 
	public void cashOut(Map<String, String> transactionData) {
		int transactionAmount =Integer.parseInt( transactionData.get("amount"));
		int accountNUmber = Integer.parseInt(transactionData.get("accountNumber"));
		Account account = accountRepository.findByAccountNumber(accountNUmber);
		account.cashOut(transactionAmount);
		accountRepository.save(account);
	}

}
