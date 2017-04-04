package com.samarthsoft.prabandhak.enums;

public enum SubjectEvaluationType {

	MARKS("MARKS"), GRADE("GRADE");

	private final String evaluationTypeName;

	private SubjectEvaluationType(String evaluationTypeName) {
		this.evaluationTypeName = evaluationTypeName;
	}

	public String getEvaluationTypeName() {
		return evaluationTypeName;
	}
}