package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

public class AttendanceFormObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String date;
	private String absentRollNumbers;
	private String attendanceForClass;

	public AttendanceFormObject() {
		super();
	}
	
	public AttendanceFormObject(String date) {
		super();
		this.date = date;
	}
	
	public AttendanceFormObject(String date, String absentRollNumbers,String attendanceForClass) {
		super();
		this.date = date;
		this.absentRollNumbers = absentRollNumbers;
		this.attendanceForClass = attendanceForClass;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAbsentRollNumbers() {
		return absentRollNumbers;
	}

	public void setAbsentRollNumbers(String absentRollNumbers) {
		this.absentRollNumbers = absentRollNumbers;
	}

	public String getAttendanceForClass() {
		return attendanceForClass;
	}

	public void setAttendanceForClass(String attendanceForClass) {
		this.attendanceForClass = attendanceForClass;
	}
}