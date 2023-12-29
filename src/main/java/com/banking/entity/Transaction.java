package com.banking.entity;
import java.sql.Date;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TRANSACTION_HISTORY")
@Data
@NoArgsConstructor
public class Transaction {

	@Id
	@NotNull(message="Transaction Id cant be null")
	@Column(name="TRANSACTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID", nullable=false)	
	private Account caccount;
	
	@ManyToOne (fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="FROM_ACCOUNT", referencedColumnName = "ACCOUNT_NUMBER", nullable=false)	
	private Account faccount;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL) 
	@JoinColumn(name="TO_ACCOUNT", referencedColumnName = "ACCOUNT_NUMBER", nullable=false)	
	private Account taccount;
	
	@NotNull(message="Transaction Amount can't be null")
	@Column(name="TRANSACTION_AMOUNT")
	private double transactionAmount;
	
	@NotNull(message="Transaction Type can't be null")
	@Column(name="TRANSACTION_TYPE")
	private String transactionType;
	
	@NotNull(message="Transaction Date can't be null")
	@Column(name="TRANSACTION_DATE")
	@Past
	private Date transactionDate;
	
	//@ManyToOne (fetch=FetchType.EAGER)
	//@JoinColumn(name="CUSTOMER_BALANCE", referencedColumnName = "BALANCE", nullable=false)	
	//private Account baccount;
	
}
