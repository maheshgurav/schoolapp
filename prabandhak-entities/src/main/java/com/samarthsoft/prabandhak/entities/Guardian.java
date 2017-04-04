package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Guardian implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentId;
	private Name name;
	private Address permanentAddress;
	private String phoneNumber;
	private String occupation;
	private Double yearlyIncome;
	private Long birthDateOfGuardian;
	private String emailId;
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Double getYearlyIncome() {
		return yearlyIncome;
	}

	public void setYearlyIncome(Double yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}

	public Long getBirthDateOfGuardian() {
		return birthDateOfGuardian;
	}

	public void setBirthDateOfGuardian(Long birthDateOfGuardian) {
		this.birthDateOfGuardian = birthDateOfGuardian;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}