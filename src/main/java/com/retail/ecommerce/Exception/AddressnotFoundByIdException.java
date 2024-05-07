package com.retail.ecommerce.Exception;

public class AddressnotFoundByIdException extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}

	public AddressnotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	
}
