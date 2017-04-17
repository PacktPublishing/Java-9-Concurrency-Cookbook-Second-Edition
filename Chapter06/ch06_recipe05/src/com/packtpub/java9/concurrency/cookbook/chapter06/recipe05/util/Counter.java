package com.packtpub.java9.concurrency.cookbook.chapter06.recipe05.util;

public class Counter {
	
	private String value;
	private int counter;
	
	public Counter() {
		counter=1;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}
	/**
	 * @param counter the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public void increment() {
		counter++;
	}

}
