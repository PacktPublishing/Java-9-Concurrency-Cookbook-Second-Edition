package com.packtpub.java9.concurrency.cookbook.appendix.recipe04.task;

import java.util.concurrent.locks.Lock;

import com.packtpub.java9.concurrency.cookbook.appendix.recipe04.utils.Operations;

public class Task2 implements Runnable {

	private final Lock lock;
	
	public Task2 (Lock lock) {
		this.lock=lock;
	}
	
	@Override
	public void run() {
		lock.lock();
		Operations.readData();
		lock.unlock();
		Operations.processData();
		lock.lock();
		Operations.writeData();
		lock.unlock();
	}
}
