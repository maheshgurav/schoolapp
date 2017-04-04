package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.LeavingCertificateForm;

@Component
public class LeavingCertificateValidator implements Validator{

	public boolean supports(Class<?> arg0) {
		return true;
	}

	public void validate(Object object, Errors errors) {
		LeavingCertificateForm leavingCertificateFormObject = (LeavingCertificateForm)object;
		
		if(ValidatorCommon.checkFieldNullOrEmpty(leavingCertificateFormObject.getLeavingCertificate().getProgress()))
			errors.rejectValue("leavingCertificate.progress", "Error.Empty.Lc.Progress","Please enter progress of student");
		if(ValidatorCommon.checkFieldNullOrEmpty(leavingCertificateFormObject.getLeavingCertificate().getConduct()))
			errors.rejectValue("leavingCertificate.conduct", "Error.Empty.Lc.Conduct","Please enter conduct of student");
		if(ValidatorCommon.checkFieldNullOrEmpty(leavingCertificateFormObject.getLeavingCertificate().getReasonOfLeaving()))
			errors.rejectValue("leavingCertificate.reasonOfLeaving", "Error.Empty.Lc.ReasonOfLeaving","Please enter reason of leaving school");
		if(ValidatorCommon.checkFieldNullOrEmpty(leavingCertificateFormObject.getLeavingCertificate().getRemark()))
			errors.rejectValue("leavingCertificate.remark", "Error.Empty.Lc.Remark","Please enter remark");
		if(ValidatorCommon.checkFieldNullOrEmpty(leavingCertificateFormObject.getLeavingDate()))
			errors.rejectValue("leavingDate", "Error.Empty.Lc.LeavingDate","Please enter leaving date");
	}
}