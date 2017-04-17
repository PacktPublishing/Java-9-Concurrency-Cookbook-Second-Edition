package com.packtpub.java9.concurrency.cookbook.chapter01.recipe02.main;

import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter01.recipe02.task.PrimeGenerator;

/**
 * Main class of the sample. Launch the PrimeGenerator, waits five seconds and
 * interrupts the Thread
 */
public class Main {

	/**
	 * Main method of the sample. Launch the PrimeGenerator, waits five seconds
	 * and interrupts the Thread
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Launch the prime numbers generator
		Thread task = new PrimeGenerator();
		task.start();

		// Wait 5 seconds
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Interrupt the prime number generator
		task.interrupt();

		// Write information about the status of the Thread
		System.out.printf("Main: Status of the Thread: %s\n", task.getState());
		System.out.printf("Main: isInterrupted: %s\n", task.isInterrupted());
		System.out.printf("Main: isAlive: %s\n", task.isAlive());
	}

}
