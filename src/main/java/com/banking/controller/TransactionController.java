package com.banking.controller;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.entity.Transaction;
import com.banking.exception.InvalidCustomerIdException;

import com.banking.repository.TransactionRepository;
import com.banking.service.TransactionService;



@RestController 
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/api/v1/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
		

	
	@PostMapping("/addNewTransaction")
	public ResponseEntity<?>  addNewTransaction (@RequestParam("transaction_amount") String transaction_amount,@RequestParam("transaction_type") String transaction_type, @RequestParam("from_account") String from_account, @RequestParam("to_account") String to_account) throws Exception
	{
		
		Long depAccount = null;
		Long borAccount = null;
		double fundAmount = 0 ;
		String fundType = null;
	
		depAccount = Long.parseLong(from_account);
		borAccount = Long.parseLong(to_account);
		fundAmount = Double.parseDouble(transaction_amount);
		fundType = transaction_type;
		System.out.println("Post"+depAccount + borAccount + fundAmount + fundType);
		
		HashMap<String, String> response=new HashMap<>();
		Transaction transaction=this.transactionService.addNewTransaction(fundAmount,fundType,depAccount,borAccount);
		if(this.transactionRepository.findById(transaction.getTransactionId()).isPresent())
		{
			
			response.put("message", "Transaction, successfully done");
			return new ResponseEntity<> (transaction,HttpStatus.OK);
		}	
		else
		{
			response.put("message", "Transaction Failed");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	
	@PostMapping("/addNewTransactionDepositWithdrawal")
	public ResponseEntity<?> addNewTransactionDepositWithdrawal (@RequestParam("transaction_amount") String transaction_amount,@RequestParam("transaction_type") String transaction_type,@RequestParam("from_account") String from_account)throws Exception
	{
		
		Long depAccount = null;
		double fundAmount = 0 ;
		String fundType = null;
	
		depAccount = Long.parseLong(from_account);
		fundAmount = Double.parseDouble(transaction_amount);
		fundType = transaction_type;
		
		
		HashMap<String, String> response=new HashMap<>();
		Transaction transaction=this.transactionService.addNewTransactionDepositWithdrawal(fundAmount,fundType,depAccount);
		if(this.transactionRepository.findById(transaction.getTransactionId()).isPresent())
		{
			
			response.put("message", "Transaction, successfully done");
			return new ResponseEntity<> (transaction,HttpStatus.OK);
		}	
		else
		{
			response.put("message", "Transaction Failed");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
	}
	@GetMapping("/getAllTransactions")
	public List<Transaction> getAllTransaction()
	{
		return this.transactionService.getAllTransaction();
	}
	

	
	
	
	@GetMapping("/transactionById/{id}")
	public ResponseEntity<?> transactionById(@PathVariable int id) throws InvalidCustomerIdException
	{
		if(this.transactionRepository.findById(id).isPresent())
		{
			return new ResponseEntity<>(this.transactionRepository.getTransactionsByCustomerId(id),HttpStatus.OK);
		}
		else
		{
			throw new InvalidCustomerIdException("Customer Id is not present");
		}
	}	
	
	@GetMapping("/getTransactionByTransactionAmount/{amount}")
	public ResponseEntity<?> getAccountsByBalance(@PathVariable double amount) 
	{
		
		return new ResponseEntity<>(this.transactionRepository.getTransactionsByTransactionAmount(amount),HttpStatus.OK);
	}	
	

}
