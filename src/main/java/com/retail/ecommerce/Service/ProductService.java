package com.retail.ecommerce.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.retail.ecommerce.Model.Product;
import com.retail.ecommerce.RequestDTO.FilterOptions;
import com.retail.ecommerce.RequestDTO.ProductRequest;
import com.retail.ecommerce.ResponseDTO.ProductResponse;
import com.retail.ecommerce.ResponseDTO.UpdatedProductResponse;
import com.retail.ecommerce.Util.ResponseStructure;

import jakarta.validation.Valid;



public interface ProductService {

	ResponseEntity<ResponseStructure<ProductResponse>> addProduct(@RequestBody ProductRequest productReq);

	ResponseEntity<ResponseStructure<UpdatedProductResponse>> updateProduct(@Valid ProductRequest productReq, @PathVariable int  productId);

	ResponseEntity<ResponseStructure<List<ProductResponse>>> getProductBySpecifications(FilterOptions options);

}
