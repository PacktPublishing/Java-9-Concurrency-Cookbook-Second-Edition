package com.packtpub.java9.concurrency.cookbook.chapter02.recipe05.task;

import com.packtpub.java9.concurrency.cookbook.chapter02.recipe05.utils.FileMock;

/**
 * This class gets lines from the simulate file and stores them in the buffer,
 * if there is space in it.
 *
 */
public class Producer implements Runnable {

	/**
	 * Simulated File
	 */
	private FileMock mock;

	/**
	 * Buffer
	 */
	private Buffer buffer;

	/**
	 * Constructor of the class. Initialize the objects
	 * 
	 * @param mock
	 *            Simulated file
	 * @param buffer
	 *            Buffer
	 */
	public Producer(FileMock mock, Buffer buffer) {
		this.mock = mock;
		this.buffer = buffer;
	}

	/**
	 * Core method of the producer. While are pending lines in the simulated
	 * file, reads one and try to store it in the buffer.
	 */
	@Override
	public void run() {
		buffer.setPendingLines(true);
		while (mock.hasMoreLines()) {
			String line = mock.getLine();
			buffer.insert(line);
		}
		buffer.setPendingLines(false);
	}

}
