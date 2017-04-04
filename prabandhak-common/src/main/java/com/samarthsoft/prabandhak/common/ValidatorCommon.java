package com.samarthsoft.prabandhak.common;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Student;

public class ValidatorCommon {
	private static Logger logger = LoggerFactory
			.getLogger(ValidatorCommon.class);
	private static final String  pinCodeRegex="^([1-9])([0-9]){5}";
	private static final String  phoneNoRegex="([0-9]){10}";

	private ValidatorCommon() {
	}

	public static boolean checkFieldNullOrEmpty(Object object) {
		try {
			if (object != null && !object.toString().isEmpty()
					&& object.toString().trim().length() > 0)
				return false;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return true;
	}

	public static boolean isValidEmail(String email) {
		try {
			return EmailValidator.getInstance().isValid(email);
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return false;
	}
	
	public static boolean isValidPinCode(String pincode) {
		try {
			Pattern pattern = Pattern.compile(pinCodeRegex);
			Matcher matcher = pattern.matcher(pincode);
			boolean result = matcher.matches();
			return result;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return false;
	}

	public static boolean isValidPhoneNumber(String phoneno) {
		try {
			Pattern pattern = Pattern.compile(phoneNoRegex);
			Matcher matcher = pattern.matcher(phoneno);
			boolean result = matcher.matches();
			return result;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return false;
	}

	public static boolean isUniquePRN(String permenentRegistrationNumber, String studentGuid,String schoolGuid) {
		try {
			List<Object> students = (List<Object>)DBCommunicator.getApiServices().getGenericApi().getObjectByColumnName(Student.class, "PRN", permenentRegistrationNumber);
			if (students != null && students.size() > 0) {
				for (Object object : students) {
					Student student = (Student)object;
					if(!student.getGuid().equals(studentGuid) && student.getPRN().equals(permenentRegistrationNumber))
						return false;
				}
			}
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return true;
	}
	
	public static void checkAndAddFieldErrors(BindingResult validationResult,ModelMap model){
		for(FieldError error : validationResult.getFieldErrors())
			model.put("error_" + error.getField().replace(".", "_"),error.getCode());
	}
}