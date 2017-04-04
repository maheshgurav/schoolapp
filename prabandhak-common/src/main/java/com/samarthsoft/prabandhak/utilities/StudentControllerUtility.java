package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.samarthsoft.prabandhak.common.DateToWordConverter;
import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.LeavingCertificate;
import com.samarthsoft.prabandhak.entities.Name;
import com.samarthsoft.prabandhak.entities.Student;
import com.samarthsoft.prabandhak.entities.StudentContactDetails;
import com.samarthsoft.prabandhak.entities.StudentParentDetails;
import com.samarthsoft.prabandhak.entities.StudentSubjectDetails;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.enums.UserRole;
import com.samarthsoft.prabandhak.form.entities.ClassOrDivisionChangeForm;
import com.samarthsoft.prabandhak.form.entities.RemoveStudentForm;
import com.samarthsoft.prabandhak.form.entities.StudentForm;
import com.samarthsoft.prabandhak.reports.BonifideCertificateReportObject;

@Component
public class StudentControllerUtility {
	
	@Autowired
	private CommonControllerUtility commonControllerUtility;
	
	public StudentForm convertOriginalObjectToFormObject(Object student) {
		StudentForm studentFormObject = new StudentForm();
		try{
			studentFormObject.setStudent((Student)student);
			studentFormObject.setAdmissionDate(DateUtility.convertTimeToString(studentFormObject.getStudent().getDateOfAdmission()));
			studentFormObject.setBirthDate(DateUtility.convertTimeToString(studentFormObject.getStudent().getDateOfBirth()));
			studentFormObject.setEditId(studentFormObject.getStudent().getGuid());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return studentFormObject;
	}

	public Student convertFormObjectToOriginalObject(Object studentFormObject) {
		Student student = ((StudentForm)studentFormObject).getStudent();
		student.setDateOfAdmission(DateUtility.convertStringToTime(((StudentForm)studentFormObject).getAdmissionDate()));
		student.setDateOfBirth(DateUtility.convertStringToTime(((StudentForm)studentFormObject).getBirthDate()));
		return student;
	}
	
	public Student getStudent(String studentGuid){
    	Filter filter = new Filter("guid",studentGuid,RestrictionType.EQ);
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(filter);
    	return (Student)DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, filters, null).get(0);
	}
	
