package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;
import com.samarthsoft.prabandhak.enums.EventType;

public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	private String guid;
	private String name;
	private String description;
	private Long date;
	private EventType typeOfEvent;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public EventType getTypeOfEvent() {
		return typeOfEvent;
	}

	public void setTypeOfEvent(EventType typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}
}