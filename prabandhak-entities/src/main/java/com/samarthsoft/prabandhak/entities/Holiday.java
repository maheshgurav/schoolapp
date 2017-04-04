package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Holiday extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Timestamp datedTo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDatedTo() {
		return datedTo;
	}

	public void setDatedTo(Timestamp datedTo) {
		this.datedTo = datedTo;
	}
}
