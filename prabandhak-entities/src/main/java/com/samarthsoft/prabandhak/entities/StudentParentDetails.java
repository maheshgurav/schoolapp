package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class StudentParentDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private String guid;
	private Name nameOfFather;
	private Name nameOfMother;
	private Address permanentAddress;
	private String phoneNumber;
	private String occupation;
	private Double yearlyIncome;
	private Long birthDateOfFather;
	private Long birthDateOfMother;
	private String emailId;
	
	private String studentGuid;
	private String dateOfBirthOfFather;
	private String dateOfBirthOfMother;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Name getNameOfFather() {
		return nameOfFather;
	}

	public void setNameOfFather(Name nameOfFather) {
		this.nameOfFather = nameOfFather;
	}

	public Name getNameOfMother() {
		return nameOfMother;
	}

	public void setNameOfMother(Name nameOfMother) {
		this.nameOfMother = nameOfMother;
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

	public Long getBirthDateOfFather() {
		return birthDateOfFather;
	}

	public void setBirthDateOfFather(Long birthDateOfFather) {
		this.birthDateOfFather = birthDateOfFather;
	}

	public Long getBirthDateOfMother() {
		return birthDateOfMother;
	}

	public void setBirthDateOfMother(Long birthDateOfMother) {
		this.birthDateOfMother = birthDateOfMother;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getStudentGuid() {
		return studentGuid;
	}

	public void setStudentGuid(String studentGuid) {
		this.studentGuid = studentGuid;
	}

	public String getDateOfBirthOfFather() {
		return dateOfBirthOfFather;
	}

	public void setDateOfBirthOfFather(String dateOfBirthOfFather) {
		this.dateOfBirthOfFather = dateOfBirthOfFather;
	}

	public String getDateOfBirthOfMother() {
		return dateOfBirthOfMother;
	}

	public void setDateOfBirthOfMother(String dateOfBirthOfMother) {
		this.dateOfBirthOfMother = dateOfBirthOfMother;
	}
}