package com.retail.ecommerce.Service;

import org.springframework.http.ResponseEntity;

import com.retail.ecommerce.RequestDTO.AddressRequest;
import com.retail.ecommerce.RequestDTO.AuthRequestDTO;
import com.retail.ecommerce.RequestDTO.OtpRequest;
import com.retail.ecommerce.RequestDTO.UserRequestDTO;
import com.retail.ecommerce.ResponseDTO.AddressResponse;
import com.retail.ecommerce.ResponseDTO.AuthResponseDTO;
import com.retail.ecommerce.ResponseDTO.UserResponse;
import com.retail.ecommerce.Util.ResponseStructure;
import com.retail.ecommerce.Util.SimpleResponseStructure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

	ResponseEntity<SimpleResponseStructure> userRegistration(UserRequestDTO userReq);

	ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OtpRequest otp);

	ResponseEntity<ResponseStructure<AuthResponseDTO>> userLogin(AuthRequestDTO authReq);

	ResponseEntity<SimpleResponseStructure> userLogout(String accessToken, String refreshToken);

	ResponseEntity<SimpleResponseStructure> refresh(String accessToken, String refreshToken);

	
}
