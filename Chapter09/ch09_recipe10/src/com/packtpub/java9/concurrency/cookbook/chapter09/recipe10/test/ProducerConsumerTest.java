package com.packtpub.java9.concurrency.cookbook.chapter09.recipe10.test;

import java.util.concurrent.LinkedTransferQueue;

import edu.umd.cs.mtc.MultithreadedTestCase;

/**
 * This class implements a test of the LinkedTransferQueue. It has
 * two consumers and a producer. First, arrives the first consumer then
 * arrives the second consumer and finally the producer puts two Strings
 * in the buffer. 
 */
public class ProducerConsumerTest extends MultithreadedTestCase {

	/**
	 * Declare the buffer shared by the producer and the consumers 
	 */
	private LinkedTransferQueue<String> queue;
	
	/**
	 * Creates the buffer
	 */
	@Override
	public void initialize() {
		super.initialize();
		queue=new LinkedTransferQueue<String>();
		System.out.printf("Test: The test has been initialized\n");
	}
	
	/**
	 * This is the first consumer. It only consumes a String
	 * @throws InterruptedException
	 */
	public void thread1() throws InterruptedException {
		String ret=queue.take();
		System.out.printf("Thread 1: %s\n",ret);
	}
	
	/**
	 * This is the second consumer. It waits for the first tick that
	 * happens when the first consumer arrives. Then, consumes a String
	 * @throws InterruptedException
	 */
	public void thread2() throws InterruptedException {
		waitForTick(1);
		String ret=queue.take();
		System.out.printf("Thread 2: %s\n",ret);
	}
	
	/**
	 * This is the Producer. It waits for the first tick that happens when
	 * the first consumer arrives. Then, waits for the second tick that 
	 * happens when the second consumer arrives. Finally, put two strings in
	 * the buffer.
	 */
	public void thread3() {
		waitForTick(1);
		waitForTick(2);
		queue.put("Event 1");
		queue.put("Event 2");
		System.out.printf("Thread 3: Inserted two elements\n");
	}
	
	/**
	 * This method is executed when the test finish its execution. It checks that
	 * the buffer is empty
	 */
	@Override
	public void finish() {
		super.finish();
		System.out.printf("Test: End\n");
		assertEquals(true, queue.size()==0);
		System.out.printf("Test: Result: The queue is empty\n");
	}
}
