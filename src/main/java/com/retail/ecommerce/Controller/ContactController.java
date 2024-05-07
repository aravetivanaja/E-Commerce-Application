package com.retail.ecommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ecommerce.RequestDTO.ContactRequest;
import com.retail.ecommerce.ResponseDTO.UpdateContact;
import com.retail.ecommerce.Service.ContactService;
import com.retail.ecommerce.Util.ResponseStructure;
import com.retail.ecommerce.Util.SimpleResponseStructure;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ecv1")
public class ContactController {
	
	private ContactService contactService;

	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}

	@PostMapping("/address/{addressId}/contacts")
	public ResponseEntity<SimpleResponseStructure> addContact(@Valid @RequestBody List<ContactRequest> contactRequests,
			@PathVariable int addressId) {
		return contactService.addContact(contactRequests, addressId);
	}

	@PostMapping("/{contactId}contacts")
	public ResponseEntity<ResponseStructure<UpdateContact>> updateContact(
			@Valid @RequestBody ContactRequest contactsRequest, @PathVariable int contactId) {
		return contactService.updateContact(contactsRequest, contactId);
	}

}
