package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class ExtraCurricularActivitiyDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentGuid;
	private String activityName;
	private String priceDetails;

	public String getStudentGuid() {
		return studentGuid;
	}

	public void setStudentGuid(String studentGuid) {
		this.studentGuid = studentGuid;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getPriceDetails() {
		return priceDetails;
	}

	public void setPriceDetails(String priceDetails) {
		this.priceDetails = priceDetails;
	}

}
