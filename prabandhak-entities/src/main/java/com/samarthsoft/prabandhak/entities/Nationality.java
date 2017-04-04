package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Nationality implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}