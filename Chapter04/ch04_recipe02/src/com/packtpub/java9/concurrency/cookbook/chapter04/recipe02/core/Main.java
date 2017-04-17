package com.packtpub.java9.concurrency.cookbook.chapter04.recipe02.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.packtpub.java9.concurrency.cookbook.chapter04.recipe02.task.FactorialCalculator;

/**
 * Main class of the example. Creates and execute ten FactorialCalculator tasks
 * in an executor controlling when they finish to write the results calculated
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create a ThreadPoolExecutor with fixed size. It has a maximun of two threads
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(2);
		// List to store the Future objects that control the execution of  the task and
		// are used to obtain the results
		List<Future<Integer>> resultList=new ArrayList<>();

		// Create a random number generator
		Random random=new Random();
		// Create and send to the executor the ten tasks
		for (int i=0; i<10; i++){
			Integer number=random.nextInt(10);
			FactorialCalculator calculator=new FactorialCalculator(number);
			Future<Integer> result=executor.submit(calculator);
			resultList.add(result);
		}
		
		// Wait for the finalization of the ten tasks
		do {
			System.out.printf("Main: Number of Completed Tasks: %d\n",executor.getCompletedTaskCount());
			for (int i=0; i<resultList.size(); i++) {
				Future<Integer> result=resultList.get(i);
				System.out.printf("Main: Task %d: %s\n",i,result.isDone());
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount()<resultList.size());
		
		// Write the results
		System.out.printf("Main: Results\n");
		for (int i=0; i<resultList.size(); i++) {
			Future<Integer> result=resultList.get(i);
			Integer number=null;
			try {
				number=result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Core: Task %d: %d\n",i,number);
		}
		
		// Shutdown the executor
		executor.shutdown();

	}

}
