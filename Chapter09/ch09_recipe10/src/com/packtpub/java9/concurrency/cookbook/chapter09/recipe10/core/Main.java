package com.packtpub.java9.concurrency.cookbook.chapter09.recipe10.core;

import com.packtpub.java9.concurrency.cookbook.chapter09.recipe10.test.ProducerConsumerTest;

import edu.umd.cs.mtc.TestFramework;

/**
 * Main class of the example. Executes the test of the LinkedTransferQueue
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Throwable {
		
		/*
		 * Create a Test object
		 */
		ProducerConsumerTest test=new ProducerConsumerTest();
		
		/*
		 * Execute the test
		 */
		System.out.printf("Main: Starting the test\n");
		TestFramework.runOnce(test);
		System.out.printf("Main: The test has finished\n");

	}

}
