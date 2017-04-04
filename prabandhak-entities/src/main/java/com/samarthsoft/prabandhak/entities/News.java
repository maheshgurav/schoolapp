package com.samarthsoft.prabandhak.entities;

import java.io.Serializable;

public class News extends Organization implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String topic;
	private String description;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}