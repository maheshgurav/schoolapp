package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

public class ExamFormObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String classGuid;
	private String subjectGuid;
	private String examGuid;
	private String marks;
	
	public String getClassGuid() {
		return classGuid;
	}

	public void setClassGuid(String classGuid) {
		this.classGuid = classGuid;
	}

	public String getSubjectGuid() {
		return subjectGuid;
	}

	public void setSubjectGuid(String subjectGuid) {
		this.subjectGuid = subjectGuid;
	}

	public String getExamGuid() {
		return examGuid;
	}

	public void setExamGuid(String examGuid) {
		this.examGuid = examGuid;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}
}