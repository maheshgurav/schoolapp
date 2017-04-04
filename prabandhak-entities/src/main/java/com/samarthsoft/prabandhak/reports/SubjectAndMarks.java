package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;

public class SubjectAndMarks implements Serializable {
	private static final long serialVersionUID = 1L;
	private String subjectName;
	private String marks;
	private String outOfMarks;
	private Boolean isPassed;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public String getOutOfMarks() {
		return outOfMarks;
	}

	public void setOutOfMarks(String outOfMarks) {
		this.outOfMarks = outOfMarks;
	}

	public Boolean isPassed() {
		return isPassed;
	}

	public void setPassed(Boolean isPassed) {
		this.isPassed = isPassed;
	}
}