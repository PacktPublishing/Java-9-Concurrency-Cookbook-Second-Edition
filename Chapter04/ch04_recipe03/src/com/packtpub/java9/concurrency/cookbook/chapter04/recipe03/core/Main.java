package com.packtpub.java9.concurrency.cookbook.chapter04.recipe03.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.packtpub.java9.concurrency.cookbook.chapter04.recipe03.task.ValidatorTask;
import com.packtpub.java9.concurrency.cookbook.chapter04.recipe03.task.UserValidator;

/**
 * This is the main class of the example. Creates two user validation systems and execute
 * them in an Executor using the invokeAny() method. If the user is validated by one of the
 * user validation systems, then it shows a message. If both system don't validate the user,
 * the application proccess the ExecutionException throwed by the method
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Initialize the parameters of the user
		String username="test";
		String password="test";
		
		// Create two user validation objects
		UserValidator ldapValidator=new UserValidator("LDAP");
		UserValidator dbValidator=new UserValidator("DataBase");
		
		// Create two tasks for the user validation objects
		ValidatorTask ldapTask=new ValidatorTask(ldapValidator, username, password);
		ValidatorTask dbTask=new ValidatorTask(dbValidator,username,password);
		
		// Add the two tasks to a list of tasks
		List<ValidatorTask> taskList=new ArrayList<>();
		taskList.add(ldapTask);
		taskList.add(dbTask);
		
		// Create a new Executor
		ExecutorService executor=(ExecutorService)Executors.newCachedThreadPool();
		String result;
		try {
			// Send the list of tasks to the executor and waits for the result of the first task 
			// that finish without throw and Exception. If all the tasks throw and Exception, the
			// method throws and ExecutionException.
			result = executor.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n",result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		// Shutdown the Executor
		executor.shutdown();
		System.out.printf("Main: End of the Execution\n");
	}

}
