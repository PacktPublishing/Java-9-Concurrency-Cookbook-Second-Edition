package com.packtpub.java9.concurrency.cookbook.chapter01.recipe07.task;

/**
 * Runnable class than throws and Exception
 *
 */
public class Task implements Runnable {

	/**
	 * Main method of the class
	 */
	@Override
	public void run() {
		// The next instruction always throws and exception
		int number = Integer.parseInt("TTT");
		// This sentence will never be executed
		System.out.printf("Number: %d ", number);
	}

}
