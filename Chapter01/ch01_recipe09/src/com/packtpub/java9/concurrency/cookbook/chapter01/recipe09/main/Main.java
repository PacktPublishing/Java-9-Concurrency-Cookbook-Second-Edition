package com.packtpub.java9.concurrency.cookbook.chapter01.recipe09.main;

import com.packtpub.java9.concurrency.cookbook.chapter01.recipe09.group.MyThreadGroup;
import com.packtpub.java9.concurrency.cookbook.chapter01.recipe09.task.Task;

/**
 * Main class of the example
 *
 */
public class Main {

	/**
	 * Main method of the example. Creates a group of threads of MyThreadGroup
	 * class and two threads inside this group
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Calculate the number of threads
		int numberOfThreads = 2 * Runtime.getRuntime().availableProcessors();

		// Create a MyThreadGroup object
		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");

		// Create a Taks object
		Task task = new Task();

		// Create the thread objects associated to the threadGroup
		for (int i = 0; i < numberOfThreads; i++) {
			Thread t = new Thread(threadGroup, task);
			t.start();
		}

		// Write information about the ThreadGroup to the console
		System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
		System.out.printf("Information about the Thread Group\n");
		threadGroup.list();

		// Write information about the status of the Thread objects to the
		// console
		Thread[] threads = new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for (int i = 0; i < threadGroup.activeCount(); i++) {
			System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
		}

	}

}
