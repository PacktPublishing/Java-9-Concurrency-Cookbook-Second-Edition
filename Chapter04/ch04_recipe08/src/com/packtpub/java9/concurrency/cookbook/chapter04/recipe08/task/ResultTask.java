package com.packtpub.java9.concurrency.cookbook.chapter04.recipe08.task;

import java.util.concurrent.FutureTask;

/**
 * This class manage the execution of a ExecutableTaks. Overrides
 * the done() method that is called when the task finish its execution 
 *
 */
public class ResultTask extends FutureTask<String> {

	/**
	 * Name of the ResultTask. It's initialized with the name of the
	 * ExecutableTask that manages
	 */
	private final String name;
	
	/**
	 * Constructor of the Class. Override one of the constructor of its parent class 
	 * @param callable The task this object manages
	 */
	public ResultTask(ExecutableTask callable) {
		super(callable);
		this.name=callable.getName();
	}

	/**
	 * Method that is called when the task finish.
	 */
	@Override
	protected void done() {
		if (isCancelled()) {
			System.out.printf("%s: Has been cancelled\n",name);
		} else {
			System.out.printf("%s: Has finished\n",name);
		}
	}

}
