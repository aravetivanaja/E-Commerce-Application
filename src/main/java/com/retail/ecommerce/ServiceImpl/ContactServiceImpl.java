package com.retail.ecommerce.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retail.ecommerce.Enum.Priority;
import com.retail.ecommerce.Exception.AddressnotFoundByIdException;
import com.retail.ecommerce.Exception.ContactNotFoundByIdException;
import com.retail.ecommerce.Exception.ContactsFilledException;
import com.retail.ecommerce.Exception.PriorityNotSetException;
import com.retail.ecommerce.Model.Contact;
import com.retail.ecommerce.Repository.AddressRepository;
import com.retail.ecommerce.Repository.ContactRepo;
import com.retail.ecommerce.RequestDTO.ContactRequest;
import com.retail.ecommerce.ResponseDTO.UpdateContact;
import com.retail.ecommerce.Service.ContactService;
import com.retail.ecommerce.Util.ResponseStructure;
import com.retail.ecommerce.Util.SimpleResponseStructure;

@Service
public class ContactServiceImpl implements ContactService{
	
	private AddressRepository addressRepo;
	private ContactRepo contactRepo;
	private SimpleResponseStructure responseStructure;
	private ResponseStructure<UpdateContact> updateContactStructure;
	

	public ContactServiceImpl(AddressRepository addressRepo, ContactRepo contactRepo,
			SimpleResponseStructure responseStructure, ResponseStructure<UpdateContact> updateContactStructure) {
		super();
		this.addressRepo = addressRepo;
		this.contactRepo = contactRepo;
		this.responseStructure = responseStructure;
		this.updateContactStructure = updateContactStructure;
	}

	@Override
	public ResponseEntity<SimpleResponseStructure> addContact(List<ContactRequest> contactRequests, int addressId) {
		
		return addressRepo.findById(addressId).map(address ->{
			
			if(contactRequests.size()>2) throw new ContactsFilledException("Cannot add more than two contacts at once");
			
			List<Contact> contacts=new ArrayList<>();
			for(ContactRequest request:contactRequests)
			{
				contacts.add(mappToContact(request));
			}
			contactRepo.saveAll(contacts);
			address.setContact(contacts);
			addressRepo.save(address);
			
			return 	ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("Contacts Is created..SuccessFully"));
			
		}).orElseThrow(() -> new AddressnotFoundByIdException(""));


	}

	private Contact mappToContact(ContactRequest request) {
		if (!request.getPriority().equals(Priority.ADDITIONAL) && !request.getPriority().equals(Priority.PRIMARY)) throw new PriorityNotSetException(" priority not set..");
		
		Contact contact=new Contact();
		contact.setName(request.getName());
		contact.setPhoneNumber(request.getPhoneNumber());
		contact.setEmail(request.getEmail());
		contact.setPriority(request.getPriority());
		return contact;
	}

	@Override
	public ResponseEntity<ResponseStructure<UpdateContact>> updateContact(ContactRequest contactRequest,int contactId) {
		return contactRepo.findById(contactId).map(contact ->{ 
			Contact mapToContact = mapToContact(contact, contactRequest);
			contactRepo.save(mapToContact);
			
			return ResponseEntity.ok(updateContactStructure.setStatusCode(HttpStatus.OK.value())
					.setMessage("contact is Updated...")
					.setData(mapToUpdateContact(mapToContact)));
			
			
		}).orElseThrow(() -> new ContactNotFoundByIdException("contact Not Found By Given Id"));



	}

	private UpdateContact mapToUpdateContact(Contact contact) {
		UpdateContact updateContact=new UpdateContact();
		updateContact.setContactId(contact.getContactId());
		updateContact.setName(contact.getName());
		updateContact.setEmail(contact.getEmail());
		updateContact.setPhoneNumber(contact.getPhoneNumber());
		updateContact.setPriority(contact.getPriority());
		
		return updateContact;
	}

	private Contact mapToContact(Contact contact, ContactRequest contactsRequest) {
		contact.setContactId(contact.getContactId());
		contact.setName(contactsRequest.getName());
		contact.setPhoneNumber(contactsRequest.getPhoneNumber());
		contact.setEmail(contactsRequest.getEmail());
		contact.setPriority(contactsRequest.getPriority());

		return contact;

	}

}
