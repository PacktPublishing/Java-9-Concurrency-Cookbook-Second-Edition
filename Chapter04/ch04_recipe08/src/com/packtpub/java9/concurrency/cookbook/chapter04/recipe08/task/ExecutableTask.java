package com.packtpub.java9.concurrency.cookbook.chapter04.recipe08.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * This class implements the task of this example. It waits a random period of time
 *
 */
public class ExecutableTask implements Callable<String> {

	/**
	 * The name of the class
	 */
	private final String name;
	
	/**
	 * Constructor of the class
	 * @param name The name of the class
	 */
	public ExecutableTask(String name){
		this.name=name;
	}
	
	/**
	 * Main method of the task. It waits a random period of time and returns a message
	 */
	@Override
	public String call() throws Exception {
		try {
			Long duration=(long)(Math.random()*10);
			System.out.printf("%s: Waiting %d seconds for results.\n",this.name,duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
		}		
		return "Hello, world. I'm "+name;
	}

	/**
	 * This method returns the name of the task
	 * @return The name of the task
	 */
	public String getName(){
		return name;
	}
}
