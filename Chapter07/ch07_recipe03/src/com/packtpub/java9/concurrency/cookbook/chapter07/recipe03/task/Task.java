package com.packtpub.java9.concurrency.cookbook.chapter07.recipe03.task;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * This class implements a generator of events. It generates 1000 events and
 * stores them in a priory queue
 *
 */
public class Task implements Runnable {

	/**
	 * Id of the task
	 */
	private final int id;

	/**
	 * Priority queue to store the events
	 */
	private final PriorityBlockingQueue<Event> queue;

	/**
	 * Constructor of the class. It initializes its attributes
	 * 
	 * @param id
	 *            Id of the task
	 * @param queue
	 *            Priority queue to store the events
	 */
	public Task(int id, PriorityBlockingQueue<Event> queue) {
		this.id = id;
		this.queue = queue;
	}

	/**
	 * Main method of the task. It generates 1000 events and store them in the
	 * queue
	 */
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			Event event = new Event(id, i);
			queue.add(event);
		}
	}
}
