package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class SupportStaff extends StaffAndTeacherCommon implements Serializable {
	private static final long serialVersionUID = 1L;

	private String qualification;
	private String designation;

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
}