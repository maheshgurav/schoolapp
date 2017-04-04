package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class IncomeExpenseBase extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long date;
	private Float amount;

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}
}