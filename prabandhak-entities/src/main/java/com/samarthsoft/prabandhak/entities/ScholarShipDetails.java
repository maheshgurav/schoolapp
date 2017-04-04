package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class ScholarShipDetails extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentGuid;
	private String scholarShipGuid;
	private Long formSubmissionDate;
	private Long senctionedDate;

	public String getStudentGuid() {
		return studentGuid;
	}

	public void setStudentGuid(String studentGuid) {
		this.studentGuid = studentGuid;
	}

	public String getScholarShipGuid() {
		return scholarShipGuid;
	}

	public void setScholarShipGuid(String scholarShipGuid) {
		this.scholarShipGuid = scholarShipGuid;
	}

	public Long getFormSubmissionDate() {
		return formSubmissionDate;
	}

	public void setFormSubmissionDate(Long formSubmissionDate) {
		this.formSubmissionDate = formSubmissionDate;
	}

	public Long getSenctionedDate() {
		return senctionedDate;
	}

	public void setSenctionedDate(Long senctionedDate) {
		this.senctionedDate = senctionedDate;
	}

}
