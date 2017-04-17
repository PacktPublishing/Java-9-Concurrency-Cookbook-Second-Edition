package com.packtpub.java9.concurrency.cookbook.chapter02.recipe04.task;

import java.util.Date;

/**
 * This class implements a writer that establish the prices
 *
 */
public class Writer implements Runnable {

	/**
	 * Class that stores the prices
	 */
	private PricesInfo pricesInfo;
	
	/**
	 * Constructor of the class
	 * @param pricesInfo object that stores the prices
	 */
	public Writer(PricesInfo pricesInfo){
		this.pricesInfo=pricesInfo;
	}
	
	/**
	 * Core method of the writer. Establish the prices
	 */
	@Override
	public void run() {
		for (int i=0; i<3; i++) {
			System.out.printf("%s: Writer: Attempt to modify the prices.\n", new Date());
			pricesInfo.setPrices(Math.random()*10, Math.random()*8);
			System.out.printf("%s: Writer: Prices have been modified.\n", new Date());
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
