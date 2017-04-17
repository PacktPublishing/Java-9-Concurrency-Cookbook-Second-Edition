package com.packtpub.java9.concurrency.cookbook.chapter09.recipe03.core;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter09.recipe03.executor.Task;

/**
 * Main class of the example. Create an Executor and submits ten Task
 * objects for its execution. It writes information about the executor
 * to see its evolution. 
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		/*
		 * Create a new Executor
		 */
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		
		/*
		 * Create and submit ten tasks
		 */
		Random random=new Random();
		for (int i=0; i<10; i++) {
			Task task=new Task(random.nextInt(10000));
			executor.submit(task);
		}
		
		/*
		 * Write information about the executor
		 */
		for (int i=0; i<5; i++){
			showLog(executor);
			TimeUnit.SECONDS.sleep(1);
		}
		
		/*
		 * Shutdown the executor
		 */
		executor.shutdown();

		/*
		 * Write information about the executor
		 */
		for (int i=0; i<5; i++){
			showLog(executor);
			TimeUnit.SECONDS.sleep(1);
		}
		
		/*
		 * Wait for the finalization of the executor
		 */
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		/*
		 * Write a message to indicate the end of the program 
		 */
		System.out.printf("Main: End of the program.\n");
		
	}

	/**
	 * Method that writes in the console information about an executor
	 * @param executor Executor this method is going to process 
	 */
	private static void showLog(ThreadPoolExecutor executor) {
		System.out.printf("*********************");
		System.out.printf("Main: Executor Log");
		System.out.printf("Main: Executor: Core Pool Size: %d\n",executor.getCorePoolSize());
		System.out.printf("Main: Executor: Pool Size: %d\n",executor.getPoolSize());
		System.out.printf("Main: Executor: Active Count: %d\n",executor.getActiveCount());
		System.out.printf("Main: Executor: Task Count: %d\n",executor.getTaskCount());
		System.out.printf("Main: Executor: Completed Task Count: %d\n",executor.getCompletedTaskCount());
		System.out.printf("Main: Executor: Shutdown: %s\n",executor.isShutdown());
		System.out.printf("Main: Executor: Terminating: %s\n",executor.isTerminating());
		System.out.printf("Main: Executor: Terminated: %s\n",executor.isTerminated());
		System.out.printf("*********************\n");
	}

}
