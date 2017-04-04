package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;

public class BonifideCertificateReportObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String caste;
	private String birthAddress;
	private String dateOfBirthInWords;
	private String dateOfBirthInDigits;
	private String bonafideIssueDate;
	private String registerNumber;
	private String classAndDivision;
	private String year;
	private String studentName;

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getBirthAddress() {
		return birthAddress;
	}

	public void setBirthAddress(String birthAddress) {
		this.birthAddress = birthAddress;
	}

	public String getDateOfBirthInWords() {
		return dateOfBirthInWords;
	}

	public void setDateOfBirthInWords(String dateOfBirthInWords) {
		this.dateOfBirthInWords = dateOfBirthInWords;
	}

	public String getDateOfBirthInDigits() {
		return dateOfBirthInDigits;
	}

	public void setDateOfBirthInDigits(String dateOfBirthInDigits) {
		this.dateOfBirthInDigits = dateOfBirthInDigits;
	}

	public String getBonafideIssueDate() {
		return bonafideIssueDate;
	}

	public void setBonafideIssueDate(String bonafideIssueDate) {
		this.bonafideIssueDate = bonafideIssueDate;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public String getClassAndDivision() {
		return classAndDivision;
	}

	public void setClassAndDivision(String classAndDivision) {
		this.classAndDivision = classAndDivision;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}