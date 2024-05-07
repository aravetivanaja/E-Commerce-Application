package com.retail.ecommerce.JWT;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.retail.ecommerce.Exception.TokenIsBlockedException;
import com.retail.ecommerce.Repository.AccessTokenRepository;
import com.retail.ecommerce.Repository.RefreshTokenRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RefreshFilter extends OncePerRequestFilter{
	
	@Autowired
	private AccessTokenRepository accessRepo;
	
	@Autowired
	private RefreshTokenRepository refreshRepo;
	
	@Autowired
	private JWTService jwtservice;

	public RefreshFilter( RefreshTokenRepository refreshRepo, JWTService jwtservice) {
		super();
		
		this.refreshRepo = refreshRepo;
		this.jwtservice = jwtservice;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		
		String at=null;
		String rt=null;
		
		if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("at")) {
                    at = cookie.getValue();
                }
                if (cookie.getName().equals("rt")) {
                    rt = cookie.getValue();
                }
            }
        }
//		if(at==null || rt==null)
//		{
//			System.out.println("throw anonymous user");
//		}
//		
//		if(at!=null || rt==null)
//		{
//			
//		}
		
		if(at!=null && rt!=null)
		{
			if(accessRepo.existsByTokenAndIsBlocked(at,true) && refreshRepo.existsByTokenAndIsBlocked(rt,true))
			{
				throw new TokenIsBlockedException("token is blocked");
			}
			
			String userName = jwtservice.getUserName(at);
			String role=jwtservice.getRole(at);
			
			if(userName!=null && role!=null )
			{
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, null,Collections.singleton(new SimpleGrantedAuthority(role)));
				authenticationToken.setDetails(new WebAuthenticationDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
		
		filterChain.doFilter(request, response);
		
		
	}
	

}
