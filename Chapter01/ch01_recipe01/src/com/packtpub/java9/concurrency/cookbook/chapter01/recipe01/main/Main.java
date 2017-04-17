package com.packtpub.java9.concurrency.cookbook.chapter01.recipe01.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

import com.packtpub.java9.concurrency.cookbook.chapter01.recipe01.task.Calculator;

/**
 * Main class of the example
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Thread priority infomation
		System.out.printf("Minimum Priority: %s\n", Thread.MIN_PRIORITY);
		System.out.printf("Normal Priority: %s\n", Thread.NORM_PRIORITY);
		System.out.printf("Maximun Priority: %s\n", Thread.MAX_PRIORITY);

		Thread threads[];
		Thread.State status[];

		// Launch 10 threads to do the operation, 5 with the max
		// priority, 5 with the min
		threads = new Thread[10];
		status = new Thread.State[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator());
			if ((i % 2) == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("My Thread " + i);
		}

		// Wait for the finalization of the threads. Meanwhile,
		// write the status of those threads in a file
		try (FileWriter file = new FileWriter(".\\data\\log.txt"); PrintWriter pw = new PrintWriter(file);) {

			// Write the status of the threads
			for (int i = 0; i < 10; i++) {
				pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
				status[i] = threads[i].getState();
			}

			// Start the ten threads
			for (int i = 0; i < 10; i++) {
				threads[i].start();
			}

			// Wait for the finalization of the threads. We save the status of
			// the threads and only write the status if it changes.
			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					if (threads[i].getState() != status[i]) {
						writeThreadInfo(pw, threads[i], status[i]);
						status[i] = threads[i].getState();
					}
				}

				finish = true;
				for (int i = 0; i < 10; i++) {
					finish = finish && (threads[i].getState() == State.TERMINATED);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method writes the state of a thread in a file
	 * 
	 * @param pw
	 *            : PrintWriter to write the data
	 * @param thread
	 *            : Thread whose information will be written
	 * @param state
	 *            : Old state of the thread
	 */
	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n", thread.getState());
		pw.printf("Main : ************************************\n");
	}

}
