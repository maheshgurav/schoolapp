package com.samarthsoft.prabandhak.enums;

public enum Gender {
	Male("0"), Female("1");

	private final String genderId;

	private Gender(String genderId) {
		this.genderId = genderId;
	}

	public String getGenderString() {
		return genderId;
	}

}
