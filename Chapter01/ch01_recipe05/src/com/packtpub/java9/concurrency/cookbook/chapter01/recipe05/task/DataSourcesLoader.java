package com.packtpub.java9.concurrency.cookbook.chapter01.recipe05.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Class that simulates an initialization operation. It sleeps during four seconds
 *
 */
public class DataSourcesLoader implements Runnable {


	/**
	 * Main method of the class
	 */
	@Override
	public void run() {
		
		// Writes a messsage
		System.out.printf("Begining data sources loading: %s\n",new Date());
		// Sleeps four seconds
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Writes a message
		System.out.printf("Data sources loading has finished: %s\n",new Date());
	}
}
