package com.retail.ecommerce.Exception;

public class AddressLimitException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public AddressLimitException(String message) {
		super();
		this.message = message;
	}
	
	

}
