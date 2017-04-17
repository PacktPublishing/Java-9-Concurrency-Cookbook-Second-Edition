package com.packtpub.java9.concurrency.cookbook.chapter09.recipe11.main;

import com.packtpub.java9.concurrency.cookbook.chapter09.recipe11.task.Task;

public class Main {

	public static void main(String[] args) {
		
		Thread[] threads = new Thread[10];
		
		for (int i=0; i<10; i++) {
			Task task=new Task();
			threads[i]=new Thread(task);
			threads[i].start();
		}
		
		for (int i=0; i<10; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
