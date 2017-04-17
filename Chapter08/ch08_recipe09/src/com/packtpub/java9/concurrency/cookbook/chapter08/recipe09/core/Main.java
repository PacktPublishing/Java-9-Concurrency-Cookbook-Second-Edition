package com.packtpub.java9.concurrency.cookbook.chapter08.recipe09.core;

import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter08.recipe09.task.Consumer;
import com.packtpub.java9.concurrency.cookbook.chapter08.recipe09.task.Event;
import com.packtpub.java9.concurrency.cookbook.chapter08.recipe09.task.MyPriorityTransferQueue;
import com.packtpub.java9.concurrency.cookbook.chapter08.recipe09.task.Producer;

/**
 * Main class of the example.
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		/*
		 * Create a Prioriy Transfer Queue
		 */
		MyPriorityTransferQueue<Event> buffer=new MyPriorityTransferQueue<>();
		
		/*
		 * Create a Producer object
		 */
		Producer producer=new Producer(buffer);
		
		/*
		 * Launch 10 producers
		 */
		Thread producerThreads[]=new Thread[10];
		for (int i=0; i<producerThreads.length; i++) {
			producerThreads[i]=new Thread(producer);
			producerThreads[i].start();
		}

		/*
		 * Create and launch the consumer
		 */
		Consumer consumer=new Consumer(buffer);
		Thread consumerThread=new Thread(consumer);
		consumerThread.start();

		/*
		 * Write in the console the actual consumer count
		 */
		System.out.printf("Main: Buffer: Consumer count: %d\n",buffer.getWaitingConsumerCount());

		/*
		 * Transfer an event to the consumer
		 */
		Event myEvent=new Event("Core Event",0);
		buffer.transfer(myEvent);
		System.out.printf("Main: My Event has ben transfered.\n");
		
		/*
		 * Wait for the finalization of the producers
		 */
		for (int i=0; i<producerThreads.length; i++) {
			producerThreads[i].join();
		}
		
		/*
		 * Sleep the thread for one second
		 */
		TimeUnit.SECONDS.sleep(1);
		
		/*
		 * Write the actual consumer count
		 */
		System.out.printf("Main: Buffer: Consumer count: %d\n",buffer.getWaitingConsumerCount());
		
		/*
		 * Transfer another event
		 */
		myEvent=new Event("Core Event 2",0);
		buffer.transfer(myEvent);
		
		/*
		 * Wait for the finalization of the consumer
		 */
		consumerThread.join();
		
		/*
		 * Write a message indicating the end of the program
		 */
		System.out.printf("Main: End of the program\n");
	}

}
