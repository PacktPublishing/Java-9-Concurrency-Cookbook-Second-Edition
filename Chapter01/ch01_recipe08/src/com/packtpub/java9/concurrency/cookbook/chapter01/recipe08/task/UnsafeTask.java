package com.packtpub.java9.concurrency.cookbook.chapter01.recipe08.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Class that shows the problem generate when some Thread objects share a data
 * structure
 *
 */
public class UnsafeTask implements Runnable {

	/**
	 * Date shared by all threads
	 */
	private Date startDate;

	/**
	 * Main method of the class. Saves the start date and writes it to the
	 * console when it starts and when it ends
	 */
	@Override
	public void run() {
		startDate = new Date();
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate);
		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate);
	}

}
