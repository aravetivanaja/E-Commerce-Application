package com.retail.ecommerce.RequestDTO;

import com.retail.ecommerce.Enum.UserRole;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email","userName"}))
public class UserRequestDTO {

	private String name;
	private String email;
	private String password;
	private UserRole userRole;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserRequestDTO(String name, String email, String password, UserRole userRole) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		
	}
	public UserRequestDTO()
	{
		
	}
	
	
	

}
