package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;

public class StudentCountByGender implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer maleCount;
	private Integer femaleCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaleCount() {
		return maleCount;
	}

	public void setMaleCount(Integer maleCount) {
		this.maleCount = maleCount;
	}

	public Integer getFemaleCount() {
		return femaleCount;
	}

	public void setFemaleCount(Integer femaleCount) {
		this.femaleCount = femaleCount;
	}
}