package com.retail.ecommerce.Model;

import java.util.List;

import com.retail.ecommerce.Enum.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Seller extends User{
	
	@OneToOne
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	@OneToMany
	private List<Product>products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Seller(Address address, List<Product> products) {
		super();
		this.address = address;
		this.products = products;
	}

	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	

}
