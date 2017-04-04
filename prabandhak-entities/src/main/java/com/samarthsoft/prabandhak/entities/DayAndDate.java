package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;
import java.util.List;

public class DayAndDate implements Serializable {
	private static final long serialVersionUID = 1L;
	private String date;
	private String day;
	private String dayNumber;
	private Integer week;
	private List<String> events;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(String dayNumber) {
		this.dayNumber = dayNumber;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}
}