package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.entities.IncomeDetails;

public class IncomeFormObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private IncomeDetails incomeDetails;
	private String dateOfIncome;

	public IncomeDetails getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(IncomeDetails incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	public String getDateOfIncome() {
		return dateOfIncome;
	}

	public void setDateOfIncome(String dateOfIncome) {
		this.dateOfIncome = dateOfIncome;
	}
}