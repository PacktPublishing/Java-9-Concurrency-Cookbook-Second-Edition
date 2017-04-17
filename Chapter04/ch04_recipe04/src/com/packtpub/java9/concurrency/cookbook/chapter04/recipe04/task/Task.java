package com.packtpub.java9.concurrency.cookbook.chapter04.recipe04.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * This class implements the task of this example. It waits during a random
 * period of time and then calculate the sum of five random numbers 
 *
 */
public class Task implements Callable<Result> {

	/**
	 * The name of the Task
	 */
	private final String name;
	
	/**
	 * Constructor of the class
	 * @param name Initializes the name of the task
	 */
	public Task(String name) {
		this.name=name;
	}
	
	/**
	 * Main method of the task. Waits during a random period of time and then
	 * calculates the sum of five random numbers
	 */
	@Override
	public Result call() throws Exception {
		// Writes a message to the console
		System.out.printf("%s: Staring\n",this.name);
		
		// Waits during a random period of time
		try {
			Long duration=(long)(Math.random()*10);
			System.out.printf("%s: Waiting %d seconds for results.\n",this.name,duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		// Calculates the sum of five random numbers
		int value=0;
		for (int i=0; i<5; i++){
			value+=(int)(Math.random()*100);

		}
		
		// Creates the object with the results
		Result result=new Result();
		result.setName(this.name);
		result.setValue(value);
		System.out.printf("%s: Ends\n",this.name);

		// Returns the result object
		return result;
	}

}
