package com.packtpub.java9.concurrency.cookbook.chapter10.recipe04.core;

import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter10.recipe04.task.Task;

/**
 * Main class of the example. Creates five threads and writes information about them
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * Create a task object
		 */
		Task task = new Task();
		
		/*
		 * Create an array to store the threads
		 */
		Thread threads[] = new Thread[5];

		/*
		 * Create and start the five threads
		 */
		for (int i = 0; i < 5; i++) {
			threads[i] = new Thread(task);
			threads[i].setPriority(i + 1);
			threads[i].start();
		}

		/*
		 * Write threads information
		 */
		for (int j = 0; j < 10; j++) {
			System.out.printf("Main: Logging threads\n");
			for (int i = 0; i < threads.length; i++) {
				System.out.printf("**********************\n");
				System.out.printf("Main: %d: Id: %d Name: %s: Priority: %d\n",i,threads[i].getId(),threads[i].getName(),threads[i].getPriority());
				System.out.printf("Main: Status: %s\n",threads[i].getState());
				System.out.printf("Main: Thread Group: %s\n",threads[i].getThreadGroup());
				System.out.printf("Main: Stack Trace: \n");
				for (int t=0; t<threads[i].getStackTrace().length; t++) {
					System.out.printf("Main: %s\n",threads[i].getStackTrace()[t]);
				}
				System.out.printf("**********************\n");
			}
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
