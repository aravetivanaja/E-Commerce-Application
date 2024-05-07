package com.retail.ecommerce.ResponseDTO;

import com.retail.ecommerce.Enum.AddressType;

import jakarta.validation.constraints.Pattern;

public class AddressResponse {
	
	private int addressId;
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
	private String streetAddress;
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
	private String streetAddressAdditional;
	@Pattern(regexp="^[a-zA-Z\\S]+$")
	private String city;
	@Pattern(regexp="^[a-zA-Z\\S]+$")
	private String state;
	@Pattern(regexp="^[0-9]+$")
	private int pincode;
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
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public AddressResponse(int addressId, @Pattern(regexp = "^[a-zA-Z0-9\\s]+$") String streetAddress,
			@Pattern(regexp = "^[a-zA-Z0-9\\s]+$") String streetAddressAdditional,
			@Pattern(regexp = "^[a-zA-Z\\S]+$") String city, @Pattern(regexp = "^[a-zA-Z\\S]+$") String state,
			@Pattern(regexp = "^[0-9]+$") int pincode, AddressType addressType) {
		super();
		this.addressId = addressId;
		this.streetAddress = streetAddress;
		this.streetAddressAdditional = streetAddressAdditional;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.addressType = addressType;
	}
	public AddressResponse() {
		// TODO Auto-generated constructor stub
	}
	
	

}
