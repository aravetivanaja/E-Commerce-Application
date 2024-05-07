package com.retail.ecommerce.Exception;

public class PriorityNotSetException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public PriorityNotSetException(String message) {
		super();
		this.message = message;
	}
	
	
	

}
