package com.packtpub.java9.concurrency.cookbook.chapter05.recipe04.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter05.recipe04.task.Task;

/**
 * Main class of the example. Creates a ForkJoinPool, an array of 100
 * elements and a Task object. Executes the Task object in the pool
 * and process the exception thrown by the Task
 *
 */
public class Main {

	/**
	 * Main method of the class
	 */
	public static void main(String[] args) {
		// Array of 100 integers
		int array[]=new int[100];
		// Task to process the array
		Task task=new Task(array,0,100);
		// ForkJoinPool to execute the Task
		ForkJoinPool pool=new ForkJoinPool();
		
		// Execute the task
		pool.execute(task);
	
		// Shutdown the ForkJoinPool
		pool.shutdown();
		
		// Wait for the finalization of the task
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Check if the task has thrown an Exception. If it's the case, write it
		// to the console
		
		if (task.isCompletedAbnormally()) {
			System.out.printf("Main: An exception has ocurred\n");
			System.out.printf("Main: %s\n",task.getException());
		}
		
		System.out.printf("Main: Result: %d",task.join());
	}
}
