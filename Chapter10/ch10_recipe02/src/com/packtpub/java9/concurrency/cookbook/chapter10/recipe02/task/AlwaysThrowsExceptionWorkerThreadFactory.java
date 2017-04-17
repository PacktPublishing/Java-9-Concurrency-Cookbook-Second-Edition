package com.packtpub.java9.concurrency.cookbook.chapter10.recipe02.task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;

/**
 * The ForkJoinPool uses a Factory to create its Working Threads.
 * As we want to use our Working Threads, we need to use our own
 * Factory to create those threads.
 * 
 * It implements the interface that every factory has to implement. The
 * ForkJoinWorkerThreadFactory
 */
public class AlwaysThrowsExceptionWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

	/**
	 * This method creates a new Worker Thread.
	 * @param pool The ForkJoinPool where the thread that is creater
	 * is going to execute
	 */
	@Override
	public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
		return new AlwaysThrowsExceptionWorkerThread(pool);
	}

}
