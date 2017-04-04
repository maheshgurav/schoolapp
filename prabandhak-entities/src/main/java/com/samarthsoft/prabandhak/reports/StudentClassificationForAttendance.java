package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;

public class StudentClassificationForAttendance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	private String feeTypeName;
	private Double fee;
	private Integer numberOfStudentsOnMonthStart;
	private Integer numberOfStudentsOnMonthEnd;
	private Integer newAdmissions;
	private Integer passedAndAdmittedToClass;
	private Integer divisionChangeAndAdmittedToClass;
	private Integer removedFromClass;
	private Integer passedAndAdmittedToNextClass;
	private Integer divisionChangeAndAdmittedToNextClass;

	public String getFeeTypeName() {
		return feeTypeName;
	}

	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getNumberOfStudentsOnMonthStart() {
		return numberOfStudentsOnMonthStart;
	}

	public void setNumberOfStudentsOnMonthStart(Integer numberOfStudentsOnMonthStart) {
		this.numberOfStudentsOnMonthStart = numberOfStudentsOnMonthStart;
	}

	public Integer getNumberOfStudentsOnMonthEnd() {
		return numberOfStudentsOnMonthEnd;
	}

	public void setNumberOfStudentsOnMonthEnd(Integer numberOfStudentsOnMonthEnd) {
		this.numberOfStudentsOnMonthEnd = numberOfStudentsOnMonthEnd;
	}

	public Integer getNewAdmissions() {
		return newAdmissions;
	}

	public void setNewAdmissions(Integer newAdmissions) {
		this.newAdmissions = newAdmissions;
	}

	public Integer getPassedAndAdmittedToClass() {
		return passedAndAdmittedToClass;
	}

	public void setPassedAndAdmittedToClass(Integer passedAndAdmittedToClass) {
		this.passedAndAdmittedToClass = passedAndAdmittedToClass;
	}

	public Integer getDivisionChangeAndAdmittedToClass() {
		return divisionChangeAndAdmittedToClass;
	}

	public void setDivisionChangeAndAdmittedToClass(Integer divisionChangeAndAdmittedToClass) {
		this.divisionChangeAndAdmittedToClass = divisionChangeAndAdmittedToClass;
	}

	public Integer getRemovedFromClass() {
		return removedFromClass;
	}

	public void setRemovedFromClass(Integer removedFromClass) {
		this.removedFromClass = removedFromClass;
	}

	public Integer getPassedAndAdmittedToNextClass() {
		return passedAndAdmittedToNextClass;
	}

	public void setPassedAndAdmittedToNextClass(Integer passedAndAdmittedToNextClass) {
		this.passedAndAdmittedToNextClass = passedAndAdmittedToNextClass;
	}

	public Integer getDivisionChangeAndAdmittedToNextClass() {
		return divisionChangeAndAdmittedToNextClass;
	}

	public void setDivisionChangeAndAdmittedToNextClass(Integer divisionChangeAndAdmittedToNextClass) {
		this.divisionChangeAndAdmittedToNextClass = divisionChangeAndAdmittedToNextClass;
	}
}