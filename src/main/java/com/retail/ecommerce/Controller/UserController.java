package com.retail.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ecommerce.JWT.JWTService;
import com.retail.ecommerce.RequestDTO.AddressRequest;
import com.retail.ecommerce.RequestDTO.AuthRequestDTO;
import com.retail.ecommerce.RequestDTO.OtpRequest;
import com.retail.ecommerce.RequestDTO.UserRequestDTO;
import com.retail.ecommerce.ResponseDTO.AddressResponse;
import com.retail.ecommerce.ResponseDTO.AuthResponseDTO;
import com.retail.ecommerce.ResponseDTO.UserResponse;
import com.retail.ecommerce.Service.UserService;
import com.retail.ecommerce.Util.ResponseStructure;
import com.retail.ecommerce.Util.SimpleResponseStructure;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true" )
@RestController
@RequestMapping("/api/ecv1")
public class UserController {
	
	private UserService us;
	private JWTService js;
	
	public UserController(UserService us, JWTService js) {
		super();
		this.us = us;
		this.js = js;
	}

	@PostMapping("/users/register")
	public ResponseEntity<SimpleResponseStructure> userRegistration(@RequestBody UserRequestDTO userReq)
	{
		return us.userRegistration(userReq);
		
	}
	
	@PostMapping("/verify-otp")
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(@RequestBody OtpRequest otp)
	{
		return us.verifyOTP(otp);
	}
	
//	@GetMapping("/test")
//	public String generateAccessToken()
//	{
//		return js.generateAccessToken("safyjvduff");
//	}
	
	@PostMapping("/users/login")
	public ResponseEntity<ResponseStructure<AuthResponseDTO>> userLogin(@RequestBody AuthRequestDTO authReq)
	{
		return us.userLogin(authReq);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<SimpleResponseStructure> userLogout(@CookieValue (name="at",required=false) String accessToken,@CookieValue(name="rt",required=false) String refreshToken)
	{
		return us.userLogout(accessToken,refreshToken);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<SimpleResponseStructure> refresh(@CookieValue (name="at",required=false) String accessToken,@CookieValue(name="rt",required=false) String refreshToken)
	{
		return us.refresh(accessToken,refreshToken);
	}
	

}
