package com.packtpub.java9.concurrency.cookbook.chapter10.recipe02.task;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * This is the Task used in this example. It does nothing. Only
 * sleeps the thread during 1 second
 *
 */
public class OneSecondLongTask extends RecursiveAction{

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Method that executes the action of the Task. It sleeps
	 * the thread during one second
	 */
	@Override
	protected void compute() {
		System.out.printf("Task: Starting.\n");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Task: Finish.\n");
	}

}
