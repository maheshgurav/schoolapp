package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class AttendanceKey implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long absentOn;
	private String studentGuid;
	
	public AttendanceKey() {
		super();
	}

	public AttendanceKey(Long absentOn, String studentGuid) {
		super();
		this.absentOn = absentOn;
		this.studentGuid = studentGuid;
	}

	public Long getAbsentOn() {
		return absentOn;
	}

	public void setAbsentOn(Long absentOn) {
		this.absentOn = absentOn;
	}

	public String getStudentGuid() {
		return studentGuid;
	}

	public void setStudentGuid(String studentGuid) {
		this.studentGuid = studentGuid;
	}
}