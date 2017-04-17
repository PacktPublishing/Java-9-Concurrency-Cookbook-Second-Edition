package com.packtpub.java9.concurrency.cookbook.chapter10.recipe03.task;

import java.util.concurrent.LinkedTransferQueue;

/**
 * This class implements a Consumer of Strings. It takes
 * 10000 Strings from the buffer
 *
 */
public class Consumer implements Runnable {

	/**
	 * Buffer to take the Strings
	 */
	private final LinkedTransferQueue<String> buffer;
	
	/**
	 * Name of the Consumer
	 */
	private final String name;
	
	/**
	 * Constructor of the class. It initializes all its attributes
	 * @param name Name of the consumer
	 * @param buffer Buffer to take the Strings
	 */
	public Consumer(String name, LinkedTransferQueue<String> buffer){
		this.name=name;
		this.buffer=buffer;
	}
	
	/**
	 * Main method of the consumer. It takes 10000 Strings from the 
	 * buffer
	 */
	@Override
	public void run() {
		for (int i=0; i<10000; i++){
			try {
				buffer.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Consumer: %s: Consumer done\n",name);
	}

}
