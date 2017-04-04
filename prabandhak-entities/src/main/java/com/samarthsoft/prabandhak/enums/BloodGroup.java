package com.samarthsoft.prabandhak.enums;

public enum BloodGroup {
	APOSITIVE("A+VE"), ANEGATIVE("A-VE"), BPOSITIVE("B+VE"), BNEGATIVE(
			"B-VE"), ABPOSITIVE("AB+VE"), ABNEGATIVE("AB-VE"), OPOSITIVE(
			"O+VE"), ONEGATIVE("O-VE");

	private final String bloodGroupName;

	private BloodGroup(String bloodGroupName) {
		this.bloodGroupName = bloodGroupName;
	}

	public String getBloodGroupName() {
		return bloodGroupName;
	}
}