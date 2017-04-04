package com.samarthsoft.prabandhak.webapp.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.samarthsoft.prabandhak.common.ValidatorCommon;
import com.samarthsoft.prabandhak.form.entities.ExpenseFormObject;

@Component
public class ExpenseValidator implements Validator{

	public boolean supports(Class<?> arg0) {
		return true;
	}

	public void validate(Object object, Errors errors) {
		ExpenseFormObject expenseFormObject = (ExpenseFormObject)object;
		
		if(ValidatorCommon.checkFieldNullOrEmpty(expenseFormObject.getExpenseDetails().getExpenseFor()))
			errors.rejectValue("expenseDetails.expenseFor", "Error.Empty.Expense.For","Please enter reason of expense");

		if(ValidatorCommon.checkFieldNullOrEmpty(expenseFormObject.getDateOfExpense()))
			errors.rejectValue("dateOfExpense", "Error.Empty.Date","Please enter date");

		if(ValidatorCommon.checkFieldNullOrEmpty(expenseFormObject.getExpenseDetails().getAmount()))
			errors.rejectValue("expenseDetails.amount", "Error.Empty.Amount","Please enter amount");
	}
}