package com.packtpub.java9.concurrency.cookbook.chapter10.recipe05.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Task used to write information about a Semaphore
 *
 */
public class TaskSemaphore implements Runnable {

	/**
	 * Semaphore shared by all the tasks
	 */
	private final Semaphore semaphore;
	
	/**
	 * Constructor of the class. Initializes its attribute
	 * @param semaphore Semaphore by all the tasks
	 */
	public TaskSemaphore(Semaphore semaphore){
		this.semaphore=semaphore;
	}
	
	/**
	 * Main method of the task. Acquire the semaphore, sleep the thread for
	 * two seconds and release the semaphore
	 */
	@Override
	public void run() {
		try {
			/*
			 * Acquire the semaphore and write a message in the console
			 */
			semaphore.acquire();
			System.out.printf("%s: Get the semaphore.\n",Thread.currentThread().getName());
			/*
			 * Sleep the thread
			 */
			TimeUnit.SECONDS.sleep(2);
			System.out.println(Thread.currentThread().getName()+": Release the semaphore.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			/*
			 * Release the semaphore and write a message
			 */
			semaphore.release();			
		}
	}
}
