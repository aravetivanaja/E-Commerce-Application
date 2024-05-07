package com.retail.ecommerce.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.retail.ecommerce.JWT.JWTFilter;
import com.retail.ecommerce.JWT.JWTService;
import com.retail.ecommerce.JWT.RefreshFilter;
import com.retail.ecommerce.Repository.AccessTokenRepository;
import com.retail.ecommerce.Repository.RefreshTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{
	
	private CustomUserDetailsService uds;
	//private JWTFilter jwtFilter;
	private AccessTokenRepository atr;
	private JWTService js;
	private RefreshTokenRepository rtr;	

	public SecurityConfig(CustomUserDetailsService uds, AccessTokenRepository atr, JWTService js,
			RefreshTokenRepository rtr) {
		super();
		this.uds = uds;
		this.atr = atr;
		this.js = js;
		this.rtr = rtr;
	}

	@Bean
	@Order(1)
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.csrf(csrf->csrf.disable())
				   .authorizeHttpRequests(auth->auth.requestMatchers("/api/ecv1/users/register","/api/ecv1/verify-otp","/api/ecv1/users/login")
				   .permitAll()//public
				   .anyRequest()//any request apart from url should be authenticate
				   .authenticated()).sessionManagement(management ->
				   management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   .authenticationProvider(authenticationProvider())
				   .addFilterBefore(new JWTFilter(atr, rtr, js), UsernamePasswordAuthenticationFilter.class)
				   .build();
				 //  .formLogin(Customizer.withDefaults())//2 -tier  -->specifies default configuration.//protection 
	} 
	
	@Bean
	@Order(2)
	SecurityFilterChain refreshFilterChain(HttpSecurity http) throws Exception
	{
		return http.csrf(csrf->csrf.disable())
				.securityMatchers(matcher -> matcher.requestMatchers("api/ecv1/refresh/**"))
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(new RefreshFilter(rtr,js), UsernamePasswordAuthenticationFilter.class)
				.authenticationProvider(authenticationProvider())
				.build();
	}

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(12);
	}
	
	@Bean
	AuthenticationProvider authenticationProvider()//data base authentication
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();// will
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService( uds);
		return provider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	
	 
}
