package com.packtpub.java9.concurrency.cookbook.chapter01.recipe10.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Class that implements the ThreadFactory interface to create a basic thread
 * factory
 *
 */
public class MyThreadFactory implements ThreadFactory {

	// Attributes to save the necessary data to the factory
	private int counter;
	private String name;
	private List<String> stats;

	/**
	 * Constructor of the class
	 * 
	 * @param name
	 *            Base name of the Thread objects created by this Factory
	 */
	public MyThreadFactory(String name) {
		counter = 0;
		this.name = name;
		stats = new ArrayList<String>();
	}

	/**
	 * Method that creates a new Thread object using a Runnable object
	 * 
	 * @param r:
	 *            Runnable object to create the new Thread
	 */
	@Override
	public Thread newThread(Runnable r) {
		// Create the new Thread object
		Thread t = new Thread(r, name + "-Thread_" + counter);
		counter++;
		// Actualize the statistics of the factory
		stats.add(String.format("Created thread %d with name %s on %s\n", t.getId(), t.getName(), new Date()));
		return t;
	}

	/**
	 * Method that returns the statistics of the ThreadFactory
	 * 
	 * @return The statistics of the ThreadFactory
	 */
	public String getStats() {
		StringBuffer buffer = new StringBuffer();
		Iterator<String> it = stats.iterator();

		while (it.hasNext()) {
			buffer.append(it.next());
		}

		return buffer.toString();
	}

}
