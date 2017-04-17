package com.packtpub.java9.concurrency.cookbook.chapter03.recipe03.utils;

/**
 * This class is used to store the number of occurrences of the number
 * we are looking for in each row of the bi-dimensional array
 *
 */
public class Results {
	
	/**
	 * Array to store the number of occurrences of the number in each row of the array
	 */
	private final int data[];

	/**
	 * Constructor of the class. Initializes its attributes
	 * @param size Size of the array to store the results
	 */
	public Results(int size){
		data=new int[size];
	}

	/**
	 * Sets the value of one position in the array of results
	 * @param position Position in the array
	 * @param value Value to set in that position
	 */
	public void  setData(int position, int value){
		data[position]=value;
	}
	
	/**
	 * Returns the array of results
	 * @return the array of results
	 */
	public int[] getData(){
		return data;
	}
}
