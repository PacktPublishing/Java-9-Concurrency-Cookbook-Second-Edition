package com.packtpub.java9.concurrency.cookbook.chapter04.recipe09.task;

import java.util.concurrent.CompletionService;

/**
 * This class represents every actor that can request a report. For this example,
 * it simply create three ReportGenerator objects and execute them through a 
 * CompletionService
 *
 */
public class ReportRequest implements Runnable {

	/**
	 * Name of this ReportRequest
	 */
	private final String name;
	
	/**
	 * CompletionService used for the execution of the ReportGenerator tasks
	 */
	private final CompletionService<String> service;
	
	/**
	 * Constructor of the class. Initializes the parameters
	 * @param name Name of the ReportRequest
	 * @param service Service used for the execution of tasks
	 */
	public ReportRequest(String name, CompletionService<String> service){
		this.name=name;
		this.service=service;
	}

	/**
	 * Main method of the class. Create three ReportGenerator tasks and executes them
	 * through a CompletionService
	 */
	@Override
	public void run() {
			ReportGenerator reportGenerator=new ReportGenerator(name, "Report");
			service.submit(reportGenerator);
	}

}
