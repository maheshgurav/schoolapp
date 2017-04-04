package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class ExpenseDetails extends IncomeExpenseBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String expenseFor;
	
	public String getExpenseFor() {
		return expenseFor;
	}

	public void setExpenseFor(String expenseFor) {
		this.expenseFor = expenseFor;
	}
}