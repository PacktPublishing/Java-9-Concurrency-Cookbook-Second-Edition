package com.packtpub.java9.concurrency.cookbook.chapter02.recipe02.core;

import com.packtpub.java9.concurrency.cookbook.chapter02.recipe02.task.Consumer;
import com.packtpub.java9.concurrency.cookbook.chapter02.recipe02.task.EventStorage;
import com.packtpub.java9.concurrency.cookbook.chapter02.recipe02.task.Producer;

/**
 * Main class of the example
 */
public class Main {

	/**
	 * Main method of the example
	 */
	public static void main(String[] args) {
		
		// Creates an event storage
		EventStorage storage=new EventStorage();
		
		// Creates a Producer and a Thread to run it
		Producer producer=new Producer(storage);
		Thread thread1=new Thread(producer);

		// Creates a Consumer and a Thread to run it
		Consumer consumer=new Consumer(storage);
		Thread thread2=new Thread(consumer);
		
		// Starts the thread
		thread2.start();
		thread1.start();
	}

}
