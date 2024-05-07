package com.retail.ecommerce.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.retail.ecommerce.Enum.AddressType;
import com.retail.ecommerce.Enum.UserRole;
import com.retail.ecommerce.Exception.AddressAllreadyAddedException;
import com.retail.ecommerce.Exception.AddressLimitException;
import com.retail.ecommerce.Exception.AddressTypeIsNullException;
import com.retail.ecommerce.Exception.AddressnotFoundByIdException;
import com.retail.ecommerce.Exception.InvalidUserEmailException;
import com.retail.ecommerce.Exception.PleaseGiveRefreshAccessTokenRequest;
import com.retail.ecommerce.Exception.UserIsNotLoginException;
import com.retail.ecommerce.JWT.JWTService;
import com.retail.ecommerce.Model.Address;
import com.retail.ecommerce.Model.Contact;
import com.retail.ecommerce.Model.Customer;
import com.retail.ecommerce.Model.Seller;
import com.retail.ecommerce.Model.User;
import com.retail.ecommerce.Repository.AddressRepository;
import com.retail.ecommerce.Repository.CustomerRepository;
import com.retail.ecommerce.Repository.SellerRepository;
import com.retail.ecommerce.Repository.UserRepository;
import com.retail.ecommerce.RequestDTO.AddressRequest;
import com.retail.ecommerce.ResponseDTO.AddressContactResponse;
import com.retail.ecommerce.ResponseDTO.AddressContactsResponse;
import com.retail.ecommerce.ResponseDTO.AddressResponse;
import com.retail.ecommerce.ResponseDTO.AddressUpdateResponse;
import com.retail.ecommerce.Service.AddressService;
import com.retail.ecommerce.Util.ResponseStructure;

@Service
public class AddressServiceImpl implements AddressService{
	
	private AddressRepository addressRepo;
	private JWTService jwtService;
	private UserRepository userRepo;
	private SellerRepository sellerRepo;
	private CustomerRepository customerRepo;
	private ResponseStructure<AddressResponse> responseStructure;
	private ResponseStructure<AddressContactsResponse> addressContactStructure;
	private ResponseStructure<AddressUpdateResponse> updateresponse;
	

	public AddressServiceImpl(AddressRepository addressRepo, JWTService jwtService, UserRepository userRepo,
			SellerRepository sellerRepo, CustomerRepository customerRepo,
			ResponseStructure<AddressResponse> responseStructure,
			ResponseStructure<AddressContactsResponse> addressContactStructure,
			ResponseStructure<AddressUpdateResponse> updateresponse) {
		super();
		this.addressRepo = addressRepo;
		this.jwtService = jwtService;
		this.userRepo = userRepo;
		this.sellerRepo = sellerRepo;
		this.customerRepo = customerRepo;
		this.responseStructure = responseStructure;
		this.addressContactStructure = addressContactStructure;
		this.updateresponse = updateresponse;
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest) {
		
		if (!addressRequest.getAddressType().equals(AddressType.HOME)
				&& !addressRequest.getAddressType().equals(AddressType.OFFICE))
			throw new AddressTypeIsNullException("AddressType Is Not Specified");
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		
		Address address = null;
		 User user = userRepo.findByUserName(userName)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

			if (user.getUserRole().equals(UserRole.SELLER)) {
				
				Seller seller = (Seller) user;
				
				if (seller.getAddress() != null)
					throw new AddressAllreadyAddedException("Address Is Allready Added...");
				
				address = addressRepo.save(mapToAddress(addressRequest));
				seller.setAddress(address);
				sellerRepo.save(seller);
			} 
			else if(user.getUserRole().equals(UserRole.CUSTOMER))
			{
	
				Customer customer = (Customer) user;
				
				if (customer.getAddresses() != null)
					throw new AddressAllreadyAddedException("Address Is Allready Added...");
				
	
				if (customer.getAddresses().size() >= 5)
					throw new AddressLimitException("You Allready Reached Limit Of Adding Addresses...");
				
				
				address = addressRepo.save(mapToAddress(addressRequest));
				
			    customer.getAddresses().add(address);
			    customerRepo.save(customer);
	
			}

		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value()).setMessage("address is saved")
				.setData(mapToAddressResponse(address)));
	}



	private AddressResponse mapToAddressResponse(Address address) {
		
		AddressResponse addressResponse =new AddressResponse();
		addressResponse.setAddressId(address.getAddressId());
		return addressResponse;
		}

	private Address mapToAddress(AddressRequest addressRequest) {
		Address address=new Address();
		address.setStreetAddress(addressRequest.getStreetAddress());
		address.setStreetAddressAdditional(addressRequest.getStreetAddressAdditional());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPincode(addressRequest.getPincode());
		address.setAddressType(addressRequest.getAddressType());
		
		return address;

	}

