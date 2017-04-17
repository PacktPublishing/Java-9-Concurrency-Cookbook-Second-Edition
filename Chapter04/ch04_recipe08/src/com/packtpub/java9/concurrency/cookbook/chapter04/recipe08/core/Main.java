package com.packtpub.java9.concurrency.cookbook.chapter04.recipe08.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter04.recipe08.task.ExecutableTask;
import com.packtpub.java9.concurrency.cookbook.chapter04.recipe08.task.ResultTask;

/**
 * Main class of the example. Creates five tasks that wait a random period of time.
 * Waits 5 seconds and cancel all the tasks. Then, write the results of that tasks
 * that haven't been cancelled.
 *
 */
public class Main {

	/**
	 * Main method of the class.
	 * @param args
	 */
	public static void main(String[] args) {
		// Create an executor
		ExecutorService executor=Executors.newCachedThreadPool();
		
		//Create five tasks
		ResultTask resultTasks[]=new ResultTask[5];
		for (int i=0; i<5; i++) {
			ExecutableTask executableTask=new ExecutableTask("Task "+i);
			resultTasks[i]=new ResultTask(executableTask);
			executor.submit(resultTasks[i]);
		}
		
		// Sleep the thread five seconds
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// Cancel all the tasks. In the tasks that have finished before this moment, this
		// cancellation has no effects
		for (int i=0; i<resultTasks.length; i++) {
			resultTasks[i].cancel(true);
		}
		
		// Write the results of those tasks that haven't been cancelled
		for (int i=0; i<resultTasks.length; i++) {
			try {
				if (!resultTasks[i].isCancelled()){
					System.out.printf("%s\n",resultTasks[i].get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} 
		}
		// Finish the executor.
		executor.shutdown();

	}

}
