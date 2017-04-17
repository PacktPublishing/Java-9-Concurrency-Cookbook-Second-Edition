package com.packtpub.java9.concurrency.cookbook.chapter09.recipe01.lock;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create a specific class to extend the ReentrantLock class.
 * The main objective of this class is to convert in public two
 * protected methods. 
 * getOwnerName() returns the name of the thread that have the control
 * of the lock and uses the proctected method getOwner();
 * getThreads() returns the list of threads queued in the lock and uses
 * the protected method getQueuedThreads();
 */
public class MyLock extends ReentrantLock {

	/**
	 * Declare the serial version uid of the class
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This method returns the name of the thread that have the control of the Lock of the constant "None"
	 * if the Lock is free 
	 * @return The name of the thread that has the control of the lock
	 */
	public String getOwnerName() {
		if (this.getOwner()==null) {
			return "None";
		}
		return this.getOwner().getName();
	}
	
	/**
	 * This method returns the list of the threads queued in the lock
	 * @return The list of threads queued in the Lock
	 */
	public Collection<Thread> getThreads() {
		return this.getQueuedThreads();
	}
}
