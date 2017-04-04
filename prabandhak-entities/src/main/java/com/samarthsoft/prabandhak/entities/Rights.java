package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Rights implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean createStudent = false;
	private Boolean viewStudent = false;
	private Boolean createTeacher = false;
	private Boolean viewTeacher = false;
	private Boolean generateReports = false;
	private Boolean fillAttendance = false;
	private Boolean fillExamResults = false;
	private Boolean viewOrUpdatesettings = false;
	private Boolean issueBonafideOrLcCertificate = false;
	private Boolean generateGeneralRegister = false;
	
	public Boolean getCreateStudent() {
		return createStudent;
	}

	public void setCreateStudent(Boolean createStudent) {
		this.createStudent = createStudent;
	}

	public Boolean getViewStudent() {
		return viewStudent;
	}

	public void setViewStudent(Boolean viewStudent) {
		this.viewStudent = viewStudent;
	}

	public Boolean getCreateTeacher() {
		return createTeacher;
	}

	public void setCreateTeacher(Boolean createTeacher) {
		this.createTeacher = createTeacher;
	}

	public Boolean getViewTeacher() {
		return viewTeacher;
	}

	public void setViewTeacher(Boolean viewTeacher) {
		this.viewTeacher = viewTeacher;
	}

	public Boolean getGenerateReports() {
		return generateReports;
	}

	public void setGenerateReports(Boolean generateReports) {
		this.generateReports = generateReports;
	}

	public Boolean getFillAttendance() {
		return fillAttendance;
	}

	public void setFillAttendance(Boolean fillAttendance) {
		this.fillAttendance = fillAttendance;
	}

	public Boolean getFillExamResults() {
		return fillExamResults;
	}

	public void setFillExamResults(Boolean fillExamResults) {
		this.fillExamResults = fillExamResults;
	}

	public Boolean getViewOrUpdatesettings() {
		return viewOrUpdatesettings;
	}

	public void setViewOrUpdatesettings(Boolean viewOrUpdatesettings) {
		this.viewOrUpdatesettings = viewOrUpdatesettings;
	}

	public Boolean getIssueBonafideOrLcCertificate() {
		return issueBonafideOrLcCertificate;
	}

	public void setIssueBonafideOrLcCertificate(Boolean issueBonafideOrLcCertificate) {
		this.issueBonafideOrLcCertificate = issueBonafideOrLcCertificate;
	}

	public Boolean getGenerateGeneralRegister() {
		return generateGeneralRegister;
	}

	public void setGenerateGeneralRegister(Boolean generateGeneralRegister) {
		this.generateGeneralRegister = generateGeneralRegister;
	}
}