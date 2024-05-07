package com.retail.ecommerce.Exception;

public class ContactsFilledException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public ContactsFilledException(String message) {
		super();
		this.message = message;
	}
	
	

}
