package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Tehsil implements Serializable {
	private static final long serialVersionUID = 1L;
	private String guid;
	private String name;
	private String districtGuid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrictGuid() {
		return districtGuid;
	}

	public void setDistrictGuid(String districtGuid) {
		this.districtGuid = districtGuid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}
}
