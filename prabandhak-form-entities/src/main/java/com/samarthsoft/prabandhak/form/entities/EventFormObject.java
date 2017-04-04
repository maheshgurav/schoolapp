package com.samarthsoft.prabandhak.form.entities;

import java.io.Serializable;

import com.samarthsoft.prabandhak.entities.Event;

public class EventFormObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String date;
	private Event event;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}