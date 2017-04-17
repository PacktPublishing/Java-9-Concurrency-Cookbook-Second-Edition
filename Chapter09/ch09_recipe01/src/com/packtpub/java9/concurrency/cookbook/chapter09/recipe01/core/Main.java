package com.packtpub.java9.concurrency.cookbook.chapter09.recipe01.core;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter09.recipe01.lock.MyLock;
import com.packtpub.java9.concurrency.cookbook.chapter09.recipe01.lock.Task;

/**
 * Main class of the example. Create five threads to execute the task and write info
 * about the Lock shared by all the threads
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * Create a Lock object
		 */
		MyLock lock=new MyLock();
		
		/*
		 * Create an array for five threads
		 */
		Thread threads[]=new Thread[5];
		
		/*
		 * Create and start five threads
		 */
		for (int i=0; i<5; i++) {
			Task task=new Task(lock);
			threads[i]=new Thread(task);
			threads[i].start();
		}
		
		/*
		 * Create a loop with 15 steps
		 */
		for (int i=0; i<15; i++) {
			/*
			 * Write info about the lock
			 */
			System.out.printf("Main: Logging the Lock\n");
			System.out.printf("************************\n");
			System.out.printf("Lock: Owner : %s\n",lock.getOwnerName());
			System.out.printf("Lock: Queued Threads: %s\n",lock.hasQueuedThreads());
			if (lock.hasQueuedThreads()){
				System.out.printf("Lock: Queue Length: %d\n",lock.getQueueLength());
				System.out.printf("Lock: Queued Threads: ");
				Collection<Thread> lockedThreads=lock.getThreads();
				for (Thread lockedThread : lockedThreads) {
				System.out.printf("%s ",lockedThread.getName());
				}
				System.out.printf("\n");
			}
			System.out.printf("Lock: Fairness: %s\n",lock.isFair());
			System.out.printf("Lock: Locked: %s\n",lock.isLocked());
			System.out.printf("************************\n");
			/*
			 * Sleep the thread for one second
			 */
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
