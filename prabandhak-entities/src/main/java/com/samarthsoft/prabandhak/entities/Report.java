package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class Report extends Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	private String reportName;
	private byte[] actualReport;

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public byte[] getActualReport() {
		return actualReport;
	}

	public void setActualReport(byte[] actualReport) {
		this.actualReport = actualReport;
	}
}