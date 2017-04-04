package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;
import java.util.List;

import com.samarthsoft.prabandhak.entities.SubjectAndMarks;
import com.samarthsoft.prabandhak.entities.Exam;

public class ExamSettingsForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Exam exam;
	private String startDateOfExam;
	private String endDateOfExam;
	private List<String> examForStandards;
	private List<SubjectAndMarks> subjectAndMarks;
	private String enteredSubjectAndMarks;

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getStartDateOfExam() {
		return startDateOfExam;
	}

	public void setStartDateOfExam(String startDateOfExam) {
		this.startDateOfExam = startDateOfExam;
	}

	public String getEndDateOfExam() {
		return endDateOfExam;
	}

	public void setEndDateOfExam(String endDateOfExam) {
		this.endDateOfExam = endDateOfExam;
	}
	
	public List<String> getExamForStandards() {
		return examForStandards;
	}

	public void setExamForStandards(List<String> examForStandards) {
		this.examForStandards = examForStandards;
	}

	public List<SubjectAndMarks> getSubjectAndMarks() {
		return subjectAndMarks;
	}

	public void setSubjectAndMarks(List<SubjectAndMarks> subjectAndMarks) {
		this.subjectAndMarks = subjectAndMarks;
	}

	public String getEnteredSubjectAndMarks() {
		return enteredSubjectAndMarks;
	}

	public void setEnteredSubjectAndMarks(String enteredSubjectAndMarks) {
		this.enteredSubjectAndMarks = enteredSubjectAndMarks;
	}
}