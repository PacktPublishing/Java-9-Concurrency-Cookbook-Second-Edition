package com.packtpub.java9.concurrency.cookbook.appendix.recipe07.core;

import com.packtpub.java9.concurrency.cookbook.appendix.recipe07.task.Task;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i=0; i<20; i++){
			Task task=new Task();
			Thread thread=new Thread(task);
			thread.start();
		}

	}

}
