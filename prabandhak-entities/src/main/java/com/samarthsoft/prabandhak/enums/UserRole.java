package com.samarthsoft.prabandhak.enums;

public enum UserRole {
	SYSTEM_OWNER("0"), SCHOOL_ADMIN("1"), TEACHER("2"),SUPPORTING_STAFF("3"),STUDENT("4"),PARENT("5");

	private String roleId;
	
	private UserRole(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleId() {
		return roleId;
	}
}