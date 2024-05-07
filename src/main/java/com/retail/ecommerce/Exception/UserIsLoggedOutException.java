package com.retail.ecommerce.Exception;

public class UserIsLoggedOutException extends RuntimeException {
	
	private String message;

	public UserIsLoggedOutException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
