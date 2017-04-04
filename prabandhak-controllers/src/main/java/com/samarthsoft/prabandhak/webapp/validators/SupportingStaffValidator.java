package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.SupportingStaffForm;

@Component
public class SupportingStaffValidator implements Validator{

	public void validate(Object object, Errors errors) {
		SupportingStaffForm supportingStaffFormObject = (SupportingStaffForm)object;
		if(ValidatorCommon.checkFieldNullOrEmpty(supportingStaffFormObject.getName().getFirstName()))
			errors.rejectValue("name.firstName", "Error.Empty.First.Name","Please enter first name");
		if(ValidatorCommon.checkFieldNullOrEmpty(supportingStaffFormObject.getName().getMiddleName()))
				errors.rejectValue("name.middleName", "Error.Empty.Middle.Name","Please enter middle name");
		if(ValidatorCommon.checkFieldNullOrEmpty(supportingStaffFormObject.getName().getLastName()))
					errors.rejectValue("name.lastName", "Error.Empty.Last.Name","Please enter last name");
/*		if(supportingStaffFormObject.getSupportStaff().getDesignation()!=null && !supportingStaffFormObject.getSupportStaff().getDesignation().equalsIgnoreCase("Peon") &&
				ValidatorCommon.checkFieldNullOrEmpty(supportingStaffFormObject.getSupportStaff().getLoginInformation().getUserName()))
			errors.rejectValue("supportStaff.loginInformation.userName", "Error.Empty.Username","Please enter user name");
		if(ValidatorCommon.checkFieldNullOrEmpty(supportingStaffFormObject.getSupportStaff().getContactNumber()))
			errors.rejectValue("supportStaff.contactNumber", "Error.Empty.ContactNumber","Please enter contact number");
		if(ValidatorCommon.checkFieldNullOrEmpty(supportingStaffFormObject.getBirthDate()))
			errors.rejectValue("birthDate", "Error.Empty.BirthDate","Please enter birth date");
		if(ValidatorCommon.checkFieldNullOrEmpty(supportingStaffFormObject.getJoiningDate()))
			errors.rejectValue("joiningDate", "Error.Empty.JoiningDate","Please enter joining date");
*/	}
	
	public boolean supports(Class<?> arg0) {
		return true;
	}
}