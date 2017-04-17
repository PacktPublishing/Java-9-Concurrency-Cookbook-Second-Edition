package com.packtpub.java9.concurrency.cookbook.chapter02.recipe04.task;

import java.util.Date;

/**
 * This class implements a reader that consults the prices
 *
 */
public class Reader implements Runnable {

	/**
	 * Class that stores the prices
	 */
	private PricesInfo pricesInfo;
	
	/**
	 * Constructor of the class
	 * @param pricesInfo object that stores the prices
	 */
	public Reader (PricesInfo pricesInfo){
		this.pricesInfo=pricesInfo;
	}
	
	/**
	 * Core method of the reader. Consults the two prices and prints them
	 * to the console
	 */
	@Override
	public void run() {
		for (int i=0; i<20; i++){
			System.out.printf("%s: %s: Price 1: %f\n",new Date(), Thread.currentThread().getName(),pricesInfo.getPrice1());
			System.out.printf("%s: %s: Price 2: %f\n",new Date(), Thread.currentThread().getName(),pricesInfo.getPrice2());
		}
	}

}
