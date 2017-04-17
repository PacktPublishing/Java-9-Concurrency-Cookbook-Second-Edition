package com.packtpub.java9.concurrency.cookbook.chapter09.recipe11.task;

import java.util.Date;

public class Task implements Runnable {

	@Override
	public void run() {

		Date start, end;

		start = new Date();

		do {
			System.out.printf("%s: tick\n", Thread.currentThread().getName());
			end = new Date();
		} while (end.getTime() - start.getTime() < 100000);
	}

}
