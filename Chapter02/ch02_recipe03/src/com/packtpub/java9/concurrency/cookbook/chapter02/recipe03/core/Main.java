package com.packtpub.java9.concurrency.cookbook.chapter02.recipe03.core;

import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter02.recipe03.task.Job;
import com.packtpub.java9.concurrency.cookbook.chapter02.recipe03.task.PrintQueue;

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
		
		System.out.printf("Running example with fair-mode = false\n");
		testPrintQueue(false);
		System.out.printf("Running example with fair-mode = true\n");
		testPrintQueue(true);
	}

	private static void testPrintQueue(boolean fairMode) {
		// Creates the print queue
		PrintQueue printQueue=new PrintQueue(fairMode);
		
		// Creates ten Threads
		Thread thread[]=new Thread[10];
		for (int i=0; i<10; i++){
			thread[i]=new Thread(new Job(printQueue),"Thread "+i);
		}
		
		// Starts the Threads
		for (int i=0; i<10; i++){
			thread[i].start();
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
		}
		
		// Wait for the end of the threads
		for (int i=0; i<10; i++) {
			try {
				thread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
