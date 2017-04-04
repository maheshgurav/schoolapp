package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

public class RemoveStudentForm extends StudentActivityFormBase implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private String reasonOfRemoving;
	private String dateOfRemoving;

	public String getReasonOfRemoving() {
		return reasonOfRemoving;
	}

	public void setReasonOfRemoving(String reasonOfRemoving) {
		this.reasonOfRemoving = reasonOfRemoving;
	}

	public String getDateOfRemoving() {
		return dateOfRemoving;
	}

	public void setDateOfRemoving(String dateOfRemoving) {
		this.dateOfRemoving = dateOfRemoving;
	}
}