	public List<Object> getStudentsByGuid(List<String> studentGuids){
    	Filter filter = new Filter("guid",studentGuids,RestrictionType.IN);
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(filter);
    	return DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, filters, null);
	}

	public StudentContactDetails getStudentContactDetails(String studentGuid){
    	Filter filter = new Filter("studentId",studentGuid,RestrictionType.EQ);
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(filter);
    	List<Object> studentContactDetails = DBCommunicator.getApiServices().getGenericApi().getObjectByColumnName(StudentContactDetails.class, "studentId", studentGuid);
    	if(studentContactDetails!=null && studentContactDetails.size() > 0){
    		return (StudentContactDetails)studentContactDetails.get(0);
    	}
    	return new StudentContactDetails();
	}

	public StudentParentDetails getStudentParentDetails(String studentGuid){
		List<Object> studentParentDetails = DBCommunicator.getApiServices().getGenericApi().getObjectByColumnName(StudentParentDetails.class, "studentGuid", studentGuid);
    	if(studentParentDetails!=null && studentParentDetails.size() > 0){
    		return (StudentParentDetails)studentParentDetails.get(0);
    	}
    	return new StudentParentDetails();
	}
	
	public StudentSubjectDetails getStudentSubjectDetails(String studentGuid,String standard,String division){
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(new Filter("studentId",studentGuid,RestrictionType.EQ));
    	filters.add(new Filter("standard",standard,RestrictionType.EQ));
    	filters.add(new Filter("division",division,RestrictionType.EQ));
    	List<Object> studentSubjectDetails = DBCommunicator.getApiServices().getGenericApi().getFilteredList(StudentSubjectDetails.class, filters, null);
    	if(studentSubjectDetails!=null && studentSubjectDetails.size() > 0){
    		return (StudentSubjectDetails)studentSubjectDetails.get(0);
    	}
    	return new StudentSubjectDetails();
	}
	
	public List<String> getStudentGuids(List<Object> students){
		List<String> result = new ArrayList<String>();
		for (Object object : students) {
			result.add(((Student)object).getGuid());	
		}
		return result;
	}
	
	public List<Object> getStudents(List<Filter> filters){
		return DBCommunicator.getApiServices().getGenericApi().getFilteredList(Student.class, filters, null);
	}
	
	public StudentForm[] getStudentFormObjects(List<Object> students) {
		StudentForm[] result = new StudentForm[students.size()];
		int count = 0;
		for (Object object : students) {
			Student student = (Student)object;
			result[count++] = convertOriginalObjectToFormObject(student);
		}
		return result;
	}
	
	
	public BonifideCertificateReportObject getBonifideCertificateReportObject(Student student){
		BonifideCertificateReportObject result = new BonifideCertificateReportObject();
		try{
		result.setBirthAddress(student.getPlaceOfBirth().toString());
		result.setBonafideIssueDate(DateUtility.convertTimeToString(Calendar.getInstance().getTimeInMillis()));
		result.setCaste(student.getCaste().toString());
		result.setClassAndDivision(commonControllerUtility.getStandardAndDivision(student));
		result.setDateOfBirthInDigits(DateUtility.convertTimeToString(student.getDateOfBirth()));
		DateToWordConverter converter = new DateToWordConverter();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(student.getDateOfBirth());
		result.setDateOfBirthInWords(converter.getDateInWords(calendar));
		result.setRegisterNumber(student.getPRN());
		result.setStudentName(student.getName().toString());
		result.setYear(getCurrentEducationalYear());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	private String getCurrentEducationalYear(){
		String result = "";
		Calendar calendar = Calendar.getInstance();
		if(calendar.get(Calendar.MONTH)+1 > 6)
			result = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.YEAR)+1);
		else result = (calendar.get(Calendar.YEAR)-1) + "-" + calendar.get(Calendar.YEAR);
		return result;
	}
	
	public RemoveStudentForm getRemoveStudentFormObject(String studentGuid){
		Student student = getStudent(studentGuid);
		RemoveStudentForm removeStudentForm = new RemoveStudentForm();
		removeStudentForm.setStudentGuid(student.getGuid());
		removeStudentForm.setStudentName(student.getName().toString());
		return removeStudentForm;
	}
	
	public ClassOrDivisionChangeForm getClassOrDivisionChangeFormObject(String studentGuid){
		Student student = getStudent(studentGuid);
		ClassOrDivisionChangeForm classOrDivisionChangeForm = new ClassOrDivisionChangeForm();
		classOrDivisionChangeForm.setStudentGuid(student.getGuid());
		classOrDivisionChangeForm.setStudentName(student.getName().toString());
		classOrDivisionChangeForm.setCurrentStandard(student.getStandard());
		classOrDivisionChangeForm.setCurrentDivision(student.getDivision());
		classOrDivisionChangeForm.setChangedStandard(student.getStandard());
		classOrDivisionChangeForm.setChangedDivision(student.getDivision());
		return classOrDivisionChangeForm;
	}
	
	public List<Filter> getFilteredList(String filter,String mapping,HttpServletRequest request){
		List<Filter> filters = new ArrayList<Filter>();
		try{
		String[] studentFilters = filter.split(",");
		String[] studentMappings = mapping.split(",");
		if(RequestCommon.getApplicationSession(request).getUserRole() != UserRole.SYSTEM_OWNER){
			filters.add(new Filter("schoolGuid", RequestCommon.getApplicationSession(request).getSchoolGuid(), RestrictionType.EQ));
		}
		for(int count = 0; count<studentFilters.length; count++){
			if (studentMappings[count].contains("/")) {
				if(RequestCommon.getAttributeValueFromRequest(studentFilters[count], request).toString()!=null && !RequestCommon.getAttributeValueFromRequest(studentFilters[count], request).toString().isEmpty()){
					String[] mappings = studentMappings[count].split("/");
					String standardAndDivision = RequestCommon.getAttributeValueFromRequest(studentFilters[count], request).toString();
					if(standardAndDivision!=null && standardAndDivision.split("-")!=null){
						if(standardAndDivision.split("-")[0]!=null)
							filters.add(new Filter(mappings[0].toString(), standardAndDivision.split("-")[0].toString() , RestrictionType.EQ));
					}
					if(standardAndDivision!=null && standardAndDivision.split("-")!=null && standardAndDivision.split("-").length > 1){
						if(standardAndDivision.split("-")[1]!=null)
							filters.add(new Filter(mappings[1].toString(), standardAndDivision.split("-")[1].toString() , RestrictionType.EQ));
					}
				}
			}else{
				//if()
				if(RequestCommon.getAttributeValueFromRequest(studentFilters[count], request).toString()!=null && !RequestCommon.getAttributeValueFromRequest(studentFilters[count], request).toString().isEmpty()){
					filters.add(new Filter(studentMappings[count], RequestCommon.getAttributeValueFromRequest(studentFilters[count], request).toString(), RestrictionType.LIKE));		
				}
			}
		}
		//filters.add(new Filter("isAlumni", false, RestrictionType.EQ));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return filters;
	}
	
	public void setFiltersInModel(String filter,ModelMap model,HttpServletRequest request){
		String[] studentFilters = filter.split(",");
		for(int count = 0; count<studentFilters.length; count++){
			model.put(studentFilters[count],RequestCommon.getAttributeValueFromRequest(studentFilters[count], request).toString());
			}
		}
	
	public LeavingCertificate getLeavingCertificate(String studentGuid){
		LeavingCertificate leavingCertificate = null;
		List<Object> leavingCertificates = DBCommunicator.getApiServices().getGenericApi().getObjectByColumnName(LeavingCertificate.class, "studentGuid", studentGuid);
		if(leavingCertificates!=null && leavingCertificates.size() > 0){
			leavingCertificate = (LeavingCertificate)leavingCertificates.get(0);
		}
		return leavingCertificate;
	}
}