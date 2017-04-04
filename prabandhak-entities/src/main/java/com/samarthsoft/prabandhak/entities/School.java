package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class School implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String guid;
	private String organizationGuid;
	private String name;
	private String address;
	private byte[] imageOrLogo;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public String getOrganizationGuid() {
		return organizationGuid;
	}

	public void setOrganizationGuid(String organizationGuid) {
		this.organizationGuid = organizationGuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte[] getImageOrLogo() {
		return imageOrLogo;
	}

	public void setImageOrLogo(byte[] imageOrLogo) {
		this.imageOrLogo = imageOrLogo;
	}
}