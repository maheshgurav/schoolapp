package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Alumni;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.LeavingCertificate;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.enums.RestrictionType;

@Component
public class StudentCertificatesControllerUtility {

	@Autowired
	private StudentControllerUtility studentControllerUtility;

	public List<Object> getStudents(String standardAndDivision,String organisationGuid){
		List<Filter> filters = new ArrayList<Filter>();
		if (!ValidatorCommon.checkFieldNullOrEmpty(standardAndDivision)) {
			filters.add(new Filter("standard", standardAndDivision.split("-")[0].trim(),
					RestrictionType.EQ));
			filters.add(new Filter("division", standardAndDivision.split("-")[1].trim(),
					RestrictionType.EQ));
		}
		return DBCommunicator.getApiServices().getGenericApi()
				.getFilteredList(Student.class, filters, null);
	}
	
	public List<Object> getBonafideCertificates(String selectedStudents){
		List<Object> result = new ArrayList<Object>();
		List<Filter> filters = new ArrayList<Filter>();
		
    	String[] studentGuids = selectedStudents.split(",");
    	List<String> studentGuidList = new ArrayList<String>();
    	for (String studentGuid : studentGuids) {
    		studentGuidList.add(studentGuid);
		}
    	
		filters.add(new Filter("guid", studentGuidList, RestrictionType.IN));
		List<Object> students = studentControllerUtility.getStudents(filters);
		for (Object object : students) {
			Student student = (Student) object;
			result.add(studentControllerUtility.getBonifideCertificateReportObject(student));
		}
		return result;
	}

	public Map<String,Object> getLeavingCertificates(String selectedStudents){
    	String[] studentGuids = selectedStudents.split(",");
    	List<String> studentGuidList = new ArrayList<String>();
    	for (String studentGuid : studentGuids) {
    		studentGuidList.add(studentGuid);
		}
		return getLeavingCertificates(studentGuidList);
	}

	public Map<String,Object> getLeavingCertificates(List<String> selectedStudents){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Object> leavingCertificates = getLeavingCertificatesList(selectedStudents);
		for (Object object : leavingCertificates) {
			result.put(((LeavingCertificate)object).getStudentGuid(), object);
		}
		return result;
	}
	
	public List<Object> getLeavingCertificatesList(List<String> selectedStudents){
    	List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("studentGuid", selectedStudents, RestrictionType.IN));
		return DBCommunicator.getApiServices().getGenericApi().getFilteredList(LeavingCertificate.class, filters, null);
	}
	
	public List<Object> getStudents(String selectedStudents){
		String[] studentGuids = selectedStudents.split(",");
    	List<String> studentGuidList = new ArrayList<String>();
    	for (String studentGuid : studentGuids) {
    		studentGuidList.add(studentGuid);
		}
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("guid", studentGuidList, RestrictionType.IN));
		return DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, filters, null);
	}
	
	public Alumni getAlumni(Student student) {
		Alumni alumni = new Alumni();
		alumni.setGuid(student.getGuid());
		alumni.setName(student.getName());
		//alumni.setFatherName(student.getFatherName());
		alumni.setAddmissionTakenInclass(student.getAddmissionTakenInclass());
		alumni.setCaste(student.getCaste());
		alumni.setCategory(student.getCategory());
		alumni.setStandard(student.getStandard());
		alumni.setDivision(student.getDivision());
		alumni.setDateOfAdmission(student.getDateOfAdmission());
		alumni.setDateOfBirth(student.getDateOfBirth());
		alumni.setGender(student.getGender());
		//alumni.setLastSchoolAttended(student.getLastAttendedSchoolName());
		//alumni.setMotherName(student.getMotherName());
		alumni.setNationality(student.getNationality());
		//alumni.setPlaceOfBirth(student.getPlaceOfBirth());
		alumni.setPRN(student.getPRN());
		//alumni.setPermanentAddress(student.getPermanentAddress());
		//alumni.setGuardianName(student.getGuardianName());
		//alumni.setGuardianContact(student.getContactNumberOfGuardian());
		//alumni.setContactNumber(student.getContactNumber());
		alumni.setBloodGroup(student.getBloodGroup());
		//alumni.setAadharCardNumber(student.getAadharCardNumber());
		alumni.setOrganizationGuid(student.getOrganizationGuid());
		//alumni.setEmailId(student.getEmailId());
		//alumni.setScholarshipType(student.getScholarshipType());
		return alumni;
	}
}