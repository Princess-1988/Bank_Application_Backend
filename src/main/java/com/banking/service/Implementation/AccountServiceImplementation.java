package com.banking.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.entity.Account;
import com.banking.repository.AccountRepository;
import com.banking.service.AccountService;

@Service
public class AccountServiceImplementation implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account addNewAccount(Account account) {
	
		return this.accountRepository.save(account);
	}

	@Override
	public Account updateAccountById(Account account) {
		
		return this.accountRepository.save(account);
		
	}

	@Override
	public List<Account> getAllAccount() {
		
		return this.accountRepository.findAll();
		
	}

	@Override
	public Account getAccountById(int id) {
		
		return this.accountRepository.findById(id).get();
	}

	@Override
	public void deleteAccount(int id) {
		
		this.accountRepository.deleteById(id);
		
	}

}
