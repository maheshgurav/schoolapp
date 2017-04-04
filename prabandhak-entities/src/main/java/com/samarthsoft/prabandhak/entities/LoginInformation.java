package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.enums.UserRole;

public class LoginInformation extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private UserRole userRole;

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}