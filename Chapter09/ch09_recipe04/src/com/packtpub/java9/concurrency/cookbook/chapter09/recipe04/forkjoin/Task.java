package com.packtpub.java9.concurrency.cookbook.chapter09.recipe04.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * This class implements a task used to show how to monitor the
 * behavior of a Fork/Join pool. The main objective of the task
 * is increment all the elements of an array. Every task has to
 * process a set of elements of this array. If the task has to process
 * more than 100 elements, it divides the set it has two process in two
 * subsets and create two tasks to execute them. Otherwise, it process
 * the elements of the subset it has to process
 */
public class Task extends RecursiveAction{

	/**
	 * Declare and initialize the serial version uid of the class
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Array of elements you want to increment
	 */
	private final int array[];
	
	/**
	 * Start position of the set of elements this task has to process
	 */
	private final int start;
	
	/**
	 * End position of the set of elements this task has to process
	 */
	private int end;
	
	/**
	 * Constructor of the class. Initializes its attributes
	 * @param array Array of elements this task has to process
	 * @param start Start position of the set of elements this task has to process
	 * @param end End position of the set of elements this task has to process 
	 */
	public Task (int array[], int start, int end) {
		this.array=array;
		this.start=start;
		this.end=end;
	}
	
	/**
	 * Main method of the task. If it has to process more that 100 elements, it
	 * divides that set it two sub-sets and creates two task to process them.
	 * Otherwise, it increments directly the elements it has to process.
	 */
	@Override
	protected void compute() {
		if (end-start>100) {
			int mid=(start+end)/2;
			Task task1=new Task(array,start,mid);
			Task task2=new Task(array,mid,end);
			
			/*
			 * Start the sub-tasks
			 */
			task1.fork();
			task2.fork();
			
			/*
			 * Wait for the finalization of the sub-tasks
			 */
			task1.join();
			task2.join();
		} else {
			for (int i=start; i<end; i++) {
				array[i]++;
				
				try {
					TimeUnit.MILLISECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
