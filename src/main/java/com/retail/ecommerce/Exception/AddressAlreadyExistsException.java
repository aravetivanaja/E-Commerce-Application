package com.retail.ecommerce.Exception;

public class AddressAlreadyExistsException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public AddressAlreadyExistsException(String message) {
		super();
		this.message = message;
	}
	
}
