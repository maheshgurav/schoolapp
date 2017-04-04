package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.AlumniFormObject;

@Component
public class AlumniValidator implements Validator{

	public void validate(Object object, Errors errors) {
		AlumniFormObject studentFormObject = (AlumniFormObject)object;
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getName().getFirstName()))
			errors.rejectValue("nameOfStudent.firstName", "Error.Empty.First.Name","Please enter first name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getName().getMiddleName()))
				errors.rejectValue("nameOfStudent.middleName", "Error.Empty.Middle.Name","Please enter middle name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getName().getLastName()))
					errors.rejectValue("nameOfStudent.lastName", "Error.Empty.Last.Name","Please enter last name");
		
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getFatherName().getFirstName()))
			errors.rejectValue("nameOfFather.firstName", "Error.Empty.First.Name","Please enter first name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getFatherName().getMiddleName()))
				errors.rejectValue("nameOfFather.middleName", "Error.Empty.Middle.Name","Please enter middle name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getFatherName().getLastName()))
					errors.rejectValue("nameOfFather.lastName", "Error.Empty.Last.Name","Please enter last name");
		
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getMotherName().getFirstName()))
			errors.rejectValue("nameOfMother.firstName", "Error.Empty.First.Name","Please enter first name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getMotherName().getMiddleName()))
				errors.rejectValue("nameOfMother.middleName", "Error.Empty.Middle.Name","Please enter middle name");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getMotherName().getLastName()))
					errors.rejectValue("nameOfMother.lastName", "Error.Empty.Last.Name","Please enter last name");
		
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getAlumni().getAddmissionTakenInclass()))
			errors.rejectValue("student.addmissionTakenInclass", "Error.Empty.Admission.Class","Please enter class in which student admitted in school");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getAlumni().getLastSchoolAttended()))
			errors.rejectValue("student.lastAttendedSchoolName", "Error.Empty.Last.School.Attended","Please enter last school attended by student");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getAlumni().getPRN()))
			errors.rejectValue("student.PRN","Error.Empty.PRN","Please enter permanent registration number");
		if(!ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getAlumni().getStudentGuid()) && !ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getAlumni().getPRN())){
			if(!ValidatorCommon.isUniquePRN(studentFormObject.getAlumni().getPRN(),studentFormObject.getAlumni().getStudentGuid(), studentFormObject.getAlumni().getOrganizationGuid()))
				errors.rejectValue("student.PRN","Error.Duplicate.PRN","Entered permanent registration number is already in use");
		}
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getDateOfAdmission()))
			errors.rejectValue("admissionDate", "Error.Empty.AdmissionDate","Please enter date of admission");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getDateOfBirth()))
			errors.rejectValue("birthDate", "Error.Empty.DateOfBirth","Please enter date of birth");
		if(ValidatorCommon.checkFieldNullOrEmpty(studentFormObject.getPlaceOfBirth().getAddressLineFirst()))
			errors.rejectValue("birthPlaceDetails.addressLineFirst", "Error.Empty.AddressLineFirst","Please enter date of birth");
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}