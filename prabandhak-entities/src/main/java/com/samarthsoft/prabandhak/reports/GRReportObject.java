package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;

public class GRReportObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String generalRegisterNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mothersName;
	private String caste;
	private String nationality;
	private String placeOfBirth;
	private String motherTongue;
	private String dateOfBirthInNameAndNumber;
	private String lastSchoolAttended;
	private String dateOfAdmission;
	private String standardAndClassIntoWhichAdmitted;
	private String progress;
	private String conduct;
	private String dateOfLeaving;
	private String standardIntoWhichLeftFromSchool;
	private String remarkOrReason;
	
	public String getGeneralRegisterNumber() {
		return generalRegisterNumber;
	}

	public void setGeneralRegisterNumber(String generalRegisterNumber) {
		this.generalRegisterNumber = generalRegisterNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public String getDateOfBirthInNameAndNumber() {
		return dateOfBirthInNameAndNumber;
	}

	public void setDateOfBirthInNameAndNumber(String dateOfBirthInNameAndNumber) {
		this.dateOfBirthInNameAndNumber = dateOfBirthInNameAndNumber;
	}

	public String getLastSchoolAttended() {
		return lastSchoolAttended;
	}

	public void setLastSchoolAttended(String lastSchoolAttended) {
		this.lastSchoolAttended = lastSchoolAttended;
	}

	public String getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(String dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public String getStandardAndClassIntoWhichAdmitted() {
		return standardAndClassIntoWhichAdmitted;
	}

	public void setStandardAndClassIntoWhichAdmitted(
			String standardAndClassIntoWhichAdmitted) {
		this.standardAndClassIntoWhichAdmitted = standardAndClassIntoWhichAdmitted;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getConduct() {
		return conduct;
	}

	public void setConduct(String conduct) {
		this.conduct = conduct;
	}

	public String getDateOfLeaving() {
		return dateOfLeaving;
	}

	public void setDateOfLeaving(String dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

	public String getStandardIntoWhichLeftFromSchool() {
		return standardIntoWhichLeftFromSchool;
	}

	public void setStandardIntoWhichLeftFromSchool(
			String standardIntoWhichLeftFromSchool) {
		this.standardIntoWhichLeftFromSchool = standardIntoWhichLeftFromSchool;
	}

	public String getRemarkOrReason() {
		return remarkOrReason;
	}

	public void setRemarkOrReason(String remarkOrReason) {
		this.remarkOrReason = remarkOrReason;
	}
}