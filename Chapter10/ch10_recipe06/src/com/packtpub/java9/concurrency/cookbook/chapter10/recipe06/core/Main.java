package com.packtpub.java9.concurrency.cookbook.chapter10.recipe06.core;

import com.packtpub.java9.concurrency.cookbook.chapter10.recipe06.task.TaskLocalRandom;

/**
 * Main class of the example. It creates three task and execute them
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * Create an array to store the threads 
		 */
		Thread threads[]=new Thread[3];
		
		/*
		 * Launch three tasks
		 */
		for (int i=0; i<threads.length; i++) {
			TaskLocalRandom task=new TaskLocalRandom();
			threads[i]=new Thread(task);
			threads[i].start();
		}

	}

}
