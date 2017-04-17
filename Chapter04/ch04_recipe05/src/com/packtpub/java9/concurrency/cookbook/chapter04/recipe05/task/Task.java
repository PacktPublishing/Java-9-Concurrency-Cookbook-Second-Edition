package com.packtpub.java9.concurrency.cookbook.chapter04.recipe05.task;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * This class implements the task of this example. Writes a
 * message to the console with the actual date and returns the
 * 'Hello, world' string
 *
 */
public class Task implements Callable<String> {

	/**
	 * Name of the task
	 */
	private final String name;
	
	/**
	 * Constructor of the class
	 * @param name Name of the task
	 */
	public Task(String name) {
		this.name=name;
	}
	
	/**
	 * Main method of the task. Writes a message to the console with
	 * the actual date and returns the 'Hello world' string
	 */
	@Override
	public String call() throws Exception {
		System.out.printf("%s: Starting at : %s\n",name,new Date());
		return "Hello, world";
	}

}
