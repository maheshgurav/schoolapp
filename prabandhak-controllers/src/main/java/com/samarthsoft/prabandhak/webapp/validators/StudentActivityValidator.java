package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.ClassOrDivisionChangeForm;
import com.samarthsoft.prabandhak.form.entities.RemoveStudentForm;

@Component
public class StudentActivityValidator implements Validator {

	//@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	//@Override
	public void validate(Object object, Errors errors) {
		if(object instanceof ClassOrDivisionChangeForm)
			System.out.println("Class Or Division Change Form");
		if(object instanceof RemoveStudentForm){
			RemoveStudentForm removeStudentForm = (RemoveStudentForm) object;
			if(ValidatorCommon.checkFieldNullOrEmpty(removeStudentForm.getDateOfRemoving()))
				errors.rejectValue("dateOfRemoving", "Error.Empty.Reason.Of.Removing","Please enter reason of removing");
			if(ValidatorCommon.checkFieldNullOrEmpty(removeStudentForm.getReasonOfRemoving()))
				errors.rejectValue("reasonOfRemoving", "Error.Empty.Reason.Of.Removing","Please enter date of removing");
		}
	}
}