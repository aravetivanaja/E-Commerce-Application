package com.retail.ecommerce.JWT;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.retail.ecommerce.Model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;



@Service
public class JWTService {
	
	//encrypt the tokens ,decrypt tokens 
	//secret
	
	@Value("${myapp.jwt.secret}")
	private String secret;
	
	@Value("${myapp.jwt.access.expiration}")
	private long accessExpiry;
	
	@Value("${myapp.jwt.refresh.expiration}")
	private long refreshExpiry;
	
	private String generateToken(User user,String role,long expiration)
	{
		return Jwts.builder()
		.setClaims(Maps.of("role", role).build())
		.setSubject(user.getUserName())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + expiration))
		.signWith(getSignatureKey(),SignatureAlgorithm.HS256)
		.compact();
	}
	
	public String generateAccessToken(User user,String role)
	{
		return generateToken(user,role, accessExpiry);
		
	}
	public String generateRefreshToken(User user,String role)
	{
		return generateToken(user,role, refreshExpiry);
	}
	
	
	private Key getSignatureKey()
	{
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}
	
	public String getRole(String role)
	{
		return parseJWTClaims(role).get("role",String.class);
	}
	
	public String getUserName(String token)
	{
		return parseJWTClaims(token)
				.getSubject();
	}
	
	public Date getIssuedAt(String token)
	{
		return parseJWTClaims(token).getIssuedAt();
	}
	
	private Claims parseJWTClaims(String token)
	{		
		return Jwts.parserBuilder()
				.setSigningKey(getSignatureKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
	}

	
}
