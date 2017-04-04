package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Name implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String middleName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public String toString() {
		return firstName + " " + middleName + " " + lastName;
	}
	
	public String toStringSurnameFirst() {
		return lastName + " " + firstName + " " + middleName;
	}
}