package com.packtpub.java9.concurrency.cookbook.chapter05.recipe02.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;


/**
 * Task that will process a fragment of a line of the document. If the 
 * fragment is too big (100 words or more), it split it in two parts
 * and throw to tasks to process each of the fragments.
 * 
 * It returns the number of appearances of the word in the fragment it has
 * to process.
 *
 */
public class LineTask extends RecursiveTask<Integer>{

	/**
	 * Serial Version of the class. You have to add it because the
	 * ForkJoinTask class implements the serializable interface
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A line of the document
	 */
	private String line[];
	
	/**
	 * Range of positions the task has to process
	 */
	private int start, end;
	
	/**
	 * Word we are looking for
	 */
	private String word;
	
	/**
	 * Constructor of the class
	 * @param line A line of the document
	 * @param start Position of the line where the task starts its process
	 * @param end Position of the line where the task starts its process
	 * @param word Work we are looking for
	 */
	public LineTask(String line[], int start, int end, String word) {
		this.line=line;
		this.start=start;
		this.end=end;
		this.word=word;
	}

	/**
	 * If the part of the line it has to process is smaller that 100, it
	 * calculates the number of appearances of the word in the block. Else,
	 * it divides the block in two blocks and throws to LineTask to calculate
	 * the number of appearances.
	 */
	@Override
	protected Integer compute() {
		Integer result=null;
		if (end-start<100) {
			result=count(line, start, end, word);
		} else {
			int mid=(start+end)/2;
			LineTask task1=new LineTask(line, start, mid, word);
			LineTask task2=new LineTask(line, mid, end, word);
			invokeAll(task1, task2);
			try {
				result=groupResults(task1.get(),task2.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Groups the results of two LineTasks
	 * @param number1 The result of the first LineTask
	 * @param number2 The result of the second LineTask
	 * @return The sum of the numbers
	 */
	private Integer groupResults(Integer number1, Integer number2) {
		Integer result;
		
		result=number1+number2;
		return result;
	}

	/**
	 * Count the appearances of a word in a part of a line of a document
	 * @param line A line of the document
	 * @param start Position of the line where the method begin to count
	 * @param end Position of the line where the method finish the count
	 * @param word Word the method looks for
	 * @return The number of appearances of the word in the part of the line
	 */
	private Integer count(String[] line, int start, int end, String word) {
		int counter;
		counter=0;
		for (int i=start; i<end; i++){
			if (line[i].equals(word)){
				counter++;
			}
		}
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return counter;
	}
	
	

}
