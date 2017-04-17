package com.packtpub.java9.concurrency.cookbook.chapter03.recipe06.task;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * This class implements the producer
 *
 */
public class Producer implements Runnable {

	/**
	 * Buffer to save the events produced
	 */
	private List<String> buffer;
	
	/**
	 * Exchager to synchronize with the consumer
	 */
	private final Exchanger<List<String>> exchanger;
	
	/**
	 * Constructor of the class. Initializes its attributes
	 * @param buffer Buffer to save the events produced
	 * @param exchanger Exchanger to syncrhonize with the consumer
	 */
	public Producer (List<String> buffer, Exchanger<List<String>> exchanger){
		this.buffer=buffer;
		this.exchanger=exchanger;
	}
	
	/**
	 * Main method of the producer. It produces 100 events. 10 cicles of 10 events.
	 * After produce 10 events, it uses the exchanger object to synchronize with 
	 * the consumer. The producer sends to the consumer the buffer with ten events and
	 * receives from the consumer an empty buffer
	 */
	@Override
	public void run() {
		
		for (int cycle=1; cycle<=10; cycle++){
			System.out.printf("Producer: Cycle %d\n",cycle);
			
			for (int j=0; j<10; j++){
				String message="Event "+(((cycle-1)*10)+j);
				System.out.printf("Producer: %s\n",message);
				buffer.add(message);
			}
			
			try {
				/*
				 * Change the data buffer with the consumer
				 */
				buffer=exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.printf("Producer: %d\n",buffer.size());

		}
		
	}
	
	

}
