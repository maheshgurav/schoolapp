package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class SelectValues implements Serializable {
	private static final long serialVersionUID = 1L;

	public SelectValues() {
		super();
	}

	public SelectValues(String projectionPropertyName) {
		super();
		this.projectionPropertyName = projectionPropertyName;
	}

	private String projectionPropertyName;

	public String getProjectionPropertyName() {
		return projectionPropertyName;
	}

	public void setProjectionPropertyName(String projectionPropertyName) {
		this.projectionPropertyName = projectionPropertyName;
	}
}