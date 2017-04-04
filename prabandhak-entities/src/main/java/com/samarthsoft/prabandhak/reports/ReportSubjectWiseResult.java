package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;

public class ReportSubjectWiseResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentName;
	private String rollNumber;
	private String mark;
	private String passedOrFail;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getPassedOrFail() {
		return passedOrFail;
	}

	public void setPassedOrFail(String passedOrFail) {
		this.passedOrFail = passedOrFail;
	}
}