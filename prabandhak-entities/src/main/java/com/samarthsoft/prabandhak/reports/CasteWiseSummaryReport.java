package com.samarthsoft.prabandhak.reports;

import java.io.Serializable;
import java.util.Map;

public class CasteWiseSummaryReport implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> casteWiseSummary;

	public Map<String, Integer> getCasteWiseSummary() {
		return casteWiseSummary;
	}

	public void setCasteWiseSummary(Map<String, Integer> casteWiseSummary) {
		this.casteWiseSummary = casteWiseSummary;
	}
}