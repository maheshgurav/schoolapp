package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class BasicInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String guid;
	protected String name;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}