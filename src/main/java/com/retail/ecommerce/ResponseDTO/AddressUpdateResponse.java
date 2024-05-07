package com.retail.ecommerce.ResponseDTO;

import com.retail.ecommerce.Enum.AddressType;

public class AddressUpdateResponse {

	private int addressId;
	private String streetAddress;
	private String streetAdressAditional;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private AddressType addressType;
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getStreetAdressAditional() {
		return streetAdressAditional;
	}
	public void setStreetAdressAditional(String streetAdressAditional) {
		this.streetAdressAditional = streetAdressAditional;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public AddressUpdateResponse(int addressId, String streetAddress, String streetAdressAditional, String city,
			String state, String country, int pincode, AddressType addressType) {
		super();
		this.addressId = addressId;
		this.streetAddress = streetAddress;
		this.streetAdressAditional = streetAdressAditional;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.addressType = addressType;
	}
	public AddressUpdateResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
