package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.JsonConverter;
import com.samarthsoft.prabandhak.common.LoginInformationCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Address;
import com.samarthsoft.prabandhak.entities.BankAccountDetails;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.LoginInformation;
import com.samarthsoft.prabandhak.entities.Teacher;
import com.samarthsoft.prabandhak.entities.interfaces.ObjectConverter;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.enums.UserRole;
import com.samarthsoft.prabandhak.form.entities.TeacherForm;

@Component
public class TeacherControllerUtility implements ObjectConverter{

	public TeacherForm convertOriginalObjectToFromObject(Object teacherOriginalObject) {
		TeacherForm teacherFormObject = new TeacherForm();
		try{
		teacherFormObject.setTeacher((Teacher)teacherOriginalObject);
		if(teacherFormObject.getTeacher().getCurrentAddress()!=null)
			teacherFormObject.setCurrentAddressDetails(teacherFormObject.getTeacher().getCurrentAddress());
		teacherFormObject.setJoiningDate(DateUtility.convertTimeToString(teacherFormObject.getTeacher().getJoiningDate()));
		teacherFormObject.setName(teacherFormObject.getTeacher().getName());
		if(teacherFormObject.getTeacher().getPermanentAddress()!=null)
			teacherFormObject.setPermanentAddressDetails(teacherFormObject.getTeacher().getPermanentAddress());
		if(teacherFormObject.getTeacher().getBankAccountDetails()!=null)
			teacherFormObject.setBankAccountDetails((BankAccountDetails)JsonConverter.fromJson(teacherFormObject.getTeacher().getBankAccountDetails(), BankAccountDetails.class));
		teacherFormObject.setBirthDate(DateUtility.convertTimeToString(teacherFormObject.getTeacher().getDateOfBirth()));
		teacherFormObject.setEditId(teacherFormObject.getTeacher().getGuid());
		//teacherFormObject.setClassInfo(CachedDataStore.getClassInfo().get(teacherFormObject.getTeacher().getClassTeacherFor()));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return teacherFormObject;
	}
	
	private Address JsonConverter(String json){
		Address address = new Address();
		try {
			JSONObject o = new JSONObject(json);
			if(o.get("addressLineFirst")!=null)
				address.setAddress(o.get("addressLineFirst").toString());
			if(o.get("state")!=null)
				address.setPinCodeNumber(o.get("state").toString());
			if(o.get("pinCodeNumber")!=null)
				address.setState(o.get("pinCodeNumber").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return address; 
	}

	public Teacher convertFormObjectToOriginalObject(Object teacherFormObject) {
		Teacher teacher = ((TeacherForm)teacherFormObject).getTeacher();
		teacher.setCurrentAddress(((TeacherForm)teacherFormObject).getCurrentAddressDetails());
		teacher.setJoiningDate(DateUtility.convertStringToTime(((TeacherForm)teacherFormObject).getJoiningDate()));
		teacher.setDateOfBirth(DateUtility.convertStringToTime(((TeacherForm)teacherFormObject).getBirthDate()));
		teacher.setName(((TeacherForm)teacherFormObject).getName());
		teacher.setPermanentAddress(((TeacherForm)teacherFormObject).getPermanentAddressDetails());
		teacher.setBankAccountDetails(JsonConverter.toJson(((TeacherForm)teacherFormObject).getBankAccountDetails()));
		return teacher;
	}
	
	public Teacher getTeacher(String teacherGuid){
		Teacher teacher = null;
    	Filter filter = new Filter("guid",teacherGuid,RestrictionType.EQ);
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(filter);
    	List<Object> teacherObjects = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Teacher.class, filters, null); 
    	if(teacherObjects!=null && teacherObjects.size() > 0){
    	teacher = (Teacher)teacherObjects.get(0);
    	if(teacher!=null){
    		LoginInformation loginInformation = LoginInformationCommon.injectLoginInformation(teacherGuid);
    		loginInformation.setUserRole(UserRole.TEACHER);
    		teacher.setLoginInformation(loginInformation);
    		}
    	}
    	return teacher;
	}
}