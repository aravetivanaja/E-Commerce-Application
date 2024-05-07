package com.retail.ecommerce.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.retail.ecommerce.Enum.AvailabilityStatus;
import com.retail.ecommerce.Exception.ProductNotFoundByIdException;
import com.retail.ecommerce.Model.Product;
import com.retail.ecommerce.Model.Seller;
import com.retail.ecommerce.Model.User;
import com.retail.ecommerce.Repository.ProductRepository;
import com.retail.ecommerce.Repository.SellerRepository;
import com.retail.ecommerce.Repository.UserRepository;
import com.retail.ecommerce.RequestDTO.FilterOptions;
import com.retail.ecommerce.RequestDTO.ProductRequest;
import com.retail.ecommerce.ResponseDTO.ProductResponse;
import com.retail.ecommerce.ResponseDTO.UpdatedProductResponse;
import com.retail.ecommerce.Service.ProductService;
import com.retail.ecommerce.Util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class ProductServiceImpl implements ProductService{
	
	private UserRepository ur;
	private SellerRepository sr;
	private ProductRepository pr;
	private ResponseStructure<ProductResponse> productResponseStructure;
	private ResponseStructure<UpdatedProductResponse> updatedProductResponseStructure;
	private ProductSpecifications specifications;
	private ResponseStructure<List<ProductResponse>> productListStructure;

	

	public ProductServiceImpl(UserRepository ur, SellerRepository sr, ProductRepository pr,
			ResponseStructure<ProductResponse> productResponseStructure,
			ResponseStructure<UpdatedProductResponse> updatedProductResponseStructure,
			ProductSpecifications specifications, ResponseStructure<List<ProductResponse>> productListStructure) {
		super();
		this.ur = ur;
		this.sr = sr;
		this.pr = pr;
		this.productResponseStructure = productResponseStructure;
		this.updatedProductResponseStructure = updatedProductResponseStructure;
		this.specifications = specifications;
		this.productListStructure = productListStructure;
	}


	@Override
	public ResponseEntity<ResponseStructure<ProductResponse>> addProduct(ProductRequest productReq) {
		
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		
		return ur.findByUserName(userName).map(user ->{
			
			Seller seller=(Seller)user;
			
			
			Product product=pr.save(mapToProductEntity(productReq));
			
			if(product.getProductQuantity()<0)
			{
				product.setAvailabilityStatus(AvailabilityStatus.SOLD_OUT);
			}
			else if(product.getProductQuantity()>0 && product.getProductQuantity()<10)
			{
				product.setAvailabilityStatus(AvailabilityStatus.FEW_AVAILABLE);
			}
			else if(product.getProductQuantity()>10)
			{
				product.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
			}
			
			
			List<Product> sellerProducts=seller.getProducts();
			if(sellerProducts==null)
			{
				sellerProducts=new ArrayList<>();
			}
			sellerProducts.add(product);
			seller.setProducts(sellerProducts);
			
			sr.save(seller);
			
			
			return ResponseEntity.ok(productResponseStructure.setStatusCode(HttpStatus.OK.value())
					.setMessage("product added successfully")
					.setData(mapToProductResponse(product)));
		}).orElseThrow();
		
		
		
	}

	
	private Product mapToProductEntity(ProductRequest productReq)
	{
		Product product=new Product();
		product.setProductName(productReq.getProductName());
		product.setProductDescription(productReq.getProductDescription());
		product.setProductPrice(productReq.getProductPrice());
		product.setProductQuantity(productReq.getProductQuantity());
		product.setCategory(productReq.getCategory());
		
		return product;
	}
	
	private ProductResponse mapToProductResponse(Product product)
	{
		ProductResponse productResponse=new ProductResponse();
		productResponse.setProductName(product.getProductName());
		productResponse.setProductDescription(product.getProductDescription());
		productResponse.setCategory(product.getCategory());
		productResponse.setProductPrice(product.getProductPrice());
		productResponse.setProductId(product.getProductId());
		productResponse.setAvailabilityStatus(product.getAvailabilityStatus());
		productResponse.setProductQuantity(product.getProductQuantity());
		return productResponse;
		
	}


	@Override
	public ResponseEntity<ResponseStructure<UpdatedProductResponse>> updateProduct(@Valid ProductRequest productReq,
			int productId) {
		
			//return pr.findById(productId).map(product->{
				
			//});
			return null;
		
	}



	@Override
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> getProductBySpecifications(FilterOptions options) {
		Specification<Product> specification = specifications.buildSpecification(options);
		List<Product> products = pr.findAll(specification);
		
		List<ProductResponse> resultProducts=new ArrayList<>();
		
		for(Product rp:products)
		{
			resultProducts.add(mapToProductResponse(rp));
		}
		return ResponseEntity.ok(productListStructure.setMessage("filtered products")
				.setData(resultProducts));
		}
	}
