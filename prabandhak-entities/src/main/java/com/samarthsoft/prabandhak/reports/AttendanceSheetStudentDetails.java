package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;
import java.util.List;

public class AttendanceSheetStudentDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentRollNumber;
	private String studentName;
	private List<AttendanceDetails> attendanceInfo;
	private String generalRegisterNumber;
	private String dateOfBirth;
	private String caste;
	private String feeDetails;
	private String subcaste;
	private String numberOfDaysStudentPresentInMonth;
	private String numberOfDaysStudentPresentInYear;
	private String totalNumberDaysStudentPresent;
	private String remark;

	public String getStudentRollNumber() {
		return studentRollNumber;
	}

	public void setStudentRollNumber(String studentRollNumber) {
		this.studentRollNumber = studentRollNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<AttendanceDetails> getAttendanceInfo() {
		return attendanceInfo;
	}

	public void setAttendanceInfo(List<AttendanceDetails> attendanceInfo) {
		this.attendanceInfo = attendanceInfo;
	}

	public String getGeneralRegisterNumber() {
		return generalRegisterNumber;
	}

	public void setGeneralRegisterNumber(String generalRegisterNumber) {
		this.generalRegisterNumber = generalRegisterNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getFeeDetails() {
		return feeDetails;
	}

	public void setFeeDetails(String feeDetails) {
		this.feeDetails = feeDetails;
	}

	public String getSubcaste() {
		return subcaste;
	}

	public void setSubcaste(String subcaste) {
		this.subcaste = subcaste;
	}

	public String getNumberOfDaysStudentPresentInMonth() {
		return numberOfDaysStudentPresentInMonth;
	}

	public void setNumberOfDaysStudentPresentInMonth(
			String numberOfDaysStudentPresentInMonth) {
		this.numberOfDaysStudentPresentInMonth = numberOfDaysStudentPresentInMonth;
	}

	public String getNumberOfDaysStudentPresentInYear() {
		return numberOfDaysStudentPresentInYear;
	}

	public void setNumberOfDaysStudentPresentInYear(
			String numberOfDaysStudentPresentInYear) {
		this.numberOfDaysStudentPresentInYear = numberOfDaysStudentPresentInYear;
	}

	public String getTotalNumberDaysStudentPresent() {
		return totalNumberDaysStudentPresent;
	}

	public void setTotalNumberDaysStudentPresent(
			String totalNumberDaysStudentPresent) {
		this.totalNumberDaysStudentPresent = totalNumberDaysStudentPresent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}