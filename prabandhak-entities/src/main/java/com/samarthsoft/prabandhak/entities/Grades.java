package com.samarthsoft.prabandhak.entities;

public enum Grades {
	FAIL(0, "FAIL"),PASS_CLASS(1, "PASS_CLASS"), SECOND_CLASS(
			2, "SECOND_CLASS"), HIGHER_SECOND_CLASS(3, "HIGHER_SECOND_CLASS"),FIRST_CLASS(4, "FIRST_CLASS"), DISTINCTION(5,
			"DISTINCTION");

	private final String gradeName;
	private final int gradeId;

	private Grades(final int gradeId, final String gradeName) {
		this.gradeId = gradeId;
		this.gradeName = gradeName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public int getGradeId() {
		return gradeId;
	}
}
