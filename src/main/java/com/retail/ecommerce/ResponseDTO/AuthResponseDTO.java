package com.retail.ecommerce.ResponseDTO;

import com.retail.ecommerce.Enum.UserRole;

public class AuthResponseDTO {
	
	private int userId;
	private String userName;
	private long accessExpiration;
	private long refreshEpiration;
	private UserRole role;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getAccessExpiration() {
		return accessExpiration;
	}
	public void setAccessExpiration(Long accessExpiration) {
		this.accessExpiration = accessExpiration;
	}
	public Long getRefreshEpiration() {
		return refreshEpiration;
	}
	public void setRefreshEpiration(Long refreshEpiration) {
		this.refreshEpiration = refreshEpiration;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public AuthResponseDTO(int userId, String userName, Long accessExpiration, Long refreshEpiration, UserRole role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.accessExpiration = accessExpiration;
		this.refreshEpiration = refreshEpiration;
		this.role = role;
	}
	
	public AuthResponseDTO()
	{
		
	}
	

}
