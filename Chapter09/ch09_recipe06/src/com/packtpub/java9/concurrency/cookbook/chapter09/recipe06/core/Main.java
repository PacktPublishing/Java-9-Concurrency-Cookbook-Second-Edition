package com.packtpub.java9.concurrency.cookbook.chapter09.recipe06.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.packtpub.java9.concurrency.cookbook.chapter09.recipe06.logger.MyLoggerFactory;
import com.packtpub.java9.concurrency.cookbook.chapter09.recipe06.task.Task;

/**
 * Main class of the example. It launch five Task objects and write
 * some log messages indicating the evolution of the execution of the program
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * Get the Logger object
		 */
		Logger logger=MyLoggerFactory.getLogger(Main.class.getName());
		
		/*
		 * Write a message indicating the start of the execution
		 */
		logger.entering(Main.class.getName(), "main()",args);
		
		/*
		 * Create and launch five Task objects
		 */
		Thread threads[]=new Thread[5];
		for (int i=0; i<threads.length; i++) {
			logger.log(Level.INFO,"Launching thread: "+i);
			Task task=new Task();
			threads[i]=new Thread(task);
			logger.log(Level.INFO,"Thread created: "+threads[i].getName());
			threads[i].start();
		}
		
		/*
		 * Write a log message indicating that the threads have been created
		 */
		logger.log(Level.INFO,"Ten Threads created. Waiting for its finalization");
		
		/*
		 * Wait for the finalization of the threads
		 */
		for (int i=0; i<threads.length; i++) {
			try {
				threads[i].join();
				logger.log(Level.INFO,"Thread has finished its execution",threads[i]);
			} catch (InterruptedException e) {
				logger.log(Level.SEVERE, "Exception", e);
			}
		}
		
		/*
		 * Write a log message indicating the end of the program
		 */
		logger.exiting(Main.class.getName(), "main()");
	}

}
