package com.banking.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Integer> {
	
	//finderMethods
	public Account findByaccountNumber(long accountNumber);
	
	
	@Query("select a.balance from Account a where a.customerId = ?1")
	public double checkBalance(int customerId);
	
	//native query
	
	@Query(value = "select * from ACCOUNT where BALANCE <= ?1 ",nativeQuery = true)
	public List<Account> getAccountByBalanceAmount(double balance);

}
