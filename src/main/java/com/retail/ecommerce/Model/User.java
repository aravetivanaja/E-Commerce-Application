package com.retail.ecommerce.Model;

import java.util.ArrayList;
import java.util.List;

import com.retail.ecommerce.Enum.UserRole;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "userRole")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String displayName;
	private String userName;
	private String email;
	private String password;
	private UserRole userRole;
	private boolean isEmailVerified;
	
	@OneToMany
	private List<RefreshToken> rList=new ArrayList<>();
	
	@OneToMany
	private List<AccessToken> aList=new ArrayList<>();
	
	
	
	
	public List<RefreshToken> getrList() {
		return rList;
	}
	public void setrList(List<RefreshToken> rList) {
		this.rList = rList;
	}
	public List<AccessToken> getaList() {
		return aList;
	}
	public void setaList(List<AccessToken> aList) {
		this.aList = aList;
	}
	public boolean isEmailVerified() {
		return isEmailVerified;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	
	
	public User() {
		super();
	}
	public User(int userId, String displayName, String userName, String email, String password, UserRole userRole,
			boolean isEmailVerified) {
		super();
		this.userId = userId;
		this.displayName = displayName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.isEmailVerified = isEmailVerified;
	}
	public boolean getisEmailVerified() {
		return isEmailVerified;
	}
	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
	
	
	
	

}
