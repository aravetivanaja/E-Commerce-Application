package com.retail.ecommerce.Exception;

public class InvalidUserEmailException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public InvalidUserEmailException(String message) {
		super();
		this.message = message;
	}
	
	

}
