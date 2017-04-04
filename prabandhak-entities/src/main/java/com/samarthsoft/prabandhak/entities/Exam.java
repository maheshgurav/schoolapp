package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Exam extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Long examStartDate;
	private Long examEndDate;
	private String standards;
	private String subjectAndMarks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getExamStartDate() {
		return examStartDate;
	}

	public void setExamStartDate(Long examStartDate) {
		this.examStartDate = examStartDate;
	}

	public Long getExamEndDate() {
		return examEndDate;
	}

	public void setExamEndDate(Long examEndDate) {
		this.examEndDate = examEndDate;
	}

	public String getStandards() {
		return standards;
	}

	public void setStandards(String standards) {
		this.standards = standards;
	}

	public String getSubjectAndMarks() {
		return subjectAndMarks;
	}

	public void setSubjectAndMarks(String subjectAndMarks) {
		this.subjectAndMarks = subjectAndMarks;
	}
}