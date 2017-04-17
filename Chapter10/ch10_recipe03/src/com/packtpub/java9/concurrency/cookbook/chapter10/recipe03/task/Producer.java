package com.packtpub.java9.concurrency.cookbook.chapter10.recipe03.task;

import java.util.concurrent.LinkedTransferQueue;

/**
 * This class implements a Producer of Strings. It generates
 * 10000 strings and stores them in the buffer.
 *
 */
public class Producer implements Runnable {

	/**
	 * Buffer to store the Strings
	 */
	private final LinkedTransferQueue<String> buffer;
	
	/**
	 * Name of the producer
	 */
	private final String name;
	
	/**
	 * Constructor of the class. Initializes its parameters
	 * @param name Name of the producer
	 * @param buffer Buffer to store the objects
	 */
	public Producer(String name, LinkedTransferQueue<String> buffer){
		this.name=name;
		this.buffer=buffer;
	}

	/**
	 * Main method of the producer. Generates 10000 of Strings
	 * and stores them in the buffer
	 */
	@Override
	public void run() {
		for (int i=0; i<10000; i++) {
			buffer.put(name+": Element "+i);
		}
		System.out.printf("Producer: %s: Producer done\n",name);
	}

}
