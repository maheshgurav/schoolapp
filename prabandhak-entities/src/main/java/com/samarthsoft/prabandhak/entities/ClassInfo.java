package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class ClassInfo extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	private String standardOrClass;
	private String division;
	private String schoolTypeGuid;
	private String classTeacherGuid;
	private String subjectsAndTeachers;

	public String getStandardOrClass() {
		return standardOrClass;
	}

	public void setStandardOrClass(String standardOrClass) {
		this.standardOrClass = standardOrClass;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getSchoolTypeGuid() {
		return schoolTypeGuid;
	}

	public void setSchoolTypeGuid(String schoolTypeGuid) {
		this.schoolTypeGuid = schoolTypeGuid;
	}

	@Override
	public String toString() {
		return standardOrClass + " - " + division;
	}

	public String getClassTeacherGuid() {
		return classTeacherGuid;
	}

	public void setClassTeacherGuid(String classTeacherGuid) {
		this.classTeacherGuid = classTeacherGuid;
	}

	public String getSubjectsAndTeachers() {
		return subjectsAndTeachers;
	}

	public void setSubjectsAndTeachers(String subjectsAndTeachers) {
		this.subjectsAndTeachers = subjectsAndTeachers;
	}
}