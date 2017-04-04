package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class BasicStudentInfo extends GeneralInformation implements Serializable {
	private static final long serialVersionUID = 1L;
	private String fatherName;
	private String motherName;
	private String guardianName;
	private String caste;
	private String category;
	private String placeOfBirth;
	private String parentsOccupation;
	private Double yearlyIncome;
	private String scholarshipType;
	private String lastAttendedSchoolName;

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
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

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getParentsOccupation() {
		return parentsOccupation;
	}

	public void setParentsOccupation(String parentsOccupation) {
		this.parentsOccupation = parentsOccupation;
	}

	public Double getYearlyIncome() {
		return yearlyIncome;
	}

	public void setYearlyIncome(Double yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}

	public String getScholarshipType() {
		return scholarshipType;
	}

	public void setScholarshipType(String scholarshipType) {
		this.scholarshipType = scholarshipType;
	}

	public void setLastAttendedSchoolName(String lastAttendedSchoolName) {
		this.lastAttendedSchoolName = lastAttendedSchoolName;
	}

	public String getLastAttendedSchoolName() {
		return lastAttendedSchoolName;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
}