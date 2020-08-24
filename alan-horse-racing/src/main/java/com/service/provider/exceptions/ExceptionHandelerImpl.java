package com.service.provider.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.service.provider.model.ExceptionHandeler;

@Service
@ControllerAdvice
public class ExceptionHandelerImpl {
	
	public ExceptionHandelerImpl() {
		
		
	}
	
	@Autowired
	@ExceptionHandler
	public ResponseEntity<ExceptionHandeler> handleException(InvalidValueException exc){
		
		ExceptionHandeler error = new ExceptionHandeler();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setErrorMsg(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<ExceptionHandeler> handleException(Exception exc) {
		
		ExceptionHandeler error = new ExceptionHandeler();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setErrorMsg(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	
	}
	

}
