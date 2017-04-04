package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class IncomeDetails extends IncomeExpenseBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String incomeFrom;

	public String getIncomeFrom() {
		return incomeFrom;
	}

	public void setIncomeFrom(String incomeFrom) {
		this.incomeFrom = incomeFrom;
	}
}