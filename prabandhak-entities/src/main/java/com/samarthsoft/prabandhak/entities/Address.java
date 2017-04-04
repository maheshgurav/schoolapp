package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String address;
	private String state;
	private String pinCodeNumber;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCodeNumber() {
		return pinCodeNumber;
	}

	public void setPinCodeNumber(String pinCodeNumber) {
		this.pinCodeNumber = pinCodeNumber;
	}

	@Override
	public String toString() {
		String addressString = "";
		addressString = addressString  +",";
		if(state!=null && !state.isEmpty())
			addressString = addressString + state +",";
		if(pinCodeNumber!=null && !pinCodeNumber.isEmpty())
			addressString = addressString + pinCodeNumber;
		addressString = addressString.replace(",,", "");
		if(addressString.endsWith(","))
			addressString = addressString.substring(0,addressString.length()-1);
		return addressString;
	}
}