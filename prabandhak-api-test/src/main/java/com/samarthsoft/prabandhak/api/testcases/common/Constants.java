package com.samarthsoft.prabandhak.api.testcases.common;

import java.sql.Timestamp;
import java.util.Calendar;
import com.samarthsoft.prabandhak.enums.Gender;

public interface Constants {
	public static String STUDENT_NAME = "studentName";

	public static String CASTE = "caste";

	public static String CATEGORY = "category";

	public static String CURRENT_ADDRESS = "currentAddress";

	public static Timestamp DATE_OF_BIRTH = (new Timestamp(Calendar
			.getInstance().getTimeInMillis()));

	public static String FATHER_NAME = "fatherName";

	public static Gender GENDER = Gender.Female;

	public static String MOTHER_NAME = "motherName";

	public static String PARENT_OCCUPATION = "parentsOccupation";

	public static String PERMANENT_ADDRESS = "permanentAddress";

	public static String PLACE_OF_BIRTH = "placeOfBirth";

	public static Double YEARlY_INCOME = 1200.0;

	public static String ORGANIZATION_GUID = "1";

	public static String PARENT_ORGANIZATION_GUID = "01";

	public static String CURRENT_CLASS = "5-A";

	public static String ADMISSION_CLASS = "1-A";

	public static String LAST_SCHOOL_ATTENDED = "SVM";

	public static String PRN = "123";

	public static String NATIONALITY = "Indian";

	public static String AADHAR_CARD_NUMBER = "1111111111111";
	
	public static String BLOOD_GROUP = "b+ve";
	
	public static String ROLL_NUMBER = "001";
	
	public static Timestamp DATE_OF_ADDMISSION = (new Timestamp(Calendar
			.getInstance().getTimeInMillis()));
	
	public static String CLASS_FOR_ADDMISSION = "001";

	public static String LAST_MARK_OR_GRADE = "001";

	public static String LAST_UNIVERSITY_ATTENDED = "001";

	public static String STAFF_NAME = "staffName";

	public static String DESIGNATION = "staff_designation";

	public static Timestamp JOINING_DATE = (new Timestamp(Calendar
			.getInstance().getTimeInMillis()));;

	public static String QUALIFICATION = "qualification";

	public static String USER_NAME = "testUserName";

	public static String PRINCIPAL_NAME = "principalName";

	public static String PERMANENT_REGISTRATION_NUMBER = "23423423432";

	public static String INSTITUTE_NAME = "institutename";

}
