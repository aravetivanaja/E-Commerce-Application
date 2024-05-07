package com.retail.ecommerce.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.retail.ecommerce.RequestDTO.ContactRequest;
import com.retail.ecommerce.ResponseDTO.UpdateContact;
import com.retail.ecommerce.Util.ResponseStructure;
import com.retail.ecommerce.Util.SimpleResponseStructure;

import jakarta.validation.Valid;

public interface ContactService {

	ResponseEntity<ResponseStructure<UpdateContact>> updateContact(ContactRequest contactsRequest, int contactId);

	ResponseEntity<SimpleResponseStructure> addContact(@Valid List<ContactRequest> contactRequests, int addressId);
	
	

}
