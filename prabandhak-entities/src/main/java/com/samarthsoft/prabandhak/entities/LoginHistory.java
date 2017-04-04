package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import com.samarthsoft.prabandhak.enums.LoginStatus;

public class LoginHistory extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userGuid;
	private Timestamp lastLogin;
	private Integer failedLoginAttemptCount;
	private LoginStatus loginStatus;

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}
	
	public Integer getFailedLoginAttemptCount() {
		return failedLoginAttemptCount;
	}

	public void setFailedLoginAttemptCount(Integer failedLoginAttemptCount) {
		this.failedLoginAttemptCount = failedLoginAttemptCount;
	}

	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

}
