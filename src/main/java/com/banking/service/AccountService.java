package com.banking.service;

import java.util.List;

import com.banking.entity.Account;

public interface AccountService {
	
	Account addNewAccount(Account account);
	Account updateAccountById (Account account);
	
	List<Account> getAllAccount ();
	Account getAccountById(int id);
	
	void deleteAccount(int id);
	
	

}
