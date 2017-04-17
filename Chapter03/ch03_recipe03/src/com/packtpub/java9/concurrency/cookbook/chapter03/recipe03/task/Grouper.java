package com.packtpub.java9.concurrency.cookbook.chapter03.recipe03.task;

import com.packtpub.java9.concurrency.cookbook.chapter03.recipe03.utils.Results;


/**
 * Group the results of each Searcher. Sum the values stored in the Results object 
 * An object of this class is executed automatically by the CyclicBarrier when
 * all the Searchers finish its job
 */
public class Grouper implements Runnable {

	/**
	 * Results object with the occurrences of the number in each row
	 */
	private final Results results;
	
	/**
	 * Constructor of the class. Initializes its attributes
	 * @param results Results object with the ocurrences of the number in each row
	 */
	public Grouper(Results results){
		this.results=results;
	}
	
	/**
	 * Main method of the Grouper. Sum the values stored in the Results object 
	 */
	@Override
	public void run() {
		int finalResult=0;
		System.out.printf("Grouper: Processing results...\n");
		int data[]=results.getData();
		for (int number:data){
			finalResult+=number;
		}
		System.out.printf("Grouper: Total result: %d.\n",finalResult);
	}

}
