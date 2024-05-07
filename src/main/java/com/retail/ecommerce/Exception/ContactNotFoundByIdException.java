package com.retail.ecommerce.Exception;

public class ContactNotFoundByIdException extends RuntimeException{
	
	private String message;

	public String getMessage() {
		return message;
	}

	public ContactNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	
	
	
	

}
