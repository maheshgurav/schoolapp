package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;
import java.util.List;

public class PaginationObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long recordCount;
	private List<Object> records;

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public List<Object> getRecords() {
		return records;
	}

	public void setRecords(List<Object> records) {
		this.records = records;
	}
}