package com.packtpub.java9.concurrency.cookbook.chapter10.recipe02.task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * This class implements a worker thread. This is a thread that
 * is going to execute ForkJoinTask objects in a ForkJoinPool.
 * 
 * Extends the basic class ForkJoinWorkerThread
 */
public class AlwaysThrowsExceptionWorkerThread extends ForkJoinWorkerThread {

	/**
	 * Constructor of the class. Call the constructor of the 
	 * parent class
	 * @param pool ForkJoinPool where the thread is going to execute
	 */
	protected AlwaysThrowsExceptionWorkerThread(ForkJoinPool pool) {
		super(pool);
	}

	/**
	 * Method that is going to execute where the Worker Thread
	 * begins its execution
	 */
	@Override
	protected void onStart() {
		super.onStart();
		throw new RuntimeException("Exception from worker thread");
	}
}
