package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class IssuedCertificateDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentGuid;
	private String certificateGuid;
	private Timestamp dateOfIssue;

	public String getStudentGuid() {
		return studentGuid;
	}

	public void setStudentGuid(String studentGuid) {
		this.studentGuid = studentGuid;
	}

	public String getCertificateGuid() {
		return certificateGuid;
	}

	public void setCertificateGuid(String certificateGuid) {
		this.certificateGuid = certificateGuid;
	}

	public Timestamp getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Timestamp dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

}
