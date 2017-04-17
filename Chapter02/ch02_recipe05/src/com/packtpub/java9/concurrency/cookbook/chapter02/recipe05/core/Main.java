package com.packtpub.java9.concurrency.cookbook.chapter02.recipe05.core;

import com.packtpub.java9.concurrency.cookbook.chapter02.recipe05.task.Buffer;
import com.packtpub.java9.concurrency.cookbook.chapter02.recipe05.task.Consumer;
import com.packtpub.java9.concurrency.cookbook.chapter02.recipe05.task.Producer;
import com.packtpub.java9.concurrency.cookbook.chapter02.recipe05.utils.FileMock;

/**
 * Main class of the example
 *
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * Creates a simulated file with 100 lines
		 */
		FileMock mock = new FileMock(101, 10);

		/**
		 * Creates a buffer with a maximum of 20 lines
		 */
		Buffer buffer = new Buffer(20);

		/**
		 * Creates a producer and a thread to run it
		 */
		Producer producer = new Producer(mock, buffer);
		Thread producerThread = new Thread(producer, "Producer");

		/**
		 * Creates three consumers and threads to run them
		 */
		Consumer consumers[] = new Consumer[3];
		Thread consumersThreads[] = new Thread[3];

		for (int i = 0; i < 3; i++) {
			consumers[i] = new Consumer(buffer);
			consumersThreads[i] = new Thread(consumers[i], "Consumer " + i);
		}

		/**
		 * Strats the producer and the consumers
		 */
		producerThread.start();
		for (int i = 0; i < 3; i++) {
			consumersThreads[i].start();
		}
	}

}
