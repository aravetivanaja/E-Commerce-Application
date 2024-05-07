package com.retail.ecommerce.Exception;

public class UserNotLoggedInException extends RuntimeException {
	
	private String message;

	public UserNotLoggedInException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
