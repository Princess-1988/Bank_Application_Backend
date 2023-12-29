package com.banking.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.banking.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
	
	@Query(value="select * from Transaction_History  where customer_id = ?1",nativeQuery=true)
	public List<Transaction> getTransactionsByCustomerId(int customerId);

	//native query
	
		@Query(value = "select * from Transaction_History where TRANSACTION_AMOUNT >= ?1 ",nativeQuery = true)
		public List<Transaction> getTransactionsByTransactionAmount(double transactionAmount);
}
