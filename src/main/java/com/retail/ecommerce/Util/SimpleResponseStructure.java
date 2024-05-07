package com.retail.ecommerce.Util;

import org.springframework.stereotype.Component;

@Component
public class SimpleResponseStructure {
	
	private int statuscode;
	private String message;
	
	public int getStatuscode() {
		return statuscode;
	}
	public SimpleResponseStructure setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public SimpleResponseStructure setMessage(String message) {
		this.message = message;
		return this;
	}
	
	

}
