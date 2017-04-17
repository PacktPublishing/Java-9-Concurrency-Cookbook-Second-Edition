package com.packtpub.java9.concurrency.cookbook.chapter01.recipe06.event;

import java.util.Date;

/**
 * Class that stores event's information
 *
 */
public class Event {

	/**
	 * Date of the event
	 */
	private Date date;

	/**
	 * Message of the event
	 */
	private String event;

	/**
	 * Reads the Date of the event
	 * 
	 * @return the Date of the event
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Writes the Date of the event
	 * 
	 * @param date
	 *            the date of the event
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Reads the message of the event
	 * 
	 * @return the message of the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * Writes the message of the event
	 * 
	 * @param event
	 *            the message of the event
	 */
	public void setEvent(String event) {
		this.event = event;
	}
}
