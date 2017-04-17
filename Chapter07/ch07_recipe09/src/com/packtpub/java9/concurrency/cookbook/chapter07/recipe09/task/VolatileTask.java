package com.packtpub.java9.concurrency.cookbook.chapter07.recipe09.task;

import java.util.Date;

import com.packtpub.java9.concurrency.cookbook.chapter07.recipe09.data.VolatileFlag;

public class VolatileTask implements Runnable {

	private VolatileFlag flag;

	public VolatileTask(VolatileFlag flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		int i = 0;
		while (flag.flag) {
			i++;
		}
		System.out.printf("VolatileTask: Stoped %d - %s\n", i, new Date());
	}

}
