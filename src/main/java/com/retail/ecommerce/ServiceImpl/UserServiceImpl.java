package com.retail.ecommerce.ServiceImpl;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.retail.ecommerce.Cache.CacheStore;
import com.retail.ecommerce.Enum.UserRole;
import com.retail.ecommerce.Exception.AddressAlreadyExistsException;
import com.retail.ecommerce.Exception.InvalidCredentialsException;
import com.retail.ecommerce.Exception.InvalidTokenAccess;
import com.retail.ecommerce.Exception.InvalidUserEmailException;
import com.retail.ecommerce.Exception.InvalidUserRoleException;
import com.retail.ecommerce.Exception.OTPInvalidException;
import com.retail.ecommerce.Exception.OtpExpiredException;
import com.retail.ecommerce.Exception.RegistrationExpiredException;
import com.retail.ecommerce.Exception.UserAlreadyExistsByEmail;
import com.retail.ecommerce.Exception.UserIsLoggedOutException;
import com.retail.ecommerce.Exception.UserNotLoggedInException;
import com.retail.ecommerce.JWT.JWTService;
import com.retail.ecommerce.Mail_service.Mail_Service;
import com.retail.ecommerce.Model.AccessToken;
import com.retail.ecommerce.Model.Address;
import com.retail.ecommerce.Model.Customer;
import com.retail.ecommerce.Model.RefreshToken;
import com.retail.ecommerce.Model.Seller;
import com.retail.ecommerce.Model.User;
import com.retail.ecommerce.Repository.AccessTokenRepository;
import com.retail.ecommerce.Repository.CustomerRepository;
import com.retail.ecommerce.Repository.RefreshTokenRepository;
import com.retail.ecommerce.Repository.SellerRepository;
import com.retail.ecommerce.Repository.UserRepository;
import com.retail.ecommerce.RequestDTO.AddressRequest;
import com.retail.ecommerce.RequestDTO.AuthRequestDTO;
import com.retail.ecommerce.RequestDTO.OtpRequest;
import com.retail.ecommerce.RequestDTO.UserRequestDTO;
import com.retail.ecommerce.ResponseDTO.AddressResponse;
import com.retail.ecommerce.ResponseDTO.AuthResponseDTO;
import com.retail.ecommerce.ResponseDTO.UserResponse;
import com.retail.ecommerce.Service.UserService;
import com.retail.ecommerce.Util.MessageModel;
import com.retail.ecommerce.Util.ResponseStructure;
import com.retail.ecommerce.Util.SimpleResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService{

	private ResponseStructure<AuthResponseDTO> authStructure;

	private UserRepository ur;

	private SellerRepository sr;

	private CustomerRepository cr;

	private ResponseStructure<UserResponse> responseStructure;

	private CacheStore<User> userCache;

	private CacheStore<String> otpCache;

	private SimpleResponseStructure simpleResponseStructure;

	private Mail_Service mailService;

	private PasswordEncoder password;

	private AuthenticationManager authenticationManager;

	private JWTService js;

	private RefreshTokenRepository refreshRepo;

	private AccessTokenRepository accessRepo;

	@Value("${myapp.jwt.access.expiration}")
	private long accessExpiration;

	@Value("${myapp.jwt.refresh.expiration}")
	private long refreshExpiration;




	public UserServiceImpl(ResponseStructure<AuthResponseDTO> authStructure, UserRepository ur, SellerRepository sr,
			CustomerRepository cr, ResponseStructure<UserResponse> responseStructure, CacheStore<User> userCache,
			CacheStore<String> otpCache, SimpleResponseStructure simpleResponseStructure, Mail_Service mailService,
			PasswordEncoder password, AuthenticationManager authenticationManager, JWTService js,
			RefreshTokenRepository refreshRepo, AccessTokenRepository accessRepo) {
		super();
		this.authStructure = authStructure;
		this.ur = ur;
		this.sr = sr;
		this.cr = cr;
		this.responseStructure = responseStructure;
		this.userCache = userCache;
		this.otpCache = otpCache;
		this.simpleResponseStructure = simpleResponseStructure;
		this.mailService = mailService;
		this.password = password;
		this.authenticationManager = authenticationManager;
		this.js = js;
		this.refreshRepo = refreshRepo;
		this.accessRepo = accessRepo;

	}

	@Override
	public ResponseEntity<SimpleResponseStructure> userRegistration(UserRequestDTO userReq) {
		if(ur.existsByEmail(userReq.getEmail()))
			throw new UserAlreadyExistsByEmail("failed to register");
		
		String encryptPassword=password.encode(userReq.getPassword());

		User user=mapToChildEntity(userReq);
		user.setPassword(encryptPassword);
		ur.save(user);

		String otp=generateOTP();

		otpCache.add(userReq.getEmail(), otp);
		userCache.add(userReq.getEmail(), user);

		System.err.println(otp);
		try {
			sendOtp(user, otp);
		}
		catch(MessagingException ex)
		{
			throw new InvalidUserEmailException("user email not verified");
		}

		return ResponseEntity.ok(simpleResponseStructure.setStatuscode(HttpStatus.ACCEPTED.value())
				.setMessage("verified otp sent through mail"));

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OtpRequest otpReq) {

		if(otpCache.get(otpReq.getEmail())==null)
			throw new OtpExpiredException("otp expired");
		if(!otpCache.get(otpReq.getEmail()).equals(otpReq.getOtp())) 
			throw new RuntimeException();

		User user=userCache.get(otpReq.getEmail());
		

		if(user==null) 
			throw new RegistrationExpiredException("registration expired");

		user.setEmailVerified(true);
		user=ur.save(user);
		
//		if (user.getUserRole() == null)
//	        throw new IllegalStateException("UserRole cannot be null");
//

//		if(user.getUserRole().equals(UserRole.SELLER))
//		{
//			sr.save(mapToSeller(new Seller(),user));
//		}
//		else if(user.getUserRole().equals(UserRole.CUSTOMER))
//		{
//			cr.save(mapToCustomer(new Customer(),user));
//		}

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(responseStructure.setData(mapToUserResponse(user))
						.setMessage("otp verified successfully")
						.setStatusCode(HttpStatus.CREATED.value()));
	}

	private Seller mapToSeller(Seller seller, User user) {
		seller.setDisplayName(user.getUserName());
		seller.setEmail(user.getEmail());
		seller.setUserName(convertUsername(new String(),user.getEmail()));
		seller.setUserRole(user.getUserRole());
		seller.setPassword(user.getPassword());
		seller.setPassword(password.encode(user.getPassword()));

		return seller;
	}

	private Customer mapToCustomer(Customer customer,User user)
	{
		customer.setDisplayName(user.getUserName());
		customer.setEmail(user.getEmail());
		customer.setUserName(convertUsername(new String(), user.getEmail()));

		customer.setPassword(password.encode(user.getPassword()));

		return customer;

	}

	private String convertUsername(String username,String user)
	{
		username=user.substring(0, user.indexOf('@'));
		return username;
	}

	private String generateOTP()
	{
		return String.valueOf(new Random().nextInt(100000,999999));
	}

	private <T extends User> T mapToChildEntity(UserRequestDTO userReq) {
		UserRole role=userReq.getUserRole();

		User user=null;
		switch(role)
		{
		case SELLER -> user=new Seller();
		case CUSTOMER -> user=new Customer();
		default ->throw new RuntimeException();
		}

		user.setDisplayName(userReq.getName());
		user.setUserName(userReq.getEmail().substring(0,userReq.getEmail().indexOf('@')));
		user.setEmail(userReq.getEmail());
		user.setPassword(userReq.getPassword());
		user.setUserRole(userReq.getUserRole());
		user.setEmailVerified(false);
		return (T) user;
	}



	private void sendOtp(User user,String otp)throws MessagingException
	{
		MessageModel message=new MessageModel(user.getEmail(),"verify your otp",
				"<p> Hi,Thanks for your interest in E-comm ,please verify your mail id using the OTP given below</p>"
						+"<h1>"+otp+ "</h1>" +"<br>" +"<p> please ignore if its not you </p>" +"<br> "+"with best regards" +"<h3> E-comm </h3>"
				);
		mailService.sendMailMessage(message);
	}

	@Override
	public ResponseEntity<ResponseStructure<AuthResponseDTO>> userLogin(AuthRequestDTO authReq) {	
	if(authReq.getUserName()==null) {
		throw new UsernameNotFoundException("username not found");
	}
	String userName=authReq.getUserName().split("@gmail.com")[0];

	Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, authReq.getPassword()));

	if(!authentication.isAuthenticated())
		throw new InvalidCredentialsException("enter valid credentials");

	SecurityContextHolder.getContext().setAuthentication(authentication);

	HttpHeaders headers=new HttpHeaders();	
	return ur.findByUserName(userName).map(user ->{
		
		generateAccessToken(user, headers);
		generateRefreshToken(user, headers);
		
		AuthResponseDTO ac=new AuthResponseDTO();
		ac.setUserId(user.getUserId());
		ac.setUserName(userName);
		ac.setRole(user.getUserRole());
		ac.setAccessExpiration(accessExpiration);
		ac.setRefreshEpiration(refreshExpiration);
		
		
		return ResponseEntity.ok().headers(headers).body(authStructure.setMessage("login successfully")
				.setData(ac)
				.setStatusCode(HttpStatus.OK.value()));

		
	}).orElseThrow(() -> new InvalidCredentialsException("enter valid details"));
			
	
}


