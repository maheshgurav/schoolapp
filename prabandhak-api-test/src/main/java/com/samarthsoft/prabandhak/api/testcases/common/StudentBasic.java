package com.samarthsoft.prabandhak.api.testcases.common;

import com.samarthsoft.prabandhak.entities.BasicStudentInfo;

public class StudentBasic {
	
	public static BasicStudentInfo setBasicInfo(BasicStudentInfo basicStudent){
		basicStudent.setCaste(Constants.CASTE);
		basicStudent.setCategory(Constants.CATEGORY);
		basicStudent.setCurrentAddress(Constants.CURRENT_ADDRESS);
		basicStudent.setDateOfBirth(Constants.DATE_OF_BIRTH);
		basicStudent.setFatherName(Constants.FATHER_NAME);
		basicStudent.setGender(Constants.GENDER);
		basicStudent.setMotherName(Constants.MOTHER_NAME);
		basicStudent.setParentsOccupation(Constants.PARENT_OCCUPATION);
		basicStudent.setPermanentAddress(Constants.PERMANENT_ADDRESS);
		basicStudent.setPlaceOfBirth(Constants.PLACE_OF_BIRTH);
		basicStudent.setStudentName(Constants.STUDENT_NAME);
		basicStudent.setYearlyIncome(Constants.YEARlY_INCOME);
		basicStudent.setOrganizationGuid(Constants.ORGANIZATION_GUID);
		basicStudent.setParentOrganizationGuid(Constants.PARENT_ORGANIZATION_GUID);
		return basicStudent;
	}

}
