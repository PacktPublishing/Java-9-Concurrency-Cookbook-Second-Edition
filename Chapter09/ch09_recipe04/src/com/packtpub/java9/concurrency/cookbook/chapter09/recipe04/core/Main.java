package com.packtpub.java9.concurrency.cookbook.chapter09.recipe04.core;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter09.recipe04.forkjoin.Task;

/**
 * Main class of the example. It creates all the elements for the
 * execution and writes information about the Fork/Join pool that
 * executes the task
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		/*
		 * Create the Fork/Join pool
		 */
		ForkJoinPool pool=new ForkJoinPool();
		
		/*
		 * Create a new Array of integer numbers with 10000 elements
		 */
		int array[]=new int[10000];
		
		/*
		 * Create a new task
		 */
		Task task1=new Task(array,0,array.length);
		
		/*
		 * Execute the task in the Fork/Join pool
		 */
		pool.execute(task1);
		
		/*
		 * Wait for the finalization of the task writing information
		 * about the pool every second
		 */
		while (!task1.isDone()) {
			showLog(pool);
			TimeUnit.SECONDS.sleep(1);
		}
		
		/*
		 * Shutdown the pool
		 */
		pool.shutdown();
		
		/*
		 * Wait for the finalization of the pool
		 */
		pool.awaitTermination(1, TimeUnit.DAYS);
		
		/*
		 * End of the program
		 */
		showLog(pool);
		System.out.printf("Main: End of the program.\n");

	}

	/*
	 * This method writes information about a Fork/Join pool
	 */
	private static void showLog(ForkJoinPool pool) {
		System.out.printf("**********************\n");
		System.out.printf("Main: Fork/Join Pool log\n");
		System.out.printf("Main: Fork/Join Pool: Parallelism: %d\n",pool.getParallelism());
		System.out.printf("Main: Fork/Join Pool: Pool Size: %d\n",pool.getPoolSize());
		System.out.printf("Main: Fork/Join Pool: Active Thread Count: %d\n",pool.getActiveThreadCount());
		System.out.printf("Main: Fork/Join Pool: Running Thread Count: %d\n",pool.getRunningThreadCount());
		System.out.printf("Main: Fork/Join Pool: Queued Submission: %d\n",pool.getQueuedSubmissionCount());
		System.out.printf("Main: Fork/Join Pool: Queued Tasks: %d\n",pool.getQueuedTaskCount());
		System.out.printf("Main: Fork/Join Pool: Queued Submissions: %s\n",pool.hasQueuedSubmissions());
		System.out.printf("Main: Fork/Join Pool: Steal Count: %d\n",pool.getStealCount());
		System.out.printf("Main: Fork/Join Pool: Terminated : %s\n",pool.isTerminated());
		System.out.printf("**********************\n");
	}

}
