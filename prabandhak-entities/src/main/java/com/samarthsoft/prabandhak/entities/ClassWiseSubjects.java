package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class ClassWiseSubjects extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	private String subjects;

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
}