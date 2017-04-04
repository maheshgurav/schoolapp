package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.LoginFormObject;

@Component
public class LoginFormValidator implements Validator {

	public void validate(Object object, Errors errors) {
		LoginFormObject loginFormObject = (LoginFormObject)object;
		if(ValidatorCommon.checkFieldNullOrEmpty(loginFormObject.getUserName()))
			errors.rejectValue("userName","Error.Empty.Username","Please enter user name");
		if(ValidatorCommon.checkFieldNullOrEmpty(loginFormObject.getPassword()))
			errors.rejectValue("password","Error.Empty.Password","Please enter password");
	}
	
	public boolean supports(Class<?> clazz) {
		return true;
	}
}