package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class SupportingStaffDesignations implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Boolean hasAssociatedLoginInformation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getHasAssociatedLoginInformation() {
		return hasAssociatedLoginInformation;
	}

	public void setHasAssociatedLoginInformation(
			Boolean hasAssociatedLoginInformation) {
		this.hasAssociatedLoginInformation = hasAssociatedLoginInformation;
	}
}