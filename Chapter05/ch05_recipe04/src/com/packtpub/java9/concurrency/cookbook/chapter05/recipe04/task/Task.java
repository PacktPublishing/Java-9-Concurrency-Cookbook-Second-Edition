package com.packtpub.java9.concurrency.cookbook.chapter05.recipe04.task;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * This task throws and exception. It process an array of elements. If the
 * block of elements it has to process has 10 or more elements, it divides
 * the block in two and executes two subtasks to process those blocks. Else, 
 * sleeps the task one second. Additionally,  If the block of elements it 
 * has to process has the third position, it throws an exception.
 * 
 */
public class Task extends RecursiveTask<Integer> {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Array to process
	 */
	private int array[];
	
	/**
	 * Start and end positions of the block of elements this task
	 * has to process
	 */
	private int start, end;
	
	/**
	 * Constructor of the class
	 * @param array Array to process
	 * @param start Start position of the block of elements this task has to process
	 * @param end End position of the block of elements this task has to process
	 */
	public Task(int array[], int start, int end){
		this.array=array;
		this.start=start;
		this.end=end;
	}
	
	/**
	 * Main method of the task. If the block of elements it has to process has 10
	 *  or more elements, it divides the block in two and executes two subtasks 
	 *  to process those blocks. Else, sleeps the task one second. Additionally,
	 *  If the block of elements it has to process has the third position, it 
	 *  throws an exception.
	 */
	@Override
	protected Integer compute() {
		System.out.printf("Task: Start from %d to %d\n",start,end);
		if (end-start<10) {
			if ((3>start)&&(3<end)){
				throw new RuntimeException("This task throws an Exception: Task from  "+start+" to "+end);
			}
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} else {
			int mid=(end+start)/2;
			Task task1=new Task(array,start,mid);
			Task task2=new Task(array,mid,end);
			invokeAll(task1, task2);
			System.out.printf("Task: Result form %d to %d: %d\n",start,mid,task1.join());
			System.out.printf("Task: Result form %d to %d: %d\n",mid,end,task2.join());
		}
		System.out.printf("Task: End form %d to %d\n",start,end);
		return 0;
	}

}
