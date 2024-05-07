package com.retail.ecommerce.Model;

import com.retail.ecommerce.Enum.Priority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contactId;
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
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public Contact(int contactId, String name, long phoneNumber, String email, Priority priority) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.priority = priority;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
