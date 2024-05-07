package com.retail.ecommerce.Model;

import java.util.List;

import com.retail.ecommerce.Enum.AddressType;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int AddressId;
	private String streetAddress;
	private String streetAddressAdditional;
	private String city;
	private String state;
	private int pincode;
	private String country;
	private AddressType addressType;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Contact> contact;

	
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
	public Address() {
		super();
		
	}
	public int getAddressId() {
		return AddressId;
	}
	public void setAddressId(int addressId) {
		AddressId = addressId;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	public List<Contact> getContact() {
		return contact;
	}
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	
	public Address(int addressId, String streetAddress, String streetAddressAdditional, String city, String state,
			int pincode, String country, AddressType addressType) {
		super();
		AddressId = addressId;
		this.streetAddress = streetAddress;
		this.streetAddressAdditional = streetAddressAdditional;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
		this.addressType = addressType;
	}
	
	
	
	
	

}
