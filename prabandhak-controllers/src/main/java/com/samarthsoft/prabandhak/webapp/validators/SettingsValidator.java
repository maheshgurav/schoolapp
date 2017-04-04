package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SettingsValidator implements Validator{

	public boolean supports(Class<?> arg0) {
		return true;
	}

	public void validate(Object arg0, Errors arg1) {		
	}
}