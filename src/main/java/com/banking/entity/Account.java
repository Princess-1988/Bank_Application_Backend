	package com.banking.entity;





import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ACCOUNT")
@Data
@NoArgsConstructor
public class Account {
	
	@Id
	@NotNull(message="Customer Id cant be null")
	@Column(name="CUSTOMER_ID")
	private int customerId;
	
	@NotNull(message="Customer Name cant be null")
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	
	@Column(name="EMAIL")
	private String email;
	
	@NotNull(message="Contact Number cant be null")
	@Column(name="CONTACT_NUMBER")
	private long mobile;
	
	@NotNull(message="Customer Address can't be null")
	@Column(name="CUSTOMER_ADDRESS")	
	private String customerAddress;
	
	@NotNull(message="Customer Zip can't be null")
	@Column(name="CUSTOMER_ZIP")
	private String customerZip;
	
	@NotNull(message="Customer Date of Birth can't be null")
	@Column(name="CUSTOMER_DOB")
	@Past
	private Date customerDOB;
	
	
	@NotNull(message="Account Number can't be null")
	@Column(name="ACCOUNT_NUMBER", unique=true)
	private long accountNumber;
	
	@NotNull(message="Balance can't be null")
	@Column(name="BALANCE")
	private double balance;

}
