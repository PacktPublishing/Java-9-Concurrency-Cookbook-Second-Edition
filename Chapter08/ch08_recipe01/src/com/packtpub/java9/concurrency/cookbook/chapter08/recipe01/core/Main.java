package com.packtpub.java9.concurrency.cookbook.chapter08.recipe01.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter08.recipe01.executor.MyExecutor;
import com.packtpub.java9.concurrency.cookbook.chapter08.recipe01.task.SleepTwoSecondsTask;

/**
 * Main clas of the example. It creates a custom executor and executes 10 tasks in it
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Creation of the custom executor
		 */
		MyExecutor myExecutor=new MyExecutor(4, 8, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
		
		/*
		 * Create a list to store the objects to control the execution of the tasks 
		 */
		List<Future<String>> results=new ArrayList<>();
		
		/*
		 * Create and submit to the executor 10 tasks 
		 */
		for (int i=0; i<10; i++) {
			SleepTwoSecondsTask task=new SleepTwoSecondsTask();
			Future<String> result=myExecutor.submit(task);
			results.add(result);
		}
		
		/*
		 * Get the result of the execution of the first five tasks 
		 */
		for (int i=0; i<5; i++){
			try {
				String result=results.get(i).get();
				System.out.printf("Main: Result for Task %d : %s\n",i,result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * Call the shutdown method 
		 */
		myExecutor.shutdown();
		
		/*
		 * Get the results of the execution of the last five tasks
		 */
		for (int i=5; i<10; i++){
			try {
				String result=results.get(i).get();
				System.out.printf("Main: Result for Task %d : %s\n",i,result);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * Wait for the finalization of the Executor
		 */
		try {
			myExecutor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*
		 * Write a message indicating the end of the program
		 */
		System.out.printf("Main: End of the program.\n");
	}

}
