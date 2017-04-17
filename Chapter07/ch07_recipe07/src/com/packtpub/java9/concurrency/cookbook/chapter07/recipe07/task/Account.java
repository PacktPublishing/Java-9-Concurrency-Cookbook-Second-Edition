package com.packtpub.java9.concurrency.cookbook.chapter07.recipe07.task;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * This class simulate a bank account
 *
 */
public class Account {

	/**
	 * Balance of the bank account
	 */
	private final AtomicLong balance;

	/**
	 * Number of operations made;
	 */
	private final LongAdder operations;
	
	/**
	 * Accumulated commission 
	 */
	private final DoubleAccumulator commission;
	
	public Account() {
		balance = new AtomicLong();
		operations = new LongAdder();
		commission = new DoubleAccumulator((x,y)-> x+y*0.2, 0);
	}

	/**
	 * Returns the balance of the account
	 * 
	 * @return the balance of the account
	 */
	public long getBalance() {
		return balance.get();
	}

	/**
	 * Establish the balance of the account
	 * 
	 * @param balance
	 *            the new balance of the account
	 */
	public void setBalance(long balance) {
		this.balance.set(balance);
		operations.reset();
		commission.reset();
	}

	/**
	 * Add an import to the balance of the account
	 * 
	 * @param amount
	 *            import to add to the balance
	 */
	public void addAmount(long amount) {
		this.balance.getAndAdd(amount);
		this.operations.increment();
		this.commission.accumulate(amount);
	}

	/**
	 * Subtract an import to the balance of the account
	 * 
	 * @param amount
	 *            import to subtract to the balance
	 */
	public void subtractAmount(long amount) {
		this.balance.getAndAdd(-amount);
		this.operations.increment();
		this.commission.accumulate(amount);
	}

	/**
	 * Method that returns the number of operations made
	 * @return the operations
	 */
	public long getOperations() {
		return operations.longValue();
	}

	/**
	 * Method that returns the accumulated commission
	 * @return the commission
	 */
	public double getCommission() {
		return commission.get();
	}
	
	

	

}
