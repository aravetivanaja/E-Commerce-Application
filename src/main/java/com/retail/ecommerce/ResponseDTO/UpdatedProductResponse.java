package com.retail.ecommerce.ResponseDTO;

import com.retail.ecommerce.Enum.AvailabilityStatus;
import com.retail.ecommerce.Enum.Category;

public class UpdatedProductResponse {
	
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	private int productQuantity;
	private AvailabilityStatus availabilityStatus;
	private Category category;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
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
	public AvailabilityStatus getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public UpdatedProductResponse(int productId, String productName, String productDescription, double productPrice,
			int productQuantity, AvailabilityStatus availabilityStatus, Category category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.availabilityStatus = availabilityStatus;
		this.category = category;
	}
	public UpdatedProductResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
