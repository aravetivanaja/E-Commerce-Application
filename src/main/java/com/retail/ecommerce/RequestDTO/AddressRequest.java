package com.retail.ecommerce.RequestDTO;

import com.retail.ecommerce.Enum.AddressType;

import jakarta.validation.constraints.Pattern;

public class AddressRequest {
	
	private int addreeId;
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
	private String streetAddress;
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
	private String streetAddressAdditional;
	@Pattern(regexp="^[a-zA-Z\\S]+$")
	private String city;
	@Pattern(regexp="^[a-zA-Z\\S]+$")
	private String state;
	//@Pattern(regexp="^[0-9]+$")
	private int pincode;
	
	private String country;
	private AddressType addressType;
	
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getStreetAddressAdditional() {
		return streetAddressAdditional;
	}
	public void setStreetAddressAdditional(String streetAddressAdditional) {
		this.streetAddressAdditional = streetAddressAdditional;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	public AddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	
	public int getAddreeId() {
		return addreeId;
	}
	public void setAddreeId(int addreeId) {
		this.addreeId = addreeId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public AddressRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddressRequest(int addreeId, @Pattern(regexp = "^[a-zA-Z0-9\\s]+$") String streetAddress,
			@Pattern(regexp = "^[a-zA-Z0-9\\s]+$") String streetAddressAdditional,
			@Pattern(regexp = "^[a-zA-Z\\S]+$") String city, @Pattern(regexp = "^[a-zA-Z\\S]+$") String state,
			@Pattern(regexp = "^[0-9]+$") int pincode, String country, AddressType addressType) {
		super();
		this.addreeId = addreeId;
		this.streetAddress = streetAddress;
		this.streetAddressAdditional = streetAddressAdditional;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
		this.addressType = addressType;
	}
	
	
	

}
