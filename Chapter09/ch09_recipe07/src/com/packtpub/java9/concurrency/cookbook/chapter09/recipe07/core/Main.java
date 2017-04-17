package com.packtpub.java9.concurrency.cookbook.chapter09.recipe07.core;

import java.util.concurrent.locks.ReentrantLock;

import com.packtpub.java9.concurrency.cookbook.chapter09.recipe07.task.Task;

/**
 * Main class of the example. It launch ten Task objects 
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Create a Lock
		 */
		ReentrantLock lock=new ReentrantLock();
		
		/*
		 * Executes the threads. There is a problem with this
		 * block of code. It uses the run() method instead of
		 * the start() method.
		 */
		for (int i=0; i<10; i++) {
			Task task=new Task(lock);
			Thread thread=new Thread(task);
			thread.run();
		}

	}

}
