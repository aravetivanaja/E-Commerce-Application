package com.retail.ecommerce.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.retail.ecommerce.Repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepo;
	
	

	public CustomUserDetailsService(UserRepository userRepo) {
		
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepo.findByUserName(username).map(user ->new CustomUserDetails(user)).orElseThrow(()-> new UsernameNotFoundException("username not found"));
				
	}
	//

}
