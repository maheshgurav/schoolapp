package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class ScholarshipType extends BasicInfo implements Serializable {
	private Boolean isDefaultForAttendanceReport;
	
	public Boolean getIsDefaultForAttendanceReport() {
		return isDefaultForAttendanceReport;
	}

	public void setIsDefaultForAttendanceReport(Boolean isDefaultForAttendanceReport) {
		this.isDefaultForAttendanceReport = isDefaultForAttendanceReport;
	}

	private static final long serialVersionUID = 1L;
}