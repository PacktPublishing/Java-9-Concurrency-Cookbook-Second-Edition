package com.packtpub.java9.concurrency.cookbook.chapter10.recipe01.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter10.recipe01.task.FileSearch;
import com.packtpub.java9.concurrency.cookbook.chapter10.recipe01.task.Task;

/**
 * Main class of the example. Create three FileSearch objects, encapsulate inside
 * three Task objects and execute them as they were callable objects
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a new Executor
		ExecutorService executor=Executors.newCachedThreadPool();

		// Create three FileSearch objects
		FileSearch system=new FileSearch("C:\\Windows", "log");
		FileSearch apps=new FileSearch("C:\\Program Files","log");
		FileSearch documents=new FileSearch("C:\\Documents And Settings","log");
		
		// Create three Task objects
		Task systemTask=new Task(system,null);
		Task appsTask=new Task(apps,null);
		Task documentsTask=new Task(documents,null);
		
		// Submit the Tasks to the Executor
		executor.submit(systemTask);
		executor.submit(appsTask);
		executor.submit(documentsTask);
		
		// Shutdown the executor and wait for the end of the tasks
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Write to the console the number of results
		try {
			System.out.printf("Main: System Task: Number of Results: %d\n",systemTask.get().size());
			System.out.printf("Main: App Task: Number of Results: %d\n",appsTask.get().size());
			System.out.printf("Main: Documents Task: Number of Results: %d\n",documentsTask.get().size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}

}
