package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class UserRights extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String rights;

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

}
