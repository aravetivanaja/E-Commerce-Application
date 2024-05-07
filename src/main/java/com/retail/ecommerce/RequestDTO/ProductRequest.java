package com.retail.ecommerce.RequestDTO;

import com.retail.ecommerce.Enum.AvailabilityStatus;
import com.retail.ecommerce.Enum.Category;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class ProductRequest {
	
	
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9 !\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~]*$")
	private String productName;
	private String productDescription;
	@Max(value = 100000)
	@Positive
	@NotNull
	private double productPrice;
	@Max(value=400)
	@Positive
	private int productQuantity;
	@NotNull
	private Category category;
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public ProductRequest( String productName, String productDescription, double productPrice,
			int productQuantity,  Category category) {
		super();
		
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		
		this.category = category;
	}
	public ProductRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
