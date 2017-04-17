package com.packtpub.java9.concurrency.cookbook.chapter01.recipe08.main;

import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter01.recipe08.task.SafeTask;

/**
 * Main class of the example.
 *
 */
public class SafeMain {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates a task
		SafeTask task = new SafeTask();

		// Creates and start three Thread objects for that Task
		for (int i = 0; i < 2 * Runtime.getRuntime().availableProcessors(); i++) {
			Thread thread = new Thread(task);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			thread.start();
		}

	}

}
