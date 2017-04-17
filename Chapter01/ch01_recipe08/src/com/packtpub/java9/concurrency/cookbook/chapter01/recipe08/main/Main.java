package com.packtpub.java9.concurrency.cookbook.chapter01.recipe08.main;

import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter01.recipe08.task.UnsafeTask;

/**
 * Main class of the UnsafeTask. Creates a Runnable task and three Thread
 * objects that run it.
 *
 */
public class Main {

	/**
	 * Main method of the UnsafeTaks. Creates a Runnable task and three Thread
	 * objects that run it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates the unsafe task
		UnsafeTask task = new UnsafeTask();

		// Throw ten Thread objects
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
