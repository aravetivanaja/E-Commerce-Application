package com.retail.ecommerce.Exception;

public class InvalidUserRoleException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public InvalidUserRoleException(String message) {
		super();
		this.message = message;
	}
	

}
