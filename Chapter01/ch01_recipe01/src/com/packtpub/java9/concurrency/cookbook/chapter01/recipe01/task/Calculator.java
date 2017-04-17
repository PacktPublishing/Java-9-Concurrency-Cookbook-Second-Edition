package com.packtpub.java9.concurrency.cookbook.chapter01.recipe01.task;

/**
 * This class prints the multiplication table of a number
 *
 */
public class Calculator implements Runnable {

	/**
	 * Method that do the calculations
	 */
	@Override
	public void run() {
		long current = 1L;
		long max = 20000L;
		long numPrimes = 0L;

		System.out.printf("Thread '%s': START\n", Thread.currentThread().getName());
		while (current <= max) {
			if (isPrime(current)) {
				numPrimes++;
			}
			current++;
		}
		System.out.printf("Thread '%s': END. Number of Primes: %d\n", Thread.currentThread().getName(), numPrimes);
	}

	/**
	 * Method that calculate if a number is prime or not
	 * 
	 * @param number
	 *            : The number
	 * @return A boolean value. True if the number is prime, false if not.
	 */
	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}
		for (long i = 2; i < number; i++) {
			if ((number % i) == 0) {
				return false;
			}
		}
		return true;
	}

}
