package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.entities.ExpenseDetails;

public class ExpenseFormObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private ExpenseDetails expenseDetails;
	private String dateOfExpense;

	public ExpenseDetails getExpenseDetails() {
		return expenseDetails;
	}

	public void setExpenseDetails(ExpenseDetails expenseDetails) {
		this.expenseDetails = expenseDetails;
	}

	public String getDateOfExpense() {
		return dateOfExpense;
	}

	public void setDateOfExpense(String dateOfExpense) {
		this.dateOfExpense = dateOfExpense;
	}
}