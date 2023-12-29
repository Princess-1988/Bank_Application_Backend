package com.banking.exception;

import java.util.Date;
import java.util.HashMap;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidCustomerIdException.class)
	public ResponseEntity<?> InvalidCustomerIdExceptionHandler(InvalidCustomerIdException ex,WebRequest request)
	{
		HashMap<String, String> response=new HashMap<>();
		
		response.put("date", String.valueOf(new Date()));
		response.put("reason", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidTransactionIdException.class)
	public ResponseEntity<?> InvalidTransactionIdExceptionHandler(InvalidTransactionIdException ex,WebRequest request)
	{
		HashMap<String, String> response=new HashMap<>();
		
		response.put("date", String.valueOf(new Date()));
		response.put("reason", ex.getMessage());
		response.put("description", request.getDescription(false));
		
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	

}
