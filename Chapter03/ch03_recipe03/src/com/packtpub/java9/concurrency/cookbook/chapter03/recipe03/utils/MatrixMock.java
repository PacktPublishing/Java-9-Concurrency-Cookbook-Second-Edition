package com.packtpub.java9.concurrency.cookbook.chapter03.recipe03.utils;

import java.util.Random;

/**
 * This class generates a random matrix of integer numbers between 1 and 10
 *
 */
public class MatrixMock {
	
	/**
	 * Bi-dimensional array with the random numbers
	 */
	private final int data[][];
	
	/**
	 * Constructor of the class. Generates the bi-dimensional array of numbers.
	 * While generates the array, it counts the times that appears the number we are going
	 * to look for so we can check that the CiclycBarrier class does a good job
	 * @param size Number of rows of the array
	 * @param length Number of columns of the array
	 * @param number Number we are going to look for
	 */
	public MatrixMock(int size, int length, int number){

		int counter=0;
		data=new int[size][length];
		Random random=new Random();
		for (int i=0; i<size; i++) {
			for (int j=0; j<length; j++){
				data[i][j]=random.nextInt(10);
				if (data[i][j]==number){
					counter++;
				}
			}
		}
		System.out.printf("Mock: There are %d ocurrences of number in generated data.\n",counter,number);
	}
	
	/**
	 * This methods returns a row of the bi-dimensional array
	 * @param row the number of the row to return
	 * @return the selected row
	 */
	public int[] getRow(int row){
		if ((row>=0)&&(row<data.length)){
			return data[row];
		}
		return null;
	}

}
