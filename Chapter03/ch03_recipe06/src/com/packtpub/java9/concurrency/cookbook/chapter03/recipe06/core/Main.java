package com.packtpub.java9.concurrency.cookbook.chapter03.recipe06.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import com.packtpub.java9.concurrency.cookbook.chapter03.recipe06.task.Consumer;
import com.packtpub.java9.concurrency.cookbook.chapter03.recipe06.task.Producer;

/**
 * Main class of the example
 *
 */
public class Main {

	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Creates two buffers
		List<String> buffer1=new ArrayList<>();
		List<String> buffer2=new ArrayList<>();
		
		// Creates the exchanger
		Exchanger<List<String>> exchanger=new Exchanger<>();
		
		// Creates the producer
		Producer producer=new Producer(buffer1, exchanger);
		// Creates the consumer
		Consumer consumer=new Consumer(buffer2, exchanger);
		
		// Creates and starts the threads
		Thread threadProducer=new Thread(producer);
		Thread threadConsumer=new Thread(consumer);
		
		threadProducer.start();
		threadConsumer.start();

	}

}
