package com.packtpub.java9.concurrency.cookbook.chapter07.recipe09.task;

import java.util.Date;

import com.packtpub.java9.concurrency.cookbook.chapter07.recipe09.data.Flag;

public class Task implements Runnable {

	private Flag flag;

	public Task(Flag flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		int i = 0;
		while (flag.flag) {
			i++;
		}
		System.out.printf("Task: %d - %s\n", i, new Date());
	}

}
