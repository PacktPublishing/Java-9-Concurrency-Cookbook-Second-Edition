package com.packtpub.java9.concurrency.cookbook.chapter03.recipe06.task;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * This class implements the consumer of the example
 *
 */
public class Consumer implements Runnable {

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
	public Consumer(List<String> buffer, Exchanger<List<String>> exchanger){
		this.buffer=buffer;
		this.exchanger=exchanger;
	}
	
	/**
	 * Main method of the producer. It consumes all the events produced by the Producer. After
	 * processes ten events, it uses the exchanger object to synchronize with 
	 * the producer. It sends to the producer an empty buffer and receives a buffer with ten events
	 */
	@Override
	public void run() {
				
		for (int cycle = 1; cycle <= 10; cycle++){
			System.out.printf("Consumer: Cycle %d\n",cycle);

			try {
				// Wait for the produced data and send the empty buffer to the producer
				buffer=exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.printf("Consumer: %d\n",buffer.size());
			
			for (int j=0; j<10; j++){
				String message=buffer.get(0);
				System.out.printf("Consumer: %s\n",message);
				buffer.remove(0);
			}

		}
		
	}

}
