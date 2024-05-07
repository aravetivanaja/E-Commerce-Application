package com.retail.ecommerce.Util;

import org.springframework.scheduling.annotation.Scheduled;

import com.retail.ecommerce.Repository.AccessTokenRepository;
import com.retail.ecommerce.Repository.RefreshTokenRepository;

public class RemoveTokens {
	
	private AccessTokenRepository accessRepo;
	private RefreshTokenRepository refreshRepo;
	public RemoveTokens(AccessTokenRepository accessRepo, RefreshTokenRepository refreshRepo) {
		super();
		this.accessRepo = accessRepo;
		this.refreshRepo = refreshRepo;
	}
	
	

}
