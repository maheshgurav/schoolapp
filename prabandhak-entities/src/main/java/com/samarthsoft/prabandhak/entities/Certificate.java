package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Certificate implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Float feePerCertificate;
	private String guid;

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getFeePerCertificate() {
		return feePerCertificate;
	}

	public void setFeePerCertificate(Float feePerCertificate) {
		this.feePerCertificate = feePerCertificate;
	}

}
