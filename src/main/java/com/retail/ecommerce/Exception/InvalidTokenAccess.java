package com.retail.ecommerce.Exception;

public class InvalidTokenAccess extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public InvalidTokenAccess(String message) {
		super();
		this.message = message;
	}


	
	

}
