package com.retail.ecommerce.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.retail.ecommerce.RequestDTO.AddressRequest;
import com.retail.ecommerce.ResponseDTO.AddressContactsResponse;
import com.retail.ecommerce.ResponseDTO.AddressResponse;
import com.retail.ecommerce.ResponseDTO.AddressUpdateResponse;
import com.retail.ecommerce.Util.ResponseStructure;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressUpdateResponse>> updateAddress(AddressRequest addressRequest,
			int addressId);

	
	ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest);


	ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddress(int userId);

}
