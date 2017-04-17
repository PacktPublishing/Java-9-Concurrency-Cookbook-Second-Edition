package com.packtpub.java9.concurrency.cookbook.chapter07.recipe05.task;

import java.util.concurrent.ConcurrentSkipListMap;

import com.packtpub.java9.concurrency.cookbook.chapter07.recipe05.util.Contact;

/**
 * This class implements a task that store contacts in a navigable map
 *
 */
public class Task implements Runnable {

	/**
	 * Navigable map to store the contacts
	 */
	private final ConcurrentSkipListMap<String, Contact> map;

	/**
	 * Id of the task
	 */
	private final String id;

	/**
	 * Constructor of the class that initializes its events
	 * 
	 * @param map
	 *            Navigable map to store the events
	 * @param id
	 *            Id of the task
	 */
	public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
		this.id = id;
		this.map = map;
	}

	/**
	 * Main method of the task. Generates 1000 contact objects and store them in
	 * the navigable map
	 */
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			Contact contact = new Contact(id, String.valueOf(i + 1000));
			map.put(id + contact.getPhone(), contact);
		}
	}

}
