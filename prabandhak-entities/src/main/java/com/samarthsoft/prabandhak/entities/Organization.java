package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Organization implements Serializable {

	private static final long serialVersionUID = 1L;
	private String guid;
	private String schoolGuid;
	private String organizationGuid;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getSchoolGuid() {
		return schoolGuid;
	}

	public void setSchoolGuid(String schoolGuid) {
		this.schoolGuid = schoolGuid;
	}

	public String getOrganizationGuid() {
		return organizationGuid;
	}

	public void setOrganizationGuid(String organizationGuid) {
		this.organizationGuid = organizationGuid;
	}
}