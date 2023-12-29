package com.banking.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.banking.entity.Account;
import com.banking.exception.InvalidCustomerIdException;
import com.banking.repository.AccountRepository;
import com.banking.service.AccountService;

import jakarta.validation.Valid;

@RestController 
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/api/v1/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/addNewAccount")
	public Account addNewAccount (@Valid @RequestBody Account account)
	{
		try {
		return this.accountService.addNewAccount(account);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	@PatchMapping("/updateAccount/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody Account account) throws InvalidCustomerIdException
	{
		if(this.accountRepository.findById(id).isPresent())
		{
			return new ResponseEntity<>(this.accountService.updateAccountById(account),HttpStatus.OK);
		}
		else
		{
			throw new InvalidCustomerIdException("Customer Id is not present");
		}
		
	 }
	
	@GetMapping("/getAllAccounts")
	public List<Account> getAllAccounts()
	{
		return this.accountService.getAllAccount();
	}
	
	@GetMapping("/getAccountById/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable int id) throws InvalidCustomerIdException
	{
		
		if(this.accountRepository.findById(id).isPresent())
		{
			return new ResponseEntity<>(this.accountService.getAccountById(id),HttpStatus.OK);
		}
		else
		{
			throw new InvalidCustomerIdException("Customer Id is not present");
		}
	}
		
	@DeleteMapping("/deleteAccount/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable int id)
	{
		HashMap<String, String> response=new HashMap<>();
		if(this.accountRepository.findById(id).isPresent())
		{
			this.accountService.deleteAccount(id);
			response.put("message", "Account deleted successfully");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else
		{
			response.put("message", "Account id not found");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	@GetMapping("/checkBalance/{id}")
	public ResponseEntity<?> checkBalance(@PathVariable int id) throws InvalidCustomerIdException
	{
		if(this.accountRepository.findById(id).isPresent())
		{
			return new ResponseEntity<>(this.accountRepository.checkBalance(id),HttpStatus.OK);
		}
		else
		{
			throw new InvalidCustomerIdException("Customer Id is not present");
		}
	}	
	
	@GetMapping("/getAccountByBalance/{balance}")
	public ResponseEntity<?> getAccountsByBalance(@PathVariable String balance) 
	{
		double balanceAmount=Double.parseDouble(balance);
		return new ResponseEntity<>(this.accountRepository.getAccountByBalanceAmount(balanceAmount),HttpStatus.OK);
	}	
	
	@GetMapping("/getAccountDetailsByAccountNumber")
	public ResponseEntity<?>getAccountDetailsByAccountNumber(@RequestParam("accountNumber") String accountNumber)
	{
		long accountNo=Long.parseLong(accountNumber);
		try {
			return new ResponseEntity<>(this.accountRepository.findByaccountNumber(accountNo),HttpStatus.OK);
		}
		catch (Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
		
	}
	
}
