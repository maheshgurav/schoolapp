package com.samarthsoft.prabandhak.api.tests;

public class TestCase {
	/*	public static void main(String[] args) {
		testAdmissionMapping();
		readAdmissionObject();
		testAdmissionMapping();
		List<Filter> filters = new ArrayList<Filter>();
		SelectValues selectValues = new SelectValues();
		selectValues.setProjectionPropertyName("studentName");
		SelectValues selectValues_1 = new SelectValues();
		selectValues_1.setProjectionPropertyName("classForAdmission");
		List<SelectValues> selectValueList = new ArrayList<SelectValues>();
		selectValueList.add(selectValues);
		selectValueList.add(selectValues_1);
		List<Object> admissions = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Admission.class, filters, selectValueList);
	}
	
	private static void testStudentMapping(){
		Student student = new Student();
		student.setAdharCardNumber("adhar");
		student.setAddmissionTakenInclass("as");
		student.setBloodGroup("A");
		student.setCaste("cst");
		student.setCategory("ctg");
		//student.setCurrentAddress(JsonConverter.toJson(getAddress()));
		student.setCurrentClass("5");
		student.setDateOfAdmission(Calendar.getInstance().getTimeInMillis());
		student.setDateOfBirth(Calendar.getInstance().getTimeInMillis());
		student.setFatherName("FNm");
		student.setGender(Gender.Male);
		student.setLastAttendedSchoolName("last school");
		student.setMotherName("mother");
		student.setNationality("Indian");
		student.setOrganizationGuid("org");
		student.setParentsOccupation("occupation");
		//student.setPermanentAddress(JsonConverter.toJson(getAddress()));
		student.setPlaceOfBirth("place");
		student.setPRN("PRN");
		student.setRollNumber("roll");
		student.setScholarshipType("type");
		student.setYearlyIncome(100000.0);
		DBCommunicator.getApiServices().getGenericApi().insert(student);
	}
	
	private static Address getAddress(){
		Address address = new Address();
		address.setAddressLineFirst("Daundaj");
		address.setCity("Pune");
		address.setCountry("India");
		address.setEmailAddress("studet@gmail.com");
		address.setPhoneNumber("9766355315");
		address.setPinCodeNumber("412 305");
		address.setState("Maharashtra");
		return address;
	}*/
	
	/*private static void testLoginMapping(){
		LoginInformation loginInfo = new LoginInformation();
		loginInfo.setPassword("mahesh");
		loginInfo.setUserName("piyu");
		loginInfo.setUserRole(UserRole.ADMIN);
		loginInfo.setOrganizationGuid("1");
		loginInfo.setParentOrganizationGuid("01");
		DBCommunicator.getApiServices().getGenericApi().insert(loginInfo);
	}
	
	private static Admission readAdmissionObject(){
		Admission admission = (Admission
				)DBCommunicator.getApiServices().getGenericApi().fetchObjectbyID(Admission.class, "ff80818144a7a2db0144a7a2dc720000");
		return admission;
	}
	
	private static void testAdmissionMapping(){
		Admission admission = new Admission();
		admission.setCaste("caste");
		admission.setCategory("category");
		admission.setClassForAdmission("classForAdmission");
		admission.setCurrentAddress("currentAddress");
		admission.setDateOfBirth(Calendar.getInstance().getTimeInMillis());
		admission.setFatherName("fatherName");
		admission.setGender(Gender.Male);
		admission.setLastAttendedSchoolName("lastAttendedSchoolName");
		admission.setLastAttendedUniversity("lastAttendedUniversity");
		admission.setMarksOrGradeOfLastClass("marksOrGradeOfLastClass");
		admission.setMotherName("motherName");
		admission.setParentsOccupation("parentsOccupation");
		admission.setPermanentAddress("permanentAddress");
		admission.setPlaceOfBirth("placeOfBirth");
		admission.setScholarshipType("scholarshiptype");
		admission.setStudentName("studentName_1");
		admission.setYearlyIncome(1200.0);
		admission.setOrganizationGuid("1");
		admission.setParentOrganizationGuid("01");
		DBCommunicator.getApiServices().getGenericApi().insert(admission);
		Admission adm = (Admission) JsonConverter.fromJson(JsonConverter.toJson(admission),Admission.class);
	}*/
}
