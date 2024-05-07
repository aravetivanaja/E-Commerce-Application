package com.retail.ecommerce.Exception;

public class OtpExpiredException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public OtpExpiredException(String message) {
		super();
		this.message = message;
	}
	

}
