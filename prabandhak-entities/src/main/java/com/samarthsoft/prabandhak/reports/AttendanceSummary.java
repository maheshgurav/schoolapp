package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;
import java.util.List;

public class AttendanceSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	private String classTeacherName;
	private String standard;
	private String division;
	private Integer totalStudents;
	private Integer totalBoys;
	private Integer totalGirls;
	private String attendanceForMonth;
	private List<StudentCountByGender> studentCountByGender;
	private List<StudentClassificationForAttendance> studentClassificationForAttendance;
	private List<StudentCountByGender> casteWiseStudentCountByGenderOnMonthStart;
	private List<StudentCountByGender> casteWiseStudentCountByGenderOnMonthEnd;

	public String getClassTeacherName() {
		return classTeacherName;
	}

	public void setClassTeacherName(String classTeacherName) {
		this.classTeacherName = classTeacherName;
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

	public Integer getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(Integer totalStudents) {
		this.totalStudents = totalStudents;
	}

	public Integer getTotalBoys() {
		return totalBoys;
	}

	public void setTotalBoys(Integer totalBoys) {
		this.totalBoys = totalBoys;
	}

	public Integer getTotalGirls() {
		return totalGirls;
	}

	public void setTotalGirls(Integer totalGirls) {
		this.totalGirls = totalGirls;
	}

	public String getAttendanceForMonth() {
		return attendanceForMonth;
	}

	public void setAttendanceForMonth(String attendanceForMonth) {
		this.attendanceForMonth = attendanceForMonth;
	}

	public List<StudentCountByGender> getStudentCountByGender() {
		return studentCountByGender;
	}

	public void setStudentCountByGender(List<StudentCountByGender> studentCountByGender) {
		this.studentCountByGender = studentCountByGender;
	}

	public List<StudentClassificationForAttendance> getStudentClassificationForAttendance() {
		return studentClassificationForAttendance;
	}

	public void setStudentClassificationForAttendance(
			List<StudentClassificationForAttendance> studentClassificationForAttendance) {
		this.studentClassificationForAttendance = studentClassificationForAttendance;
	}

	public List<StudentCountByGender> getCasteWiseStudentCountByGenderOnMonthStart() {
		return casteWiseStudentCountByGenderOnMonthStart;
	}

	public void setCasteWiseStudentCountByGenderOnMonthStart(
			List<StudentCountByGender> casteWiseStudentCountByGenderOnMonthStart) {
		this.casteWiseStudentCountByGenderOnMonthStart = casteWiseStudentCountByGenderOnMonthStart;
	}

	public List<StudentCountByGender> getCasteWiseStudentCountByGenderOnMonthEnd() {
		return casteWiseStudentCountByGenderOnMonthEnd;
	}

	public void setCasteWiseStudentCountByGenderOnMonthEnd(
			List<StudentCountByGender> casteWiseStudentCountByGenderOnMonthEnd) {
		this.casteWiseStudentCountByGenderOnMonthEnd = casteWiseStudentCountByGenderOnMonthEnd;
	}
}