private void generateAccessToken(User user,HttpHeaders headers)
{
	String token=js.generateAccessToken(user, user.getUserRole().name());
	headers.add(HttpHeaders.SET_COOKIE, configureCookie("at",token,accessExpiration));
	AccessToken at=new AccessToken();
	at.setBlocked(false);
	at.setExpiration(accessExpiration);
	at.setToken(token);
	accessRepo.save(at);
	user.getaList().add(at);
	ur.save(user);
}



private void generateRefreshToken(User user,HttpHeaders headers)
{
	String token=js.generateRefreshToken(user,user.getUserRole().name());
	headers.add(HttpHeaders.SET_COOKIE, configureCookie("rt",token,refreshExpiration));
	RefreshToken rf=new RefreshToken();
	rf.setBlocked(false);
	rf.setExpiration(refreshExpiration);
	rf.setToken(token);
	refreshRepo.save(rf);
	user.getrList().add(rf);
	ur.save(user);


}
private String configureCookie(String name, String value, long maxAge) {

	return ResponseCookie.from(name,value)
			.domain("localhost")
			.path("/")
			.httpOnly(true)
			.secure(false)
			.maxAge(Duration.ofMillis(maxAge))
			.sameSite("Lax")
			.build()
			.toString();

}

private AuthResponseDTO mapToAuthResponse(User user) 
{
	AuthResponseDTO authResponse=new AuthResponseDTO();
	authResponse.setRole(user.getUserRole());
	authResponse.setUserId(user.getUserId());
	authResponse.setUserName(user.getUserName());

	return authResponse;

}


