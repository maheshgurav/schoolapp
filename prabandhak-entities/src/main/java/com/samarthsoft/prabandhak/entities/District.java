package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class District implements Serializable {
	private static final long serialVersionUID = 1L;
	private String guid;
	private String name;
	private String stateGuid;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getStateGuid() {
		return stateGuid;
	}

	public void setStateGuid(String stateGuid) {
		this.stateGuid = stateGuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
