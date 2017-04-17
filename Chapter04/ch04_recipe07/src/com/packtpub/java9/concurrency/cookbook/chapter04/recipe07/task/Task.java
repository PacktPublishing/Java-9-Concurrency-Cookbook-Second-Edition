package com.packtpub.java9.concurrency.cookbook.chapter04.recipe07.task;

import java.util.concurrent.Callable;

/**
 * This class implements the task of the example. It simply writes a message
 * to the console every 100 milliseconds 
 *
 */
public class Task implements Callable<String> {

	/**
	 * Main method of the task. It has an infinite loop that writes a message to
	 * the console every 100 milliseconds
	 */
	@Override
	public String call() throws Exception {
		while (true){
			System.out.printf("Task: Test\n");
			Thread.sleep(100);
		}
	}
}
