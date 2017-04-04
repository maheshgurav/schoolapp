package com.samarthsoft.prabandhak.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.utilities.LoginControllerUtility;

public class UserDetailsAuthenticationService implements UserDetailsService {

	@Autowired
	private LoginControllerUtility loginControllerUtility;
	
	public CustomUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
		LoginInformation loginInformation = loginControllerUtility.getLoginInformationByUserName(userName);
		if(loginInformation!=null)
			return new CustomUserDetails(loginInformation);
		return new CustomUserDetails(new LoginInformation());
	}
}