package com.packtpub.java9.concurrency.cookbook.chapter05.recipe05.task;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter05.recipe05.util.TaskManager;

/**
 * This task look for a number in an array of integer numbers.
 * If the part of the array it has to process has more than
 * 10 elements, it creates two subtasks and executes then asynchronously
 * with the fork method. Otherwise, look for the number in the block
 * it has to process.
 * 
 * If the task found the number, return the position where the number has
 * been found. Else, return the -1 value. If a subtask found the number,
 * the tasks suspend the other subtask and return the position where the number
 * has been found. If none of the two subtasks found the number, return the -1
 * value.
 *
 */
public class SearchNumberTask extends RecursiveTask<Integer> {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Valued returned when the number has not been found by the task
	 */
	private final static int NOT_FOUND=-1;

	/**
	 * Array of numbers
	 */
	private final int numbers[];
	
	/**
	 * Start and end positions of the block of numbers
	 * this task has to process
	 */
	private final int start, end;
	
	/**
	 * Number this task is going to look for
	 */
	private final int number;
	
	/**
	 * Object that allows the cancellation of all the tasks
	 */
	private final TaskManager manager;
	
	/**
	 * Constructor of the class
	 * @param array Array of numbers
	 * @param start Start position of the block of numbers this task has to process 
	 * @param end End position of the block of numbers this task has to process
	 * @param number Number this task is going to look for
	 * @param manager 
	 */
	public SearchNumberTask(int numbers[], int start, int end, int number, TaskManager manager){
		this.numbers=numbers;
		this.start=start;
		this.end=end;
		this.number=number;
		this.manager=manager;
	}
	
	/**
	 * If the block of number this task has to process has more than
	 * ten elements, divide that block in two parts and create two
	 * new Tasks using the launchTasks() method.
	 * Else, looks for the number in the block assigned to it using
	 * the lookForNumber() method
	 */
	@Override
	protected Integer compute() {
		System.out.println("Task: "+start+":"+end);
		int ret;
		if (end-start>10) {
			ret=launchTasks();
		} else {
			ret=lookForNumber();
		}
		return ret;
	}

	/**
	 * Looks for the number in the block of numbers assigned to this task
	 * @return The position where it found the number or -1 if it doesn't find it
	 */
	private int lookForNumber() {
		for (int i=start; i<end; i++){
			if (numbers[i]==number) {
				System.out.printf("Task: Number %d found in position %d\n",number,i);
				manager.cancelTasks(this);
				return i;
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return NOT_FOUND;
	}

	/**
	 * Divide the block of numbers assigned to this task in two and 
	 * execute to new Task objects to process that blocks 
	 * @return The position where the number has been found of -1
	 * if the number haven't been found in the subtasks
	 */
	private int launchTasks() {
		int mid=(start+end)/2;
		
		SearchNumberTask task1=new SearchNumberTask(numbers,start,mid,number,manager);
		SearchNumberTask task2=new SearchNumberTask(numbers,mid,end,number,manager);
		
		manager.addTask(task1);
		manager.addTask(task2);

		task1.fork();
		task2.fork();
		int returnValue;
		
		returnValue=task1.join();
		if (returnValue!=-1) {
			return returnValue;
		}
		
		returnValue=task2.join();
		return returnValue;
	}
	
	public void logCancelMessage(){
		System.out.printf("Task: Cancelled task from %d to %d\n",start,end);
	}

}