//	@Override
//	public ResponseEntity<ResponseStructure<AddressContactsResponse>> findAddress() {
//
//		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//		
//		 User user = userRepo.findByUserName(userName)
//	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//		if (user.getUserRole().equals(UserRole.SELLER)) {
//			Seller seller = (Seller) user;
//			Address address = seller.getAddress();
//			List<Contact> contact = address.getContact();
//			return ResponseEntity.ok(addressContactStructure.setStatusCode(HttpStatus.OK.value())
//					.setMessage("Data Found").setData(mapToAddressContactResponse(address, contact)));
//		} else {
//			Customer customer = (Customer) user;
//			List<Address> addresses = customer.getAddresses();
//			return ResponseEntity.ok(addressContactStructure.setStatusCode(HttpStatus.OK.value())
//					.setMessage("Data Found").setData(mapToAddressContactResponse(addresses)));
//		}
//
//	}

	private AddressContactsResponse mapToAddressContactResponse(Address address, List<Contact> contact) {
		
		AddressContactsResponse addressContactsResponse=new AddressContactsResponse();
		
		addressContactsResponse.setAddress(mapToAddressContacts(address,contact));
		
		return addressContactsResponse;
	}

	private List<AddressContactResponse> mapToAddressContacts(Address address, List<Contact> contact) {

		List<AddressContactResponse> list = new ArrayList<>();
		AddressContactResponse addressContactResponse = new AddressContactResponse();
		addressContactResponse.setAddressId(address.getAddressId());
		addressContactResponse.setStreetAddress(address.getStreetAddress());
		addressContactResponse.setStreetAdressAditional(address.getStreetAddressAdditional());
		addressContactResponse.setCity(address.getCity());
		addressContactResponse.setState(address.getState());
		addressContactResponse.setCountry(address.getCountry());
		addressContactResponse.setPincode(address.getPincode());
		addressContactResponse.setContact(contact);
		addressContactResponse.setAddressType(address.getAddressType());
		
		list.add(addressContactResponse);

		return list;
	}

	private AddressContactsResponse mapToAddressContactResponse(List<Address> addresses) {
		
		AddressContactsResponse addressContactsResponse=new AddressContactsResponse();
		addressContactsResponse.setAddress(mapToAddresContact(addresses));
		return addressContactsResponse;
	}

	private List<AddressContactResponse> mapToAddresContact(List<Address> addresses) {

		List<AddressContactResponse> list = new ArrayList<>();
		for (Address address : addresses) {
			AddressContactResponse addressContactResponse=new AddressContactResponse();
			addressContactResponse.setAddressId(address.getAddressId());
			addressContactResponse.setStreetAddress(address.getStreetAddress());
			addressContactResponse.setStreetAdressAditional(address.getStreetAddressAdditional());
			addressContactResponse.setCity(address.getCity());
			addressContactResponse.setState(address.getState());
			addressContactResponse.setCountry(address.getCountry());
			addressContactResponse.setPincode(address.getPincode());
			addressContactResponse.setContact(address.getContact());
			addressContactResponse.setAddressType(address.getAddressType());
					
			list.add(addressContactResponse);

		}
		return list;
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressUpdateResponse>> updateAddress(AddressRequest addressRequest,
			int addressId) {
		return addressRepo.findById(addressId).map(address -> {
			address = mapToAddress(address, addressRequest);
			addressRepo.save(address);
			return ResponseEntity.ok(updateresponse.setStatusCode(HttpStatus.OK.value())
					.setMessage("Address Is Updated").setData(mapToAddressUpdateResponse(address)));
		}).orElseThrow(() -> new AddressnotFoundByIdException("address not found by given id....."));

	}

	private AddressUpdateResponse mapToAddressUpdateResponse(Address address) {
		AddressUpdateResponse addressUpdateResponse=new AddressUpdateResponse();
		
		addressUpdateResponse.setAddressId(address.getAddressId());
		addressUpdateResponse.setAddressType(address.getAddressType());
		addressUpdateResponse.setCity(address.getCity());
		addressUpdateResponse.setCountry(address.getCountry());
		addressUpdateResponse.setPincode(address.getPincode());
		addressUpdateResponse.setState(address.getState());
		addressUpdateResponse.setStreetAddress(address.getStreetAddress());
		addressUpdateResponse.setStreetAdressAditional(address.getStreetAddressAdditional());
		return addressUpdateResponse;


	}

	private Address mapToAddress(Address address, AddressRequest addressRequest) {
		address.setAddressId(address.getAddressId());
		address.setStreetAddress(addressRequest.getStreetAddress());
		address.setStreetAddressAdditional(addressRequest.getStreetAddressAdditional());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setCountry(addressRequest.getCountry());
		address.setPincode(addressRequest.getPincode());
		address.setContact(address.getContact());
		
		return address;

	}

//	@Override
//	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddress(int userId) {
//		
//		return  userRepo.findById(userId).map(user ->{
//			
//			String email=SecurityContextHolder.getContext().getAuthentication().getName();
//			System.out.println(email+" "+user.getUserName());
//			if(!email.equals(user.getUserName()))
//				throw new InvalidUserEmailException("unauthorized user");
//			
//			List<Address> list=new ArrayList<>();
//
//			if(user instanceof Customer)
//			{
//				Customer cos=(Customer)user;
//				List<Address> address = cos.getAddresses();
//				list.addAll(address);
//
//			}
//			else if(user instanceof Seller)
//			{
//				Seller sel=(Seller)user;
//				list.add(sel.getAddress());
//			}
//			
//			return ResponseEntity.ok(new ResponseStructure<List<AddressResponse>>().setData(mapToAddressResponse(address))
//					.setMessage("find address successfully"));
//			
//		}).orElseThrow(()-> new UserIdNotFoundException("Invalid User Id"));
//
//	}
	
	private List<AddressResponse> mapToList(List<Address> list, ArrayList<AddressResponse> arrayList) {

		for(Address add:list)
		{
			arrayList.add(mapToAddressResponse(add));
		}

		return arrayList;

	}

	@Override
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddress(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
