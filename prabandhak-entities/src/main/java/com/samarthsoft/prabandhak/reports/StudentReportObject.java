package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;

public class StudentReportObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentName;
	private String studentClassAndDivision;
	private String studentBirthDate;
	private String studentSchoolJoiningDate;
	private String casteAndSubcaste;
	private String scholarshipName;
	private Integer recordNumber;
	private String generalRegisterNumber;
	
	private String classAndDivision;
	private String rollNumber;
	private String bloodGroup;
	private String address;
	private String dateOfBirth;
	private String iCardForYear;
	
	public String getGeneralRegisterNumber() {
		return generalRegisterNumber;
	}

	public void setGeneralRegisterNumber(String generalRegisterNumber) {
		this.generalRegisterNumber = generalRegisterNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentClassAndDivision() {
		return studentClassAndDivision;
	}

	public void setStudentClassAndDivision(String studentClassAndDivision) {
		this.studentClassAndDivision = studentClassAndDivision;
	}

	public String getStudentBirthDate() {
		return studentBirthDate;
	}

	public void setStudentBirthDate(String studentBirthDate) {
		this.studentBirthDate = studentBirthDate;
	}

	public String getStudentSchoolJoiningDate() {
		return studentSchoolJoiningDate;
	}

	public void setStudentSchoolJoiningDate(String studentSchoolJoiningDate) {
		this.studentSchoolJoiningDate = studentSchoolJoiningDate;
	}

	public String getCasteAndSubcaste() {
		return casteAndSubcaste;
	}

	public void setCasteAndSubcaste(String casteAndSubcaste) {
		this.casteAndSubcaste = casteAndSubcaste;
	}

	public String getScholarshipName() {
		return scholarshipName;
	}

	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}

	public Integer getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(Integer recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getClassAndDivision() {
		return classAndDivision;
	}

	public void setClassAndDivision(String classAndDivision) {
		this.classAndDivision = classAndDivision;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getiCardForYear() {
		return iCardForYear;
	}

	public void setiCardForYear(String iCardForYear) {
		this.iCardForYear = iCardForYear;
	}
}