package com.retail.ecommerce.RequestDTO;

import com.retail.ecommerce.Enum.Priority;

public class ContactRequest {
	
	private String name;
	private long phoneNumber;
	private String email;
	private Priority priority;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public ContactRequest(String name, long phoneNumber, String email, Priority priority) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.priority = priority;
	}
	public ContactRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
