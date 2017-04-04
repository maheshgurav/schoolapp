package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;
import java.util.List;

public class ReportCard implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentName;
	private String rollNumber;
	private String examName;
	private Float percentageMarks;
	private String passingGrade;
	private List<SubjectAndMarks> subjectAndMarks;

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

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public List<SubjectAndMarks> getSubjectAndMarks() {
		return subjectAndMarks;
	}

	public void setSubjectAndMarks(List<SubjectAndMarks> subjectAndMarks) {
		this.subjectAndMarks = subjectAndMarks;
	}

	public Float getPercentageMarks() {
		return percentageMarks;
	}

	public void setPercentageMarks(Float percentageMarks) {
		this.percentageMarks = percentageMarks;
	}

	public String getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}
}