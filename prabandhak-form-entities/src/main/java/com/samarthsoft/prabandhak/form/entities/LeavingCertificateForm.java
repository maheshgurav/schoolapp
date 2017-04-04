package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.entities.LeavingCertificate;

public class LeavingCertificateForm extends StudentActivityFormBase implements Serializable {
	private static final long serialVersionUID = 1L;
	private String leavingDate;
	private LeavingCertificate leavingCertificate;
	
	public String getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(String leavingDate) {
		this.leavingDate = leavingDate;
	}

	public LeavingCertificate getLeavingCertificate() {
		return leavingCertificate;
	}

	public void setLeavingCertificate(LeavingCertificate leavingCertificate) {
		this.leavingCertificate = leavingCertificate;
	}
}