package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;

public class LeavingCertificateReportObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String permenentRegistrationNumber;
	private String studentName;
	private String fathersName;
	private String mothersName;
	private String caste;
	private String motherTongue;
	private String birthAddress;
	private String dateOfBirth;
	private String dateOfBirthInWord;
	private String nameOfPreviousSchoolAttended;
	private String dateOfJoining;
	private String progress;
	private String conduct;
	private String dateOfLeaving;
	private String reasonOfLeaving;
	private String remark;
	private String standard;
	private String leavingCertificateIssueDateForReport;
	private String isDuplicateCertificate;

	public String getPermenentRegistrationNumber() {
		return permenentRegistrationNumber;
	}

	public void setPermenentRegistrationNumber(
			String permenentRegistrationNumber) {
		this.permenentRegistrationNumber = permenentRegistrationNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
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

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public String getBirthAddress() {
		return birthAddress;
	}

	public void setBirthAddress(String birthAddress) {
		this.birthAddress = birthAddress;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirthInWord() {
		return dateOfBirthInWord;
	}

	public void setDateOfBirthInWord(String dateOfBirthInWord) {
		this.dateOfBirthInWord = dateOfBirthInWord;
	}

	public String getNameOfPreviousSchoolAttended() {
		return nameOfPreviousSchoolAttended;
	}

	public void setNameOfPreviousSchoolAttended(
			String nameOfPreviousSchoolAttended) {
		this.nameOfPreviousSchoolAttended = nameOfPreviousSchoolAttended;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
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

	public String getReasonOfLeaving() {
		return reasonOfLeaving;
	}

	public void setReasonOfLeaving(String reasonOfLeaving) {
		this.reasonOfLeaving = reasonOfLeaving;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getLeavingCertificateIssueDateForReport() {
		return leavingCertificateIssueDateForReport;
	}

	public void setLeavingCertificateIssueDateForReport(
			String leavingCertificateIssueDateForReport) {
		this.leavingCertificateIssueDateForReport = leavingCertificateIssueDateForReport;
	}

	public String getIsDuplicateCertificate() {
		return isDuplicateCertificate;
	}

	public void setIsDuplicateCertificate(String isDuplicateCertificate) {
		this.isDuplicateCertificate = isDuplicateCertificate;
	}
}