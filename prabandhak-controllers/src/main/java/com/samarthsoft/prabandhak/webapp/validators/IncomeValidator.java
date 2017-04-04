package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.IncomeFormObject;

@Component
public class IncomeValidator implements Validator{

	public boolean supports(Class<?> arg0) {
		return true;
	}

	public void validate(Object object, Errors errors) {
		IncomeFormObject incomeFormObject = (IncomeFormObject)object;
		
		if(ValidatorCommon.checkFieldNullOrEmpty(incomeFormObject.getIncomeDetails().getIncomeFrom()))
			errors.rejectValue("incomeDetails.incomeFrom", "Error.Empty.Income.From","Please enter reason by which you got income");

		if(ValidatorCommon.checkFieldNullOrEmpty(incomeFormObject.getDateOfIncome()))
			errors.rejectValue("dateOfIncome", "Error.Empty.Date","Please enter date");

		if(ValidatorCommon.checkFieldNullOrEmpty(incomeFormObject.getIncomeDetails().getAmount()))
			errors.rejectValue("incomeDetails.amount", "Error.Empty.Amount","Please enter amount");
	}
}