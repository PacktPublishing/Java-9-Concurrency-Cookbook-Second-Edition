package com.packtpub.java9.concurrency.cookbook.chapter09.recipe07.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task implemented to test the FindBugs utility 
 *
 */
public class Task implements Runnable {

	/**
	 * Lock used in the task
	 */
	private ReentrantLock lock;
	
	/**
	 * Constructor of the class
	 * @param lock Lock used in the task
	 */
	public Task(ReentrantLock lock) {
		this.lock=lock;
	}
	
	/**
	 * Main method of the task. 
	 */
	@Override
	public void run() {
		lock.lock();

		try {
			TimeUnit.SECONDS.sleep(1);
			/*
			 * There is a problem with this unlock. If the thread is interrupted
			 * while it is sleeping, the lock won't be unlocked and it will cause
			 * that the threads that are waiting for this block will be blocked and
			 * never will get the control of the Lock.
			 */
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
