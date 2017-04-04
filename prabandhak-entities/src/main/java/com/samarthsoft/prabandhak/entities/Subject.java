package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String guid;
	private String name;
	private String code;
	private String defaultForClasses;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDefaultForClasses() {
		return defaultForClasses;
	}

	public void setDefaultForClasses(String defaultForClasses) {
		this.defaultForClasses = defaultForClasses;
	}
}