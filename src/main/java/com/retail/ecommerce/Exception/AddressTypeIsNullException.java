package com.retail.ecommerce.Exception;

public class AddressTypeIsNullException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public AddressTypeIsNullException(String message) {
		super();
		this.message = message;
	}
	
	

}
