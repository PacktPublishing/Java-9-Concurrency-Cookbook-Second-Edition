package com.packtpub.java9.concurrency.cookbook.chapter07.recipe04.task;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * This class implements a taks that store events in a delayed queue
 *
 */
public class Task implements Runnable {

	/**
	 * Id of the task
	 */
	private final int id;

	/**
	 * Delayed queue to store the events
	 */
	private final DelayQueue<Event> queue;

	/**
	 * Constructor of the class. It initializes its attributes
	 * 
	 * @param id
	 *            Id of the task
	 * @param queue
	 *            Delayed queue to store the events
	 */
	public Task(int id, DelayQueue<Event> queue) {
		this.id = id;
		this.queue = queue;
	}

	/**
	 * Main method of the task. It generates 100 events with the same activation
	 * time. The activation time will be the execution time of the thread plus
	 * the id of the thread seconds
	 */
	@Override
	public void run() {

		Date now = new Date();
		Date delay = new Date();
		delay.setTime(now.getTime() + (id * 1000));

		System.out.printf("Thread %s: %s\n", id, delay);

		for (int i = 0; i < 100; i++) {
			Event event = new Event(delay);
			queue.add(event);
		}
	}

}
