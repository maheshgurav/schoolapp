package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.enums.Gender;

public class Student extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	private String PRN;
	private String rollNumber;
	private Name name;
	private String firstNameOfMother;
	private String caste;
	private String category;
	private String scholarshipName;
	private Long dateOfBirth;
	private Address placeOfBirth;
	private String standard;
	private String division;
	private String addmissionTakenInclass;
	private String nameOfLastAttendedSchool;
	private String nationality;
	private String motherTongue;
	private String bloodGroup;
	private Long dateOfAdmission;
	private Gender gender;
	private Boolean isAlumni;
	private String parentGuid;

	public String getPRN() {
		return PRN;
	}

	public void setPRN(String pRN) {
		PRN = pRN;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getFirstNameOfMother() {
		return firstNameOfMother;
	}

	public void setFirstNameOfMother(String firstNameOfMother) {
		this.firstNameOfMother = firstNameOfMother;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getScholarshipName() {
		return scholarshipName;
	}

	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}

	public Long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(Address placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getAddmissionTakenInclass() {
		return addmissionTakenInclass;
	}

	public void setAddmissionTakenInclass(String addmissionTakenInclass) {
		this.addmissionTakenInclass = addmissionTakenInclass;
	}

	public String getNameOfLastAttendedSchool() {
		return nameOfLastAttendedSchool;
	}

	public void setNameOfLastAttendedSchool(String nameOfLastAttendedSchool) {
		this.nameOfLastAttendedSchool = nameOfLastAttendedSchool;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Long getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Long dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Boolean getIsAlumni() {
		return isAlumni;
	}

	public void setIsAlumni(Boolean isAlumni) {
		this.isAlumni = isAlumni;
	}

	public String getParentGuid() {
		return parentGuid;
	}

	public void setParentGuid(String parentGuid) {
		this.parentGuid = parentGuid;
	}
}