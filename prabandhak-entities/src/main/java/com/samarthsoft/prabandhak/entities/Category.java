package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Category extends BasicInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean isDefaultForAttendanceReport;

	public Boolean getIsDefaultForAttendanceReport() {
		return isDefaultForAttendanceReport;
	}

	public void setIsDefaultForAttendanceReport(Boolean isDefaultForAttendanceReport) {
		this.isDefaultForAttendanceReport = isDefaultForAttendanceReport;
	}
	
	@Override
	public String toString() {
		return name;
	}
}