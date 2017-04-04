package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class ExamResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private ExamResultKey examResultKey;
	private String marks;

	public ExamResultKey getExamResultKey() {
		return examResultKey;
	}

	public void setExamResultKey(ExamResultKey examResultKey) {
		this.examResultKey = examResultKey;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}
}