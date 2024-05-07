package com.retail.ecommerce.ResponseDTO;

import com.retail.ecommerce.Enum.Priority;

public class UpdateContact {
	
	private int contactId;
	private String name;
	private String email;
	private long phoneNumber;
	private Priority priority;
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public UpdateContact(int contactId, String name, String email, long phoneNumber, Priority priority) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.priority = priority;
	}
	public UpdateContact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
