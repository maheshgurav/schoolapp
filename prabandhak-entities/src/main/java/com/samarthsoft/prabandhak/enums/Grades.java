package com.samarthsoft.prabandhak.enums;

public enum Grades {
	A("A"), APLUS("A+"), B("B"), BPLUS("B+"), C("C"), D("D"), O("O");

	private final String gradeName;

	private Grades(String gradeName) {
		this.gradeName = gradeName;
	}
	
	public String getGradeName() {
		return gradeName;
	}
}