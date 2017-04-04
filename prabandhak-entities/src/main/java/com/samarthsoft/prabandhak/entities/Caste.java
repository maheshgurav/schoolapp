package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Caste implements Serializable {
	private static final long serialVersionUID = 1L;

	private String categoryGuid;
	private String religion;
	private String subcaste;
	private Boolean isMinority;
	private String guid;
	private Boolean isDefaultForAttendanceReport;

	public Caste(){
	}
	
	public Caste(String categoryGuid,String religion,String subcaste,Boolean isMinority){
		this.categoryGuid = categoryGuid;
		this.religion = religion;
		this.subcaste = religion;
		this.isMinority = isMinority;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getGuid() {
		return guid;
	}

	public String getCategoryGuid() {
		return categoryGuid;
	}

	public void setCategoryGuid(String categoryGuid) {
		this.categoryGuid = categoryGuid;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getSubcaste() {
		return subcaste;
	}

	public void setSubcaste(String subcaste) {
		this.subcaste = subcaste;
	}

	public Boolean getIsMinority() {
		return isMinority;
	}

	public void setIsMinority(Boolean isMinority) {
		this.isMinority = isMinority;
	}
	
	public Boolean getIsDefaultForAttendanceReport() {
		return isDefaultForAttendanceReport;
	}

	public void setIsDefaultForAttendanceReport(Boolean isDefaultForAttendanceReport) {
		this.isDefaultForAttendanceReport = isDefaultForAttendanceReport;
	}

	@Override
	public String toString() {
		if(religion!=null && !religion.isEmpty() && subcaste!=null && !subcaste.isEmpty())
			return religion + " - " + subcaste;
		if(religion==null && subcaste!=null && !subcaste.isEmpty())
			return subcaste;
		if(religion!=null && (subcaste==null || subcaste.isEmpty()))
			return religion;
		return subcaste;
	}
}