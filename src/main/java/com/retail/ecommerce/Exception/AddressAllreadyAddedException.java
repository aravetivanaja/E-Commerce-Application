package com.retail.ecommerce.Exception;

public class AddressAllreadyAddedException extends RuntimeException {

	
	private String message;

	public String getMessage() {
		return message;
	}

	public AddressAllreadyAddedException(String message) {
		super();
		this.message = message;
	}

	
}
