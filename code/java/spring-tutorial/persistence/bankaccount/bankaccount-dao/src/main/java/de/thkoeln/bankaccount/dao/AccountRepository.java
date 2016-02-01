package de.thkoeln.bankaccount.dao;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends 
  CrudRepository<Account, Long>,AccountRepositoryExtended {

	public Account findByAccountNumber(int accountNumber);
}
