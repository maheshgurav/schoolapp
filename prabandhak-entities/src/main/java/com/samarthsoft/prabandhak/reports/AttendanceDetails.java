package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;

public class AttendanceDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String presentOrAbsent;
	private String date;

	public String getPresentOrAbsent() {
		return presentOrAbsent;
	}

	public void setPresentOrAbsent(String presentOrAbsent) {
		this.presentOrAbsent = presentOrAbsent;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}