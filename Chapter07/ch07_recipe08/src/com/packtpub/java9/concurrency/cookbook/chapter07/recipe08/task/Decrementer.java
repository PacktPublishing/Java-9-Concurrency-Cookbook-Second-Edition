package com.packtpub.java9.concurrency.cookbook.chapter07.recipe08.task;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * This task implements a decrementer. It decrements by 1 unit all the
 * elements of an array
 *
 */
public class Decrementer implements Runnable {

	/**
	 * The array to decrement the elements
	 */
	private final AtomicIntegerArray vector; 
	
	/**
	 * Constructor of the class
	 * @param vector The array to decrement is elements
	 */
	public Decrementer(AtomicIntegerArray vector) {
		this.vector=vector;
	}
	
	/**
	 * Main method of the class. It decrements all the elements of the 
	 * array
	 */
	@Override
	public void run() {
		for (int i=0; i<vector.length(); i++) {
			vector.getAndDecrement(i);
		}	
	}

}
