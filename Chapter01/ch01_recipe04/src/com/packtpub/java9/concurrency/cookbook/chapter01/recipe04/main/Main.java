package com.packtpub.java9.concurrency.cookbook.chapter01.recipe04.main;

import java.util.concurrent.TimeUnit;

import com.packtpub.java7.concurrency.cookbook.chapter01.recipe04.task.ConsoleClock;

/**
 * Main class of the Example. Creates a FileClock runnable object and a Thread
 * to run it. Starts the Thread, waits five seconds and interrupts it.
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates a FileClock runnable object and a Thread
		// to run it
		ConsoleClock clock = new ConsoleClock();
		Thread thread = new Thread(clock);

		// Starts the Thread
		thread.start();
		try {
			// Waits five seconds
			TimeUnit.SECONDS.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Interrupts the Thread
		thread.interrupt();
	}
}
