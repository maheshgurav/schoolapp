package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

public class SubjectFormObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String subjectCode;
	private String subjectName;
	private String guid;
	private Boolean isTakenByStudent;

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Boolean getIsTakenByStudent() {
		return isTakenByStudent;
	}

	public void setIsTakenByStudent(Boolean isTakenByStudent) {
		this.isTakenByStudent = isTakenByStudent;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}