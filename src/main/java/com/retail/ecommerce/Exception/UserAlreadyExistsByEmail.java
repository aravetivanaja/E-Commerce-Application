package com.retail.ecommerce.Exception;

public class UserAlreadyExistsByEmail extends RuntimeException {
	
	private String message;

	public UserAlreadyExistsByEmail(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
