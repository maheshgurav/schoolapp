package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class StudentAndMark implements Serializable {
	private static final long serialVersionUID = 1L;
	private String guid;
	private String marks;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}
}