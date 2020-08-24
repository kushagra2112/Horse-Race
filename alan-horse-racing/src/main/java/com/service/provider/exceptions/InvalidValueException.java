package com.service.provider.exceptions;

import org.springframework.stereotype.Service;

@Service
public class InvalidValueException extends RuntimeException{
	
	static final long serialVersionUID = 1 ; 
	
	public InvalidValueException() {
		
	}

	public InvalidValueException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidValueException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidValueException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
