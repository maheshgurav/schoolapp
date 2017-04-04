package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

public class StudentActivityFormBase implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentGuid;
	private String studentName;

	public StudentActivityFormBase() {
		super();
	}

	public StudentActivityFormBase(String studentName, String studentGuid) {
		super();
		this.studentName = studentName;
		this.studentGuid = studentGuid;
	}

	public String getStudentGuid() {
		return studentGuid;
	}

	public void setStudentGuid(String studentGuid) {
		this.studentGuid = studentGuid;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}