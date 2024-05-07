package com.retail.ecommerce.Exception;

public class UserIsNotLoginException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public UserIsNotLoginException(String message) {
		super();
		this.message = message;
	}
	
	

}
