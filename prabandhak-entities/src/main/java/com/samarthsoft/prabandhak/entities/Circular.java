package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Circular extends Organization implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private byte[] actualCircular;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getActualCircular() {
		return actualCircular;
	}

	public void setActualCircular(byte[] actualCircular) {
		this.actualCircular = actualCircular;
	}
}