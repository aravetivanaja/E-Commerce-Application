package com.retail.ecommerce.Model;

import java.util.ArrayList;
import java.util.List;

import com.retail.ecommerce.Enum.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Customer extends User{
	
	
	@OneToMany
	private List<Address> addresses;

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Customer(List<Address> addresses) {
		super();
		this.addresses = addresses;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

}
