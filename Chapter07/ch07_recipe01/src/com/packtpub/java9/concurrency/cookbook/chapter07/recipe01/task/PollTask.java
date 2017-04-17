package com.packtpub.java9.concurrency.cookbook.chapter07.recipe01.task;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Task that delete 10000 elements from a ConcurrentListDeque
 *
 */
public class PollTask implements Runnable {

	/**
	 * List to delete the elements
	 */
	private final ConcurrentLinkedDeque<String> list;

	/**
	 * Constructor of the class
	 * 
	 * @param list
	 *            List to delete the elements
	 */
	public PollTask(ConcurrentLinkedDeque<String> list) {
		this.list = list;
	}

	/**
	 * Main method of the task. Deletes 10000 elements from the list using the
	 * pollFirst() that deletes the first element of the list and pollLast()
	 * that deletes the last element of the list
	 */
	@Override
	public void run() {
		for (int i = 0; i < 5000; i++) {
			list.pollFirst();
			list.pollLast();
		}
	}

}
