package com.packtpub.java9.concurrency.cookbook.chapter01.recipe05.core;

import java.util.Date;

import com.packtpub.java9.concurrency.cookbook.chapter01.recipe05.task.DataSourcesLoader;
import com.packtpub.java9.concurrency.cookbook.chapter01.recipe05.task.NetworkConnectionsLoader;

/**
 * Main class of the Example. Create and start two initialization tasks
 * and wait for their finish
 *
 */
public class Main {

	/**
	 * Main method of the class. Create and star two initialization tasks
	 * and wait for their finish
	 * @param args
	 */
	public static void main(String[] args) {

		// Creates and starts a DataSourceLoader runnable object
		DataSourcesLoader dsLoader = new DataSourcesLoader();
		Thread thread1 = new Thread(dsLoader,"DataSourceThread");

		// Creates and starts a NetworkConnectionsLoader runnable object
		NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
		Thread thread2 = new Thread(ncLoader,"NetworkConnectionLoader");

		// Start both threads
		thread1.start();
		thread2.start();

		// Wait for the finalization of the two threads
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Waits a message
		System.out.printf("Main: Configuration has been loaded: %s\n",new Date());
	}
}
