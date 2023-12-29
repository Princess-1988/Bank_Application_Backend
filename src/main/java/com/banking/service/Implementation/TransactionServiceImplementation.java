package com.banking.service.Implementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.entity.Account;
import com.banking.entity.Transaction;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import com.banking.service.TransactionService;

@Service
public class TransactionServiceImplementation implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	

	@Override
	public List<Transaction> getAllTransaction() {
		
		return this.transactionRepository.findAll();
	}

	

	@Override
	public Transaction addNewTransaction(double fundAmount, String tranferType, long daccount, long baccount)
	{
		Transaction transaction = null;
		String fundTypeTransfer =new String("Deposit");
		try {
			transaction = new Transaction();
			Account dep =this.accountRepository.findByaccountNumber(daccount);
			int custId=dep.getCustomerId();
			long millis=System.currentTimeMillis();  
			java.sql.Date fundSQlDate = new java.sql.Date(millis);   
			
			transaction.setCaccount(this.accountRepository.findById(custId).get());
			transaction.setFaccount(this.accountRepository.findByaccountNumber(daccount));
			transaction.setTaccount(this.accountRepository.findByaccountNumber(baccount));
			transaction.setTransactionAmount(fundAmount);
			transaction.setTransactionDate(fundSQlDate);
			transaction.setTransactionType(tranferType);		
		
		}
		catch(Exception ex)
		{
			System.out.println("Error in Transaction");
			ex.printStackTrace();
		}		
		if(transaction.getTransactionType().equals(fundTypeTransfer))
		{
			try {
				if(transaction.getFaccount().getBalance()>=fundAmount&&fundAmount!=0)
				{
				
				Account d=this.accountRepository.findByaccountNumber(daccount);
				Account b=this.accountRepository.findByaccountNumber(baccount);
				double updatedDepBalance=transaction.getFaccount().getBalance()-fundAmount;
				double updatedBorBalance = transaction.getTaccount().getBalance()+fundAmount;
				d.setBalance(updatedDepBalance);
				b.setBalance(updatedBorBalance);				
				transaction.setCaccount(d);	
				transaction.setFaccount(d);
				transaction.setTaccount(b);
				
				return this.transactionRepository.save(transaction);
				}
				
			}
			catch (Exception  ex)
			{
				System.out.println("No Amount to Transfer");
				transaction=null;
				ex.printStackTrace();
			}	
		}
			return transaction;
	}

	@Override
	public Transaction addNewTransactionDepositWithdrawal(double fundAmount, String tranferType, long daccount) {
		Transaction transaction = null;
		
			transaction = new Transaction();
			Account dep =this.accountRepository.findByaccountNumber(daccount);
			int custId=dep.getCustomerId();
			long millis=System.currentTimeMillis();  
			java.sql.Date fundSQlDate = new java.sql.Date(millis);   
			
			transaction.setCaccount(this.accountRepository.findById(custId).get());
			transaction.setFaccount(this.accountRepository.findByaccountNumber(daccount));
			transaction.setTaccount(this.accountRepository.findByaccountNumber(daccount));;
			transaction.setTransactionAmount(fundAmount);
			transaction.setTransactionDate(fundSQlDate);
			transaction.setTransactionType(tranferType);		
			String FundTypeDeposit = new String("Deposit");
			String FundTypeWithdrawal=new String("Withdrawal");
 			
		if(transaction.getTransactionType().equals(FundTypeDeposit))
		{
			
			try {
				if(fundAmount!=0.0)
				{
					
				 	Account d=this.accountRepository.findByaccountNumber(daccount);							
					double updatedDepBalance=transaction.getFaccount().getBalance()+fundAmount;
					d.setBalance(updatedDepBalance);
						//transaction.setCaccount(d);					
						
						return this.transactionRepository.save(transaction);
								
				}
			}
			catch(Exception ex)
				{
						System.out.println("No Amount to Transfer");
						transaction=null;
						
				}	
		}
				
			
		 if(transaction.getTransactionType().equals(FundTypeWithdrawal))
		{
			 System.out.println(transaction.getTransactionType()=="Withdrawal");
			try {
				if(!(transaction.getFaccount().getBalance()<fundAmount))
				{
			
				Account d=this.accountRepository.findByaccountNumber(daccount);				
				double updatedDepBalance=transaction.getFaccount().getBalance()-fundAmount;
				d.setBalance(updatedDepBalance);
				//transaction.setCaccount(d);	
				
				return this.transactionRepository.save(transaction);
				}
				
			}
			catch (Exception  ex)
			{
				System.out.println("No Amount to Transfer");
				transaction=null;
				ex.printStackTrace();
			}	
		}
		
			return transaction;
	}



	

	
	
}

