package com.packtpub.java9.concurrency.cookbook.chapter01.recipe09.group;

/**
 * Class that extends the ThreadGroup class to implement a uncaught exceptions
 * method
 *
 */
public class MyThreadGroup extends ThreadGroup {

	/**
	 * Constructor of the class. Calls the parent class constructor
	 * 
	 * @param name
	 */
	public MyThreadGroup(String name) {
		super(name);
	}

	/**
	 * Method for process the uncaught exceptions
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// Prints the name of the Thread
		System.out.printf("The thread %s has thrown an Exception\n", t.getId());
		// Print the stack trace of the exception
		e.printStackTrace(System.out);
		// Interrupt the rest of the threads of the thread group
		System.out.printf("Terminating the rest of the Threads\n");
		interrupt();
	}
}
