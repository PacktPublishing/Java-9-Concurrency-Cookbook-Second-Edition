package com.packtpub.java9.concurrency.cookbook.chapter04.recipe09.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * This class simulates the generation of a report. Is a Callable
 * object that will be executed by the executor inside a 
 * CompletionService
 *
 */
public class ReportGenerator implements Callable<String> {

	/**
	 * The sender of the report
	 */
	private final String sender;
	/**
	 * The title of the report
	 */
	private final String title;
	
	/**
	 * Constructor of the class. Initializes the two attributes
	 * @param sender The sender of the report
	 * @param title The title of the report
	 */
	public ReportGenerator(String sender, String title){
		this.sender=sender;
		this.title=title;
	}

	/**
	 * Main method of the ReportGenerator. Waits a random period of time
	 * and then generates the report as a String.
	 */
	@Override
	public String call() throws Exception {
		try {
			Long duration=(long)(Math.random()*10);
			System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n",this.sender,this.title,duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String ret=sender+": "+title;
		return ret;
	}
	
}
