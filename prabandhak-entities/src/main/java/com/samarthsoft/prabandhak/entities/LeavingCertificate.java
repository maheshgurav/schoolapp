package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class LeavingCertificate implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentGuid;
	private String progress;
	private String conduct;
	private String reasonOfLeaving;
	private String remark;
	private Long dateOfLeaving;
	private String studyingSinceMonthAndYear;
	
	public String getStudentGuid() {
		return studentGuid;
	}

	public void setStudentGuid(String studentGuid) {
		this.studentGuid = studentGuid;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getConduct() {
		return conduct;
	}

	public void setConduct(String conduct) {
		this.conduct = conduct;
	}

	public String getReasonOfLeaving() {
		return reasonOfLeaving;
	}

	public void setReasonOfLeaving(String reasonOfLeaving) {
		this.reasonOfLeaving = reasonOfLeaving;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getDateOfLeaving() {
		return dateOfLeaving;
	}

	public void setDateOfLeaving(Long dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

	public String getStudyingSinceMonthAndYear() {
		return studyingSinceMonthAndYear;
	}

	public void setStudyingSinceMonthAndYear(String studyingSinceMonthAndYear) {
		this.studyingSinceMonthAndYear = studyingSinceMonthAndYear;
	}
}