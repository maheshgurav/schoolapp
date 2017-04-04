package com.samarthsoft.prabandhak.utilities;

import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.entities.ExpenseDetails;
import com.samarthsoft.prabandhak.form.entities.ExpenseFormObject;

@Component
public class ExpenseControllerUtility {
	
	public ExpenseFormObject getExpenseFormObject(ExpenseDetails expenseDetails){
		ExpenseFormObject expenseFormObject = new ExpenseFormObject();
		expenseFormObject.setExpenseDetails(expenseDetails);
		expenseFormObject.setDateOfExpense(DateUtility.convertTimeToString(expenseDetails.getDate()));
		return expenseFormObject;
	}
	
	public ExpenseDetails getExpenseDetailsObject(ExpenseFormObject expenseFormObject){
		ExpenseDetails expenseDetails = expenseFormObject.getExpenseDetails();
		expenseDetails.setDate(DateUtility.convertStringToTime(expenseFormObject.getDateOfExpense()));
		return expenseDetails;
	}
}