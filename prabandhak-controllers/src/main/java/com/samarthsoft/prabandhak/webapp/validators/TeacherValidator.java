package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.TeacherForm;

@Component
public class TeacherValidator implements Validator{

	public void validate(Object object, Errors errors) {
		TeacherForm teacherFormObject = (TeacherForm)object;
		if(ValidatorCommon.checkFieldNullOrEmpty(teacherFormObject.getTeacher().getName().getFirstName()))
			errors.rejectValue("teacher.name.firstName", "Error.Empty.First.Name","Error.Empty.First.Name");
		if(ValidatorCommon.checkFieldNullOrEmpty(teacherFormObject.getTeacher().getName().getMiddleName()))
				errors.rejectValue("teacher.name.middleName", "Error.Empty.Middle.Name","Error.Empty.Middle.Name");
		if(ValidatorCommon.checkFieldNullOrEmpty(teacherFormObject.getTeacher().getName().getLastName()))
					errors.rejectValue("teacher.name.lastName", "Error.Empty.Last.Name","Error.Empty.Last.Name");
		if(ValidatorCommon.checkFieldNullOrEmpty(teacherFormObject.getTeacher().getLoginInformation().getUserName()))
			errors.rejectValue("teacher.loginInformation.userName", "Error.Empty.Username","Error.Empty.Username");
/*		if(ValidatorCommon.checkFieldNullOrEmpty(teacherFormObject.getTeacher().getContactNumber()))
			errors.rejectValue("teacher.contactNumber", "Error.Empty.ContactNumber","Error.Empty.ContactNumber");
		if(ValidatorCommon.checkFieldNullOrEmpty(teacherFormObject.getBirthDate()))
			errors.rejectValue("birthDate", "Error.Empty.BirthDate","Error.Empty.BirthDate");
		if(ValidatorCommon.checkFieldNullOrEmpty(teacherFormObject.getJoiningDate()))
			errors.rejectValue("joiningDate", "Error.Empty.JoiningDate","Error.Empty.JoiningDate");
*/	}

	public boolean supports(Class<?> arg0) {
		return true;
	}
}