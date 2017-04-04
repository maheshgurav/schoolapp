package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class ExamResultKey implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentGuid;
	private String examGuid;
	private String subjectGuid;
	private String standardAndDivision;

	public String getStudentGuid() {
		return studentGuid;
	}

	public void setStudentGuid(String studentGuid) {
		this.studentGuid = studentGuid;
	}

	public String getExamGuid() {
		return examGuid;
	}

	public void setExamGuid(String examGuid) {
		this.examGuid = examGuid;
	}

	public String getSubjectGuid() {
		return subjectGuid;
	}

	public void setSubjectGuid(String subjectGuid) {
		this.subjectGuid = subjectGuid;
	}

	public String getStandardAndDivision() {
		return standardAndDivision;
	}

	public void setStandardAndDivision(String standardAndDivision) {
		this.standardAndDivision = standardAndDivision;
	}
}