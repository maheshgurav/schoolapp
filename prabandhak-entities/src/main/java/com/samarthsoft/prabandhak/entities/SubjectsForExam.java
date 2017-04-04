package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.enums.SubjectEvaluationType;

public class SubjectsForExam implements Serializable {
	private static final long serialVersionUID = 1L;
	private String guid;
	private String subjectGuid;
	private SubjectEvaluationType subjectEvaluationType;
	private String marksOrGradesRequiredForPassing;
	private String examGuid;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getSubjectGuid() {
		return subjectGuid;
	}

	public void setSubjectGuid(String subjectGuid) {
		this.subjectGuid = subjectGuid;
	}

	public SubjectEvaluationType getSubjectEvaluationType() {
		return subjectEvaluationType;
	}

	public void setSubjectEvaluationType(
			SubjectEvaluationType subjectEvaluationType) {
		this.subjectEvaluationType = subjectEvaluationType;
	}

	public String getMarksOrGradesRequiredForPassing() {
		return marksOrGradesRequiredForPassing;
	}

	public void setMarksOrGradesRequiredForPassing(
			String marksOrGradesRequiredForPassing) {
		this.marksOrGradesRequiredForPassing = marksOrGradesRequiredForPassing;
	}

	public String getExamGuid() {
		return examGuid;
	}

	public void setExamGuid(String examGuid) {
		this.examGuid = examGuid;
	}
}