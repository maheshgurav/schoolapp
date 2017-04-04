package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.security.core.context.SecurityContext;

import com.samarthsoft.prabandhak.entities.Rights;
import com.samarthsoft.prabandhak.enums.UserRole;

public class ApplicationSession implements Serializable{
	private static final long serialVersionUID = 1L;
	private Locale applicationLocale;
	private SecurityContext ctx = null;
	private Long lastLoginTime;
	private String schoolGuid;
	private Rights userRights;
	private String userName;
	private String userGuid;
	private UserRole userRole;
	private String associatedClassGuid;
	
	public Locale getApplicationLocale() {
		return applicationLocale;
	}

	public void setApplicationLocale(Locale applicationLocale) {
		this.applicationLocale = applicationLocale;
	}

	public SecurityContext getCtx() {
		return ctx;
	}

	public void setCtx(SecurityContext ctx) {
		this.ctx = ctx;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getSchoolGuid() {
		return schoolGuid;
	}

	public void setSchoolGuid(String schoolGuid) {
		this.schoolGuid = schoolGuid;
	}

	public Rights getUserRights() {
		return userRights;
	}

	public void setUserRights(Rights userRights) {
		this.userRights = userRights;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getAssociatedClassGuid() {
		return associatedClassGuid;
	}

	public void setAssociatedClassGuid(String associatedClassGuid) {
		this.associatedClassGuid = associatedClassGuid;
	}
}