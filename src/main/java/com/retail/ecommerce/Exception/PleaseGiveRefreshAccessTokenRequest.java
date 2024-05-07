package com.retail.ecommerce.Exception;

public class PleaseGiveRefreshAccessTokenRequest extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public PleaseGiveRefreshAccessTokenRequest(String message) {
		super();
		this.message = message;
	}
	
	

}
