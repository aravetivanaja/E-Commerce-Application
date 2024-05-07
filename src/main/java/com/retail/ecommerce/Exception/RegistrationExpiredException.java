package com.retail.ecommerce.Exception;

public class RegistrationExpiredException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public RegistrationExpiredException(String message) {
		super();
		this.message = message;
	}
	
	

}
