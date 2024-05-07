package com.retail.ecommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ecommerce.RequestDTO.AddressRequest;
import com.retail.ecommerce.ResponseDTO.AddressContactsResponse;
import com.retail.ecommerce.ResponseDTO.AddressResponse;
import com.retail.ecommerce.ResponseDTO.AddressUpdateResponse;

import com.retail.ecommerce.Service.AddressService;
import com.retail.ecommerce.Util.ResponseStructure;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ecv1")
public class AddressController {
	
	private AddressService addressservice;

	public AddressController(AddressService addressservice) {
		super();
		this.addressservice = addressservice;
	}
	@PostMapping("/addAddress")
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(
			@Valid @RequestBody AddressRequest addressRequest) {
		return addressservice.addAddress(addressRequest);

	}
	
	@PostMapping("/{addressId}/updateAddress")
	public ResponseEntity<ResponseStructure<AddressUpdateResponse>> updateAddress(@Valid @RequestBody AddressRequest addressRequest,@PathVariable int addressId){
		return addressservice.updateAddress(addressRequest,addressId);
	}
	
	@GetMapping("/{userId}/findAddress")
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddress(@PathVariable int userId)
	{
		return addressservice.findAddress(userId);
	}
	

}
