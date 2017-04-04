package com.samarthsoft.prabandhak.webapp.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.samarthsoft.prabandhak.entities.LoginInformation;

public class CustomUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
	private LoginInformation loginInformation;
	
	public CustomUserDetails(LoginInformation loginInformation) {
		this.loginInformation = loginInformation;
	}
	
	public LoginInformation getLoginInformation() {
		return loginInformation;
	}

	public void setLoginInformation(LoginInformation loginInformation) {
		this.loginInformation = loginInformation;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(loginInformation.getUserRole().name()));
		return grantedAuthorities;
	}

	public String getPassword() {
		return loginInformation.getPassword();
	}

	public String getUsername() {
		return loginInformation.getUserName();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}