package com.packtpub.java9.concurrency.cookbook.chapter04.recipe09.task;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * This class will take the results of the ReportGenerator tasks executed through
 * a CompletinoService
 *
 */
public class ReportProcessor implements Runnable {

	/**
	 * CompletionService that executes the ReportGenerator tasks
	 */
	private final CompletionService<String> service;
	/**
	 * Variable to store the status of the Object. It will executes until the variable
	 * takes the true value
	 */
	private volatile boolean end;
	
	/**
	 * Constructor of the class. It initializes the attributes of the class
	 * @param service The CompletionService used to execute the ReportGenerator tasks
	 */
	public ReportProcessor (CompletionService<String> service){
		this.service=service;
		end=false;
	}

	/**
	 * Main method of the class. While the variable end is false, it
	 * calls the poll method of the CompletionService and waits 20 seconds
	 * for the end of a ReportGenerator task
	 */
	@Override
	public void run() {
		while (!end){
			try {
				Future<String> result=service.poll(20, TimeUnit.SECONDS);
				if (result!=null) {
					String report=result.get();
					System.out.printf("ReportProcessor: Report Recived: %s\n",report);
				}			
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		System.out.printf("ReportProcessor: End\n");
	}

	/**
	 * Method that establish the value of the end attribute
	 * @param end New value of the end attribute.
	 */
	public void stopProcessing() {
		this.end = true;
	}
	
	
}
