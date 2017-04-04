package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

public class ClassOrDivisionChangeForm extends StudentActivityFormBase implements Serializable {
	private static final long serialVersionUID = 1L;
	private String currentStandard;
	private String currentDivision;
	private String changedStandard;
	private String changedDivision;
	private String dateOfChange;

	public String getCurrentStandard() {
		return currentStandard;
	}

	public void setCurrentStandard(String currentStandard) {
		this.currentStandard = currentStandard;
	}

	public String getCurrentDivision() {
		return currentDivision;
	}

	public void setCurrentDivision(String currentDivision) {
		this.currentDivision = currentDivision;
	}

	public String getChangedStandard() {
		return changedStandard;
	}

	public void setChangedStandard(String changedStandard) {
		this.changedStandard = changedStandard;
	}

	public String getChangedDivision() {
		return changedDivision;
	}

	public void setChangedDivision(String changedDivision) {
		this.changedDivision = changedDivision;
	}

	public String getDateOfChange() {
		return dateOfChange;
	}

	public void setDateOfChange(String dateOfChange) {
		this.dateOfChange = dateOfChange;
	}
}