package com.packtpub.java9.concurrency.cookbook.chapter03.recipe01.task;

/**
 * This class simulates a job that send a document to print.
 *
 */
public class Job implements Runnable {

	/**
	 * Queue to print the documents
	 */
	private PrintQueue printQueue;
	
	/**
	 * Constructor of the class. Initializes the queue
	 * @param printQueue
	 */
	public Job(PrintQueue printQueue){
		this.printQueue=printQueue;
	}
	
	/**
	 * Core method of the Job. Sends the document to the print queue and waits
	 *  for its finalization
	 */
	@Override
	public void run() {
		System.out.printf("%s: Going to print a job\n",Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());		
	}
}
