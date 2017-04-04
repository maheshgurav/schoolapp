package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.AlumniFormObject;
import com.samarthsoft.prabandhak.form.entities.StudentForm;

@Component
public class AlumniValidator implements Validator{

	public void validate(Object object, Errors errors) {
		if(object instanceof StudentForm){
		AlumniFormObject alumniFormObject = (AlumniFormObject)object;
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getName().getFirstName()))
			errors.rejectValue("student.name.firstName", "Error.Empty.First.Name","Please enter first name");
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getName().getMiddleName()))
				errors.rejectValue("student.name.middleName", "Error.Empty.Middle.Name","Please enter middle name");
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getName().getLastName()))
					errors.rejectValue("student.name.lastName", "Error.Empty.Last.Name","Please enter last name");
		
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getFirstNameOfMother()))
			errors.rejectValue("student.firstNameOfMother", "Error.Empty.First.Name","Please enter first name");
		
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getAddmissionTakenInclass()))
			errors.rejectValue("student.addmissionTakenInclass", "Error.Empty.Admission.Class","Please enter class in which student admitted in school");
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getNameOfLastAttendedSchool()))
			errors.rejectValue("student.lastAttendedSchoolName", "Error.Empty.Last.School.Attended","Please enter last school attended by student");
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getPRN()))
			errors.rejectValue("student.PRN","Error.Empty.PRN","Please enter permanent registration number");
		if(!ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getGuid()) && !ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getPRN())){
			if(!ValidatorCommon.isUniquePRN(alumniFormObject.getAlumni().getPRN(),alumniFormObject.getAlumni().getGuid(), alumniFormObject.getAlumni().getOrganizationGuid()))
				errors.rejectValue("student.PRN","Error.Duplicate.PRN","Entered permanent registration number is already in use");
		}
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAdmissionDate()))
			errors.rejectValue("admissionDate", "Error.Empty.AdmissionDate","Please enter date of admission");
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getBirthDate()))
			errors.rejectValue("birthDate", "Error.Empty.DateOfBirth","Please enter date of birth");
		if(ValidatorCommon.checkFieldNullOrEmpty(alumniFormObject.getAlumni().getPlaceOfBirth().getAddress()))
			errors.rejectValue("student.placeOfBirth.address", "Error.Empty.AddressLineFirst","Please enter date of birth");
		}
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}