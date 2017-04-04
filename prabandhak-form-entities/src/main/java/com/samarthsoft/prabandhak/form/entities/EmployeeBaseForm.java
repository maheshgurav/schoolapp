package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.entities.Address;
import com.samarthsoft.prabandhak.entities.BankAccountDetails;
import com.samarthsoft.prabandhak.entities.Name;

public class EmployeeBaseForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String editId;
	private Name name;
	private String joiningDate;
	private String birthDate;
	private Address currentAddressDetails;
	private Address permanentAddressDetails;
	private BankAccountDetails bankAccountDetails;

	public String getEditId() {
		return editId;
	}

	public void setEditId(String editId) {
		this.editId = editId;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Address getCurrentAddressDetails() {
		return currentAddressDetails;
	}

	public void setCurrentAddressDetails(Address currentAddressDetails) {
		this.currentAddressDetails = currentAddressDetails;
	}

	public Address getPermanentAddressDetails() {
		return permanentAddressDetails;
	}

	public void setPermanentAddressDetails(Address permanentAddressDetails) {
		this.permanentAddressDetails = permanentAddressDetails;
	}

	public BankAccountDetails getBankAccountDetails() {
		return bankAccountDetails;
	}

	public void setBankAccountDetails(BankAccountDetails bankAccountDetails) {
		this.bankAccountDetails = bankAccountDetails;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
}