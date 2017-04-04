package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class RemovedStudentHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentGuid;
	private Long dateOfRemoving;
	private String reasonOfRemovingFromCatalog;

	public String getStudentGuid() {
		return studentGuid;
	}

	public void setStudentGuid(String studentGuid) {
		this.studentGuid = studentGuid;
	}

	public Long getDateOfRemoving() {
		return dateOfRemoving;
	}

	public void setDateOfRemoving(Long dateOfRemoving) {
		this.dateOfRemoving = dateOfRemoving;
	}

	public String getReasonOfRemovingFromCatalog() {
		return reasonOfRemovingFromCatalog;
	}

	public void setReasonOfRemovingFromCatalog(String reasonOfRemovingFromCatalog) {
		this.reasonOfRemovingFromCatalog = reasonOfRemovingFromCatalog;
	}
}