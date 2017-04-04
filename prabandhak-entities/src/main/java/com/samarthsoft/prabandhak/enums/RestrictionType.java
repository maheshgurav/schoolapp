package com.samarthsoft.prabandhak.enums;

public enum RestrictionType {
	EQ(1), IN(2), LIKE(3),GE(4),LE(5);
	private Integer restrictionId;

	public Integer getRestrictionId() {
		return restrictionId;
	}

	private RestrictionType(Integer restrictionId) {
		this.restrictionId = restrictionId;
	}

}
