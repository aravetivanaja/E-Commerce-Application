package com.retail.ecommerce.Exception;

public class TokenIsBlockedException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public TokenIsBlockedException(String message) {
		super();
		this.message = message;
	}

	
	

}
