package com.retail.ecommerce.Util;

public class MessageModel {
	
	private String to;
	private String subject;
	private String text;
	
	public String getTo() {
		return to;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getText() {
		return text;
	}

	public MessageModel(String to, String subject, String text) {
		super();
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
	
	
	
	

}
