package com.packtpub.java9.concurrency.cookbook.chapter07.recipe08.task;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * This task implements an incrementer. It increments by 1
 * all the elements of an array
 *
 */
public class Incrementer implements Runnable {

	/**
	 * Array that store the elements to increment
	 */
	private final AtomicIntegerArray vector; 
	
	/**
	 * Constructor of the class
	 * @param vector Array to store the elements to increment
	 */
	public Incrementer(AtomicIntegerArray vector) {
		this.vector=vector;
	}
	
	/**
	 * Main method of the task. Increment all the elements of the
	 * array
	 */
	@Override
	public void run() {
		
		for (int i=0; i<vector.length(); i++){
			vector.getAndIncrement(i);
		}
		
	}

}
