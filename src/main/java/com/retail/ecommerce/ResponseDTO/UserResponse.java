package com.retail.ecommerce.ResponseDTO;

import com.retail.ecommerce.Enum.UserRole;

public class UserResponse {
	
	private int userId;
	private String displayName;
	private String userName;
	private String email;
	private UserRole userRole;
	private boolean isEmailVerified;
	
	public String getDisplayName() {
	return displayName;
}
	public void setDisplayName(String displayName) {
	this.displayName = displayName;
}
	public boolean isEmailVerified() {
		return isEmailVerified;
	}
	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Enum getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserResponse() {
		super();
	}
	public UserResponse(int userId, String displayName, String userName, String email, UserRole userRole,
			boolean isEmailVerified) {
		super();
		this.userId = userId;
		this.displayName = displayName;
		this.userName = userName;
		this.email = email;
		this.userRole = userRole;
		this.isEmailVerified = isEmailVerified;
	}
	
	

}
