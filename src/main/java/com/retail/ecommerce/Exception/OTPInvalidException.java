package com.retail.ecommerce.Exception;

public class OTPInvalidException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public OTPInvalidException(String message) {
		super();
		this.message = message;
	}
	
	
}
