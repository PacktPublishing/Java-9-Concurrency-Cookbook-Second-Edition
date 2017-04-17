package com.packtpub.java9.concurrency.cookbook.chapter09.recipe01.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * This task used to write information about a Lock. It takes the control
 * of the Lock, sleeps for 500 milliseconds and free the Lock. Repeat this
 * behavior five times
 *
 */
public class Task implements Runnable {

	/**
	 * Lock shared by all the tasks
	 */
	private final Lock lock;
	
	/**
	 * Constructor of the class
	 * @param lock Lock shared by all the tasks
	 */
	public Task (Lock lock) {
		this.lock=lock;
	}
	
	/**
	 * Main method of the task. Takes the control of the Lock, sleeps for 500 milliseconds and free the
	 * lock. Repeats this behavior five times
	 */
	@Override
	public void run() {
		/*
		 * Loop with five steps
		 */
		for (int i=0; i<5; i++) {
			/*
			 * Acquire the lock
			 */
			lock.lock();
			System.out.printf("%s: Get the Lock.\n",Thread.currentThread().getName());
			/*
			 * Sleeps the thread 500 milliseconds
			 */
			try {
				TimeUnit.MILLISECONDS.sleep(500);
				System.out.printf("%s: Free the Lock.\n",Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				/*
				 * Free the lock
				 */
				lock.unlock();
			}
		}

	}
}
