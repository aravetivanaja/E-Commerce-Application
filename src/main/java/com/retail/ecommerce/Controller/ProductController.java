package com.retail.ecommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ecommerce.RequestDTO.FilterOptions;
import com.retail.ecommerce.RequestDTO.ProductRequest;
import com.retail.ecommerce.ResponseDTO.ProductResponse;
import com.retail.ecommerce.ResponseDTO.UpdatedProductResponse;
import com.retail.ecommerce.Service.ProductService;
import com.retail.ecommerce.ServiceImpl.ProductSpecifications;
import com.retail.ecommerce.Util.ResponseStructure;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ecv1")
public class ProductController {
	
	private ProductService productService;
	private ProductSpecifications productSpecifications;
	
	

	public ProductController(ProductService productService, ProductSpecifications productSpecifications) {
		super();
		this.productService = productService;
		this.productSpecifications = productSpecifications;
	}

	@PreAuthorize("hasAuthority('SELLER')")
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<ProductResponse>>addProdcut(@Valid @RequestBody ProductRequest productReq)
	{
		return productService.addProduct(productReq);
	}
	
	@PostMapping("/{productId}/updateProduct")
	public ResponseEntity<ResponseStructure<UpdatedProductResponse>> updateProduct(@Valid @RequestBody ProductRequest productReq,@PathVariable int productId)
	{
		return productService.updateProduct(productReq,productId);
	}
	
//	@GetMapping("/{minPrice}/{maxPrice}/{availability}/{rating}/{discount}/{category}/filterAndFindProduct")
//	public ResponseEntity<ResponseStructure<ProductResponse>> filterAndFindProduct(@RequestParam(required = false) int minPrice,
//			@RequestParam(required = false)int maxPrice,@RequestParam(required = false) String availability,
//			@RequestParam(required = false) int rating,@RequestParam(required = false) int discount,
//			@RequestParam(required = false) String category)
//	{
//		return null;
//		
//		
//	}
	
	
	
	@GetMapping("/specifications")
	public ResponseEntity<ResponseStructure<List<ProductResponse>>> getProductBySpecifications(FilterOptions options)
	{
		System.out.println("filter");
		return productService.getProductBySpecifications(options);
	}
	
	
}
