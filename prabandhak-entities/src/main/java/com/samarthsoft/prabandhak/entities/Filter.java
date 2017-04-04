package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.enums.RestrictionType;

public class Filter implements Serializable {
	private static final long serialVersionUID = 1L;

	private String filterName;
	private Object filterValue;
	private RestrictionType filterType;

	public Filter(String filterName, Object filterValue, RestrictionType filterType) {
		super();
		this.filterName = filterName;
		this.filterValue = filterValue;
		this.filterType = filterType;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public Object getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(Object filterValue) {
		this.filterValue = filterValue;
	}

	public RestrictionType getFilterType() {
		return filterType;
	}

	public void setFilterType(RestrictionType filterType) {
		this.filterType = filterType;
	}
}