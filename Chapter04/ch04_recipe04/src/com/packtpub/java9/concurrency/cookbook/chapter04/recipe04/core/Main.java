package com.packtpub.java9.concurrency.cookbook.chapter04.recipe04.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.packtpub.java9.concurrency.cookbook.chapter04.recipe04.task.Result;
import com.packtpub.java9.concurrency.cookbook.chapter04.recipe04.task.Task;

/**
 * Main class of the example. Launch three tasks using the invokeAll() method
 * and then prints their results to the console
 *
 */
public class Main {

	public static void main(String[] args) {

		// Create an executor
		ExecutorService executor=(ExecutorService)Executors.newCachedThreadPool();

		// Create three tasks and stores them in a List
		List<Task> taskList=new ArrayList<>();
		for (int i=0; i<10; i++){
			Task task=new Task("Task-"+i);
			taskList.add(task);
		}

		// Call the invokeAll() method
		List<Future<Result>>resultList=null;
		try {
			resultList=executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Finish the executor
		executor.shutdown();
		
		// Writes the results to the console
		System.out.printf("Core: Printing the results\n");
		for (int i=0; i<resultList.size(); i++){
			Future<Result> future=resultList.get(i);
			try {
				Result result=future.get();
				System.out.printf("%s: %s\n",result.getName(),result.getValue());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} 
		}
	}

}
