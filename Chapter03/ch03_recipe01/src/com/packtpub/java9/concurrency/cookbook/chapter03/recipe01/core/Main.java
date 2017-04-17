package com.packtpub.java9.concurrency.cookbook.chapter03.recipe01.core;

import com.packtpub.java9.concurrency.cookbook.chapter03.recipe01.task.Job;
import com.packtpub.java9.concurrency.cookbook.chapter03.recipe01.task.PrintQueue;

/**
 * Main class of the example.
 *
 */
public class Main {

	/**
	 * Main method of the class. Run ten jobs in parallel that
	 * send documents to the print queue at the same time.
	 */
	public static void main (String args[]){
		
		// Creates the print queue
		PrintQueue printQueue=new PrintQueue();
		
		// Creates twelve Threads
		Thread[] threads=new Thread[12];
		for (int i=0; i < threads.length; i++){
			threads[i]=new Thread(new Job(printQueue),"Thread "+i);
		}
		
		// Starts the Threads
		for (int i=0; i < threads.length; i++){
			threads[i].start();
		}
	}

}
