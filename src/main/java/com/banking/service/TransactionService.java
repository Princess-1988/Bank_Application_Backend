package com.banking.service;

import java.sql.Date;
import java.util.List;

import com.banking.entity.Transaction;

public interface TransactionService {
	
	//Transaction addNewTransaction(double fundAmount,Date fundSQlDate,String tranferType,int custId,long daccount, long baccount);
	Transaction addNewTransaction(double fundAmount,String tranferType,long daccount, long baccount);
	Transaction addNewTransactionDepositWithdrawal(double fundAmount,String tranferType,long daccount);
	
	List<Transaction> getAllTransaction();
	
	
	

}
