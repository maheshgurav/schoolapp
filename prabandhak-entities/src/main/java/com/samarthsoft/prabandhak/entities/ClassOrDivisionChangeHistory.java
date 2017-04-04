package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class ClassOrDivisionChangeHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String studentId;
	private String previousStandard;
	private String previousDivision;
	private String newStandard;
	private String newDivision;
	private Long dateOfChange;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getPreviousStandard() {
		return previousStandard;
	}

	public void setPreviousStandard(String previousStandard) {
		this.previousStandard = previousStandard;
	}

	public String getPreviousDivision() {
		return previousDivision;
	}

	public void setPreviousDivision(String previousDivision) {
		this.previousDivision = previousDivision;
	}

	public String getNewStandard() {
		return newStandard;
	}

	public void setNewStandard(String newStandard) {
		this.newStandard = newStandard;
	}

	public String getNewDivision() {
		return newDivision;
	}

	public void setNewDivision(String newDivision) {
		this.newDivision = newDivision;
	}

	public Long getDateOfChange() {
		return dateOfChange;
	}

	public void setDateOfChange(Long dateOfChange) {
		this.dateOfChange = dateOfChange;
	}
}