package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.StudentForm;

@Component
public class StudentValidator implements Validator{

	public void validate(Object object, Errors errors) {
		StudentForm studentFormObject = (StudentForm)object;
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getNameOfStudent().getFirstName()))
			errors.rejectValue("nameOfStudent.firstName", "Error.Empty.First.Name","Please enter first name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getNameOfStudent().getMiddleName()))
				errors.rejectValue("nameOfStudent.middleName", "Error.Empty.Middle.Name","Please enter middle name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getNameOfStudent().getLastName()))
					errors.rejectValue("nameOfStudent.lastName", "Error.Empty.Last.Name","Please enter last name");
		
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getNameOfFather().getFirstName()))
			errors.rejectValue("nameOfFather.firstName", "Error.Empty.First.Name","Please enter first name");
		/*if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getNameOfFather().getMiddleName()))
				errors.rejectValue("nameOfFather.middleName", "Error.Empty.Middle.Name","Please enter middle name");
		*/
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getNameOfFather().getLastName()))
					errors.rejectValue("nameOfFather.lastName", "Error.Empty.Last.Name","Please enter last name");
		
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getNameOfMother().getFirstName()))
			errors.rejectValue("nameOfMother.firstName", "Error.Empty.First.Name","Please enter first name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getNameOfMother().getMiddleName()))
				errors.rejectValue("nameOfMother.middleName", "Error.Empty.Middle.Name","Please enter middle name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getNameOfMother().getLastName()))
					errors.rejectValue("nameOfMother.lastName", "Error.Empty.Last.Name","Please enter last name");
		
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getStudent().getAddmissionTakenInclass()))
			errors.rejectValue("student.addmissionTakenInclass", "Error.Empty.Admission.Class","Please enter class in which student admitted in school");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getStudent().getLastAttendedSchoolName()))
			errors.rejectValue("student.lastAttendedSchoolName", "Error.Empty.Last.School.Attended","Please enter last school attended by student");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getStudent().getPRN()))
			errors.rejectValue("student.PRN","Error.Empty.PRN","Please enter permanent registration number");
		if(!ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getStudent().getGuid()) && !ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getStudent().getPRN())){
			if(!ValidatorCommon.isUniquePRN(studentFormObject.getStudent().getPRN(),studentFormObject.getStudent().getGuid(), studentFormObject.getStudent().getOrganizationGuid()))
				errors.rejectValue("student.PRN","Error.Duplicate.PRN","Entered permanent registration number is already in use");
		}
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getAdmissionDate()))
			errors.rejectValue("admissionDate", "Error.Empty.AdmissionDate","Please enter date of admission");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getBirthDate()))
			errors.rejectValue("birthDate", "Error.Empty.DateOfBirth","Please enter date of birth");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getBirthPlaceDetails().getAddressLineFirst()))
			errors.rejectValue("birthPlaceDetails.addressLineFirst", "Error.Empty.AddressLineFirst","Please enter date of birth");
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}