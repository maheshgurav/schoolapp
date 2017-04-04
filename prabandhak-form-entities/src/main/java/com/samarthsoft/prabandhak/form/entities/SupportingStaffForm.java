package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.entities.SupportStaff;

public class SupportingStaffForm extends EmployeeBaseForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private SupportStaff supportStaff;

	public SupportStaff getSupportStaff() {
		return supportStaff;
	}

	public void setSupportStaff(SupportStaff supportStaff) {
		this.supportStaff = supportStaff;
	}
}