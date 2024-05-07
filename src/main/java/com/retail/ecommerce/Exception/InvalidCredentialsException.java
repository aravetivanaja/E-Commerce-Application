package com.retail.ecommerce.Exception;

public class InvalidCredentialsException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public InvalidCredentialsException(String message) {
		super();
		this.message = message;
	}
	

}
