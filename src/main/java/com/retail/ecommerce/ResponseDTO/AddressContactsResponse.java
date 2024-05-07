package com.retail.ecommerce.ResponseDTO;

import java.util.List;

public class AddressContactsResponse {
	
	private List<AddressContactResponse> address;

	public List<AddressContactResponse> getAddress() {
		return address;
	}

	public void setAddress(List<AddressContactResponse> address) {
		this.address = address;
	}

	

	public AddressContactsResponse(List<AddressContactResponse> address) {
		super();
		this.address = address;
	}

	public AddressContactsResponse() {
		// TODO Auto-generated constructor stub
	}
	
	

}