private UserResponse mapToUserResponse(User user)
{
	UserResponse userResponse=new UserResponse();
	userResponse.setUserId(user.getUserId());
	userResponse.setDisplayName(user.getDisplayName());
	userResponse.setEmail(user.getEmail());
	userResponse.setUserName(user.getUserName());
	userResponse.setUserRole(user.getUserRole());
	userResponse.setEmailVerified(user.getisEmailVerified());
	return userResponse;

}


@Override
public ResponseEntity<SimpleResponseStructure> userLogout(String accessToken, String refreshToken) {
	
	
	if(accessToken ==null && refreshToken==null)
		throw new UserNotLoggedInException("user cannot logged in");
	
	HttpHeaders headers=new HttpHeaders();
	accessRepo.findByToken(accessToken).ifPresent(access ->{
			refreshRepo.findByToken(refreshToken).ifPresent(refresh ->{
				
				access.setBlocked(true);
				accessRepo.save(access);
				
				refresh.setBlocked(true);
				refreshRepo.save(refresh);
				
				removeToken("at",headers);
				removeToken("rt",headers);
				
			});
	});
		
  return ResponseEntity.ok(simpleResponseStructure.setMessage("blocked and removed successfully")
		  .setStatuscode(HttpStatus.OK.value()));
	}

private void removeToken(String value,HttpHeaders headers)
{
	headers.add(HttpHeaders.SET_COOKIE, removeCookie(value));}
	
	
	private String removeCookie(String name) {
		
		return ResponseCookie.from(name,"")
				.domain("localhost").path("/")
				.httpOnly(true)
				.secure(false)
				.maxAge(0)
				.sameSite("Lax")
				.build().toString();
		
	}

	@Override
	public ResponseEntity<SimpleResponseStructure> refresh(String accessToken, String refreshToken) {
		
		if(accessToken==null || refreshToken==null) 
			throw new InvalidCredentialsException("Tokens Expired");
		HttpHeaders headers= new HttpHeaders();
		accessRepo.findByToken(accessToken).ifPresent(access ->{
			refreshRepo.findByToken(refreshToken).ifPresent(refresh ->{
				
				Date date=js.getIssuedAt(refreshToken);
				if(new Date().equals(date))
				{
					access.setBlocked(true);
					generateAccessToken(access.getUser(), headers);
					accessRepo.save(access);
				}
				else {
					headers.add(HttpHeaders.SET_COOKIE, configureCookie("at", accessToken, accessExpiration));
				}
				if(new Date().before(date))
				{
					if(new Date().equals(date))
					{
						refresh.setBlocked(true);
						generateRefreshToken(refresh.getUser(), headers);
						refreshRepo.save(refresh);
					}
					throw new InvalidTokenAccess("Token Expired");
				}
				else
				{
					headers.add(HttpHeaders.SET_COOKIE, configureCookie("rt", refreshToken, refreshExpiration));
				}
			
			});
		});
		return ResponseEntity.ok().headers(headers).body(simpleResponseStructure.setMessage("User able to access")
				.setStatuscode(HttpStatus.OK.value()));
	}

//	
}
