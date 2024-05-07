package com.retail.ecommerce.Model;

import java.util.List;

import com.retail.ecommerce.Enum.ImageType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageId;
	
	private List<String> image;
	
	private ImageType imageType;

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}


	@ManyToOne
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Image(int imageId, List<String> image, ImageType imageType, Product product) {
		super();
		this.imageId = imageId;
		this.image = image;
		this.imageType = imageType;
		this.product = product;
	}

	public Image() {
		super();
	}
	
	
	
	

